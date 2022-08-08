package com.red.figureapi.db.dao;

import com.red.figureapi.db.pojo.LoanRd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface LoanRdDao {

    /**
     * TODO 统计各贷款期数数量
     * @return: List<Map<Integer,Integer>> 各贷款期数数量
    */
    List<Map<Integer, Integer>> searchTermNum();

    /**
     * TODO 统计不同贷款金额的记录数量
     * @param min 范围最小值
     * @param max 范围最大值
     * @return: int 贷款金额在最小值和最大值之间的记录数量
    */
    int searchAmtDistribution(@Param("min") int min, @Param("max") int max);

    /**
     * TODO 统计各省人数
     * @return: List<Map<String,Object>> 各省人数
    */
    List<Map<String, Object>> searchProvinceNum();

    /**
     * TODO 统计各贷款意图数量
     * @return: List<Map<String,Object>>
    */
    List<Map<String, Object>> searchPurposeNum();

    /**
     * TODO 获取不同贷款状态（已还清、坏账、正在还等）的记录数量
     * @param loanStatus 贷款状态
     * @return: int 贷款状态对应的记录数量
    */
    int getLoanStatusCount(@Param("loanStatus") String loanStatus);

    /**
     * TODO 获取不同贷款状态下，不同贷款比率的数量
     * @param min 比率范围最小值
     * @param max 比率范围最大值
     * @param loanStatus 贷款状态
     * @return: int 该贷款状态下，贷款比率在min和max之间的记录数量
    */
    int searchIntRateAndLoanStatusNum(@Param("min") int min, @Param("max") int max, @Param("loanStatus") String loanStatus);
}