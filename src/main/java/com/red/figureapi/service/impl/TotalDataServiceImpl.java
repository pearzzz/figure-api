package com.red.figureapi.service.impl;

import com.red.figureapi.db.dao.LoanRdDao;
import com.red.figureapi.service.TotalDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author pearz
 * @Email zhaihonghao317@163.com
 * @Date 9:39 2022-08-03
 */
@Service
@Slf4j
@Scope("prototype")
public class TotalDataServiceImpl implements TotalDataService {

    @Autowired
    private LoanRdDao loanRdDao;

    /**
     * TODO 统计各贷款期数数量
     * @return: List<Map < Integer, Integer>> 各贷款期数数量
     */
    @Override
    public List<Map<Integer, Integer>> searchTermNum() {
        return loanRdDao.searchTermNum();
    }

    /**
     * TODO 统计不同贷款金额的记录数量
     * @return: int 贷款金额在最小值和最大值之间的记录数量
     */
    @Override
    public List<Map<String, Integer>> searchAmtDistribution() {
        List<Map<String, Integer>> list = new ArrayList<>();
        for (int i = 0, min = 1, max = 5000; i < 5; i++, min = min + 5000, max = max + 5000) {
            Map<String, Integer> map = new HashMap<>();
            map.put("min", min);
            map.put("max", max);
            map.put("value", loanRdDao.searchAmtDistribution(min, max));
            list.add(map);
        }

        Map<String, Integer> map = new HashMap<>();
        map.put("min", 25001);
        map.put("value", loanRdDao.searchAmtDistribution(25001, Integer.MAX_VALUE));
        list.add(map);

        return list;
    }

    /**
     * TODO 统计各省人数
     * @return: List<Map < String, Object>> 各省人数
     */
    @Override
    public List<Map<String, Object>> searchProvinceNum() {
        return loanRdDao.searchProvinceNum();
    }

    /**
     * TODO 统计各贷款意图数量
     * @return: List<Map < String, Object>>
     */
    @Override
    public List<Map<String, Object>> searchPurposeNum() {
        return loanRdDao.searchPurposeNum();
    }

    /**
     * TODO 获取不同贷款状态下，贷款比率的范围分布
     * @param loanStatus 贷款状态
     * @return: List<Map<String, Object>> 该贷款状态下，贷款比率的占比
     */
    @Override
    public List<Map<String, Object>> searchIntRateDistributionOfLoanStatu(String loanStatus) {
        List<Map<String, Object>> list = new ArrayList<>();

        //获取贷款状态对应的记录数量
        int loanStatusCount = loanRdDao.getLoanStatusCount(loanStatus);
        //保留小数点后两位
        DecimalFormat df = new DecimalFormat("######0.00");
        for (int i = 0, min = 5, max = 10; i < 5; i++, min = min + 5, max = max + 5) {
            Map<String, Object> map = new HashMap<>();
            map.put("min", min);
            map.put("max", max);
            double rate = (double) loanRdDao.searchIntRateAndLoanStatusNum(min, max, loanStatus) / loanStatusCount;
            map.put("value", df.format(rate));
            list.add(map);
        }
        return list;
    }

    @Override
    public Map<String, List> searchIntRateDistributionOfLoanStatus() {
        Map<String, List> result = new HashMap<>();
        List<Map<String, Object>> fullyPaid = searchIntRateDistributionOfLoanStatu("Fully Paid");
        List<Map<String, Object>> chargedOff = searchIntRateDistributionOfLoanStatu("Charged Off");
        result.put("fullyPaid", fullyPaid);
        result.put("chargedOff", chargedOff);
        return result;
    }

}