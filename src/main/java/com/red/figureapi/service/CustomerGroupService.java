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
}
