package com.red.figureapi.controller;

import com.red.figureapi.common.R;
import com.red.figureapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author pearz
 * @Email zhaihonghao317@163.com
 * @Date 10:39 2022-08-04
 */

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("searchChargedOffPerson")
    public R searchChargedOffPerson() {
        return R.ok().put("result", userService.searchChargedOffPerson());
    }

    @GetMapping("searchLoanInfo")
    public R searchLoanInfo(HttpServletRequest request) {
        int id = Integer.valueOf(request.getParameter("id"));
        List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
        List<Map<String, Object>> list = userService.searchLoanInfo(id);

        Map<String, Object> map = list.get(0);
        for (String key : map.keySet()) {
            Map<String, Object> m = new HashMap<>();
            m.put("name", key);
            m.put("value", map.get(key));
            result.add(m);
        }

        return R.ok().put("result", result);
    }

    @GetMapping("searchTag")
    public R searchTag(HttpServletRequest request) {
        int id = Integer.valueOf(request.getParameter("id"));
        return R.ok().put("result", userService.searchTag(id));
    }

    @GetMapping("getRelation")
    public R getRelation(HttpServletRequest request) {
        int memberId = Integer.valueOf(request.getParameter("memberId"));
        String name = userService.getNameByMemberId(memberId);
        return R.ok().put("memberId", memberId).put("name", name).put("relationship", userService.getRelation(memberId));
    }

    @GetMapping("getPastRecord")
    public R getPastRecord(HttpServletRequest request) {
        int memberId = Integer.valueOf(request.getParameter("memberId"));
        int id = Integer.valueOf(request.getParameter("id"));
        return R.ok().put("result", userService.getPastRecord(memberId, id));
    }

    @GetMapping("getCreditScoreByMemberId")
    public R getCreditScoreByMemberId(HttpServletRequest request) {
        int memberId = Integer.valueOf(request.getParameter("memberId"));
        return R.ok().put("result", userService.getCreditScoreByMemberId(memberId));
    }

    @GetMapping("getRadarScore")
    public R getRadarScore(HttpServletRequest request) {
        int memberId = Integer.valueOf(request.getParameter("memberId"));
        return R.ok().put("result", userService.getRadarScore(memberId));
    }

    @GetMapping("getRelationRecord")
    public R getRelationRecord(HttpServletRequest request) {
        int memberId = Integer.valueOf(request.getParameter("memberId"));
        return R.ok().put("result", userService.getPastRecord(memberId, -1));
    }
}