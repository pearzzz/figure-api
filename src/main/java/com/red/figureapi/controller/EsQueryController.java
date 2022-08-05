package com.red.figureapi.controller;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/esquery")
public class EsQueryController {


    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    // ElasticSearch条件带分页查询
    @GetMapping("/queryByPage/{current}")
    public List<Map<String, Object>> queryListBypage(@PathVariable("current") int current, HttpServletRequest httpServletRequest) throws IOException {
        SearchRequest request = new SearchRequest().indices("es_load_rd"); //创建要搜索的数据库对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); //构建搜索构造器
        searchSourceBuilder.trackTotalHits(true); //开启查询范围最大化设置，否则无法查询10000条以上数据

        // 获取到地址的tag字段值，最多获取到五个tag
        String annual_inc = httpServletRequest.getParameter("annual_inc");
        String pub_rec = httpServletRequest.getParameter("pub_rec");
        String purpose = httpServletRequest.getParameter("purpose");
        String term = httpServletRequest.getParameter("term");
        String emp_length = httpServletRequest.getParameter("emp_length");

        // 创建条件查询构造器
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(annual_inc != null && annual_inc.equals("1")) {
            boolQueryBuilder.must(QueryBuilders.rangeQuery("annual_inc").gte(0).lte(50000)); //低收入查询
        }
        if(pub_rec!= null && pub_rec.equals("2")) {
            boolQueryBuilder.must(QueryBuilders.termQuery("pub_rec", 1)); //有不良信贷记录查询
        }
        if(purpose != null && purpose.equals("3")) {
            boolQueryBuilder.must(QueryBuilders.wildcardQuery("purpose.keyword", "房屋贷款")); //贷款原因查询
        }
        if(term != null && term.equals("4")) {
            boolQueryBuilder.must(QueryBuilders.termQuery("term", 60)); //长期贷款查询
        }
        if(emp_length != null && emp_length.equals("5")) {
            boolQueryBuilder.must(QueryBuilders.termQuery("emp_length", 1)); //是否为职场新人查询
        }

        // 将条件查询构造器注入总的搜索构造器中
        searchSourceBuilder.query(boolQueryBuilder).size(1000000);

        // 进行分页的设置
        searchSourceBuilder.from(current); //当前页
        searchSourceBuilder.size(8); //每页所放的数据量

        // 获取搜索响应对象
        request.source(searchSourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        // 计算得到总页数
        int sumOfPage = 0;
        if(response.getHits().getTotalHits().value % 8 == 0) {
            sumOfPage = (int) response.getHits().getTotalHits().value / 8;
        }
        else {
            sumOfPage = (int) response.getHits().getTotalHits().value / 8 + 1;
        }

        // 获取查询到的数据总量
        int sum = (int) response.getHits().getTotalHits().value;

        // 将所有需要的返回值封装到list中并返回
        List<Map<String, Object>> list = new ArrayList<>();
        for(SearchHit hit : response.getHits().getHits()) {
            String str = hit.toString();
            // 转换成JSON格式
            JSONObject jsonObject = JSONObject.parseObject(str);
            JSONObject source = jsonObject.getJSONObject("_source");
            Map<String, Object> map = new HashMap<>();
            // 申请时间、姓名、职业、地区、收入、贷款金额、贷款期数、贷款原因、不良信贷记录、id、member_id
            map.put("issue_d", source.get("issue_d")); //申请时间
            map.put("name", source.get("name")); //姓名
            map.put("emp_title", source.get("emp_title")); //职业
            map.put("addr_state", source.get("addr_state"));//地区
            map.put("annual_inc", source.get("annual_inc")); //贷款金额
            map.put("loan_amnt", source.get("loan_amnt")); //收入
            map.put("term", source.get("term")); //贷款期数
            map.put("purpose", source.get("purpose")); //贷款原因
            map.put("pub_rec", source.get("pub_rec")); //贷款不良信贷记录
            map.put("id", source.get("id")); //id
            map.put("member_id", source.get("member_id")); //member_id
            list.add(map);
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("data", list); //所有用户的数据
        map1.put("current", current); //当前页
        map1.put("sumOfPage", sumOfPage); //总页数
        map1.put("sum", sum); //数据总量
        List<Map<String, Object>> result = new ArrayList<>();
        result.add(map1);
        return result;
    }

}