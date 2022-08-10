package com.red.figureapi.utils;

import com.red.figureapi.db.pojo.Event;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Description
 * @Author pearz
 * @Email zhaihonghao317@163.com
 * @Date 14:02 2022-08-10
 */
public class DataCleanByFlink {

    public static void main(String[] args) throws Exception {

        //创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //设置并行度
        env.setParallelism(1);

        //获取数据源
        DataStream<Event> stream = env.addSource(new RichSourceFunction<Event>() {

                    private volatile boolean isRunning = true;
                    private Connection conn = null;
                    private PreparedStatement ps = null;

                    // 打开数据库连接，只执行一次，之后一直使用这个连接
                    @Override
                    public void open(Configuration parameters) throws Exception {
                        super.open(parameters);
                        Class.forName("com.mysql.cj.jdbc.Driver");  // 加载数据库驱动
                        conn = (Connection) DriverManager.getConnection(  // 获取连接
                                "jdbc:mysql://192.168.10.200:3306/hive?useSSL=false&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&tinyInt1isBit=false&allowMultiQueries=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true",  // 数据库URL
                                "root",  // 用户名
                                "root");  // 登录密码
                        ps = (PreparedStatement) conn.prepareStatement(  // 获取执行语句
                                "SELECT id, grade FROM data_flink LIMIT 0,10");  // 需要执行的SQL语句
                    }

                    // 执行查询并获取结果
                    @Override
                    public void run(SourceContext<Event> ctx) throws Exception {
//                while(isRunning) {  // 使用while循环可以不断读取数据
                        ResultSet resultSet = ps.executeQuery();
                        while (resultSet.next()) {
                            String grade = resultSet.getString("grade");
                            int id = Integer.parseInt(resultSet.getString("id"));
                            ctx.collect(new Event(id, grade));  // 以流的形式发送结果
                        }
//                    Thread.sleep(6000);  // 每隔6秒查询一次
//                }
                    }

                    // 取消数据生成
                    @Override
                    public void cancel() {
                        isRunning = false;
                    }

                    // 关闭数据库连接
                    @Override
                    public void close() throws Exception {
                        super.close();
                        if (conn != null) {
                            conn.close();
                        }
                        if (ps != null) {
                            ps.close();
                        }
                    }
                })

                //中间可以进行一些数据转换操作
                .map(new MapFunction<Event, Event>() {
                    @Override
                    public Event map(Event event) throws Exception {
                        return new Event(event.getId(), event.getGrade()+"_");
                    }
                });

        //把数据存入到hive中
        stream.addSink(JdbcSink.sink(
                "INSERT INTO data_flink VALUES (?)",//SQL语句 插值表达式
                (((statement, event) -> {//(进行数据插入,数据源的数据)
                    statement.setString(1, event.getGrade());
                })),
                new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
                        .withUrl("jdbc:mysql://192.168.10.200:3306/hive?useSSL=false&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&tinyInt1isBit=false&allowMultiQueries=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true")//URL连接地址
                        .withDriverName("com.mysql.cj.jdbc.Driver")//JDBC驱动
                        .withUsername("root")//用户名
                        .withPassword("root")//密码
                        .build()
        ));

        //启动
        env.execute();
    }
}
