package com.red.figureapi.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author pearz
 * @Email zhaihonghao317@163.com
 * @Date 9:39 2022-08-03
 */
public interface TotalDataService {

    /**
     * TODO 统计各贷款期数数量
     * @return: List<Map < Integer, Integer>> 各贷款期数数量
     */
    List<Map<Integer, Integer>> searchTermNum();

    /**
     * TODO 统计不同贷款金额的记录数量
     * @param min 范围最小值
     * @param max 范围最大值
     * @return: int 贷款金额在最小值和最大值之间的记录数量
     */
    int searchAmtDistribution(int min, int max);

    /**
     * TODO 统计各省人数
     * @return: List<Map < String, Object>> 各省人数
     */
    List<Map<String, Object>> searchProvinceNum();

    /**
     * TODO 统计各贷款意图数量
     * @return: List<Map < String, Object>>
     */
    List<Map<String, Object>> searchPurposeNum();


    /**
     * TODO 获取不同贷款状态下，贷款比率的范围分布
     * @param loanStatus 贷款状态
     * @return: int 该贷款状态下，贷款比率在min和max之间的占比
     */
    List<Map<String, Object>> searchIntRateDistributionOfLoanStatus(String loanStatus);

}