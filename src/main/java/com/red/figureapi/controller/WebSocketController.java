package com.red.figureapi.controller;

import com.red.figureapi.common.R;
import com.red.figureapi.service.TotalDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author pearz
 * @Email zhaihonghao317@163.com
 * @Date 14:13 2022-08-08
 */

@Slf4j
@Component
@Service
@ServerEndpoint("/websocket")
public class WebSocketController {
    @Autowired
    private TotalDataService totalDataService;

    @GetMapping("/searchTermNum")
    public R searchTermNum() {
        return R.ok().put("result", totalDataService.searchTermNum());
    }

    @GetMapping("/searchAmtDistribution")
    public R searchAmtDistribution() {
        List<Map> list = new ArrayList<>();
        for (int i = 0, min = 1, max = 5000; i < 5; i++, min = min + 5000, max = max + 5000) {
            Map<String, Integer> map = new HashMap<>();
            map.put("min", min);
            map.put("max", max);
            map.put("value", totalDataService.searchAmtDistribution(min, max));
            list.add(map);
        }

        Map<String, Integer> map = new HashMap<>();
        map.put("min", 25001);
        map.put("value", totalDataService.searchAmtDistribution(25001, Integer.MAX_VALUE));
        list.add(map);

        return R.ok().put("result", list);
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
        List<Map<String, Object>> fullyPaid = totalDataService.searchIntRateDistributionOfLoanStatus("Fully Paid");
        List<Map<String, Object>> chargedOff = totalDataService.searchIntRateDistributionOfLoanStatus("Charged Off");
        return R.ok().put("FullyPaid", fullyPaid).put("ChargedOff", chargedOff);
    }
}
