package com.red.figureapi.controller;

import com.red.figureapi.common.R;
import com.red.figureapi.service.TotalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author pearz
 * @Email zhaihonghao317@163.com
 * @Date 9:43 2022-08-03
 */
@RestController
@RequestMapping("totaldata")
public class TotalDataController {

    @Autowired
    private TotalDataService totalDataService;

    @GetMapping("/searchTermNum")
    public R searchTermNum() {
        return R.ok().put("result", totalDataService.searchTermNum());
    }

    @GetMapping("/searchAmtDistribution")
    public R searchAmtDistribution() {
        return R.ok().put("result", totalDataService.searchAmtDistribution());
    }

    @GetMapping("/searchProvinceNum")
    public R searchProvinceNum() {
        return R.ok().put("result", totalDataService.searchProvinceNum());
    }

    @GetMapping("/searchPurposeNum")
    public R searchPurposeNum() {
        return R.ok().put("result", totalDataService.searchPurposeNum());
    }

    @GetMapping("/searchIntRateDistributionOfLoanStatus")
    public R searchIntRateDistributionOfLoanStatus() {
        List<Map<String, Object>> fullyPaid = totalDataService.searchIntRateDistributionOfLoanStatu("Fully Paid");
        List<Map<String, Object>> chargedOff = totalDataService.searchIntRateDistributionOfLoanStatu("Charged Off");
        return R.ok().put("FullyPaid", fullyPaid).put("ChargedOff", chargedOff);
    }
}