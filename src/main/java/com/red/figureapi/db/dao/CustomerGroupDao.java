package com.red.figureapi.db.dao;

import com.red.figureapi.db.pojo.CustomerGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CustomerGroupDao {

    /**
     * TODO 根据聚类类别获得贷款金额分布
     * @param min 最小贷款金额
     * @param max 最大贷款金额
     * @param classify 聚类类别
     * @return: int 贷款金额在min和max之间，且聚类类型为classify的人数
    */
    int searchAmtDisByClassify(@Param("min")int min, @Param("max") int max, @Param("classify") int classify);

    // 获取客群房屋情况的各个类别的数量(分为客群类别为0或1的情况)
    Map<String, Integer> searchHomeOwnershipSortCount(@Param("classify") int classify);

    /**
     * TODO 根据聚类类别获得期数分布
     * @param classify 聚类类别
     * @return: List<HashMap<Integer, Integer>> 聚类类型为classify的期数分布
    */
    List<HashMap<Integer, Integer>> searchTermDisByClassify(@Param("classify") int classify);
}