package com.red.figureapi.service.impl;

import com.red.figureapi.db.dao.LoanRdDao;
import com.red.figureapi.service.TotalDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
     * @param min 范围最小值
     * @param max 范围最大值
     * @return: int 贷款金额在最小值和最大值之间的记录数量
     */
    @Override
    public int searchAmtDistribution(int min, int max) {
        return loanRdDao.searchAmtDistribution(min, max);
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
    public List<Map<String, Object>> searchIntRateDistributionOfLoanStatus(String loanStatus) {
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

}