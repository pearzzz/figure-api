package com.red.figureapi.db.dao;

import com.red.figureapi.db.pojo.CustomerGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CustomerGroupDao {

    /**
     * TODO 根据聚类类别获得贷款金额分布
     * @param min 最小贷款金额
     * @param max 最大贷款金额
     * @param classify 聚类类别
     * @return: int 贷款金额在min和max之间，且聚类类型为classify的人数
    */
    int searchAmtDisByClassify(@Param("min")int min, @Param("max") int max, @Param("classify") int classify);
}