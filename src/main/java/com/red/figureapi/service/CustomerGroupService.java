package com.red.figureapi.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author pearz
 * @Email zhaihonghao317@163.com
 * @Date 10:33 2022-08-08
 */
public interface CustomerGroupService {
    /**
     * TODO 根据聚类类别获得贷款金额分布
     * @param classify 聚类类别
     * @return: List<Map<String, Object>> 聚类类型为classify的贷款金额分布
     */
    List<Map<String, Object>> searchAmtDisByClassify(int classify);


    // 获取客群房屋情况的各个类别的数量(分为客群类别为0或1的情况)
    Map<String, Integer> searchHomeOwnershipSortCount(@Param("classify") int classify);

    /**
     * TODO 根据聚类类别获得期数分布
     * @param classify 聚类类别
     * @return: List<HashMap<Integer, Integer>> 聚类类型为classify的期数分布
     */
    Map<String, Integer> searchTermDisByClassify(@Param("classify") int classify);
}