package com.red.figureapi.controller;

import com.red.figureapi.common.R;
import com.red.figureapi.service.CustomerGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author pearz
 * @Email zhaihonghao317@163.com
 * @Date 10:17 2022-08-08
 */
@RestController
@RequestMapping("/customergroup")
public class CustomerGroupController {
    @Autowired
    private CustomerGroupService customerGroupService;

    @GetMapping("/searchAmtDisByClassify")
    public R searchAmtDisByClassify(HttpServletRequest request) {
        int classify = Integer.valueOf(request.getParameter("classify"));
        return R.ok().put("result", customerGroupService.searchAmtDisByClassify(classify));
    }

    @GetMapping("/searchHomeOwnershipSortCount")
    public R searchHomeOwnershipSortCount(HttpServletRequest request) {
        int classify = Integer.valueOf(request.getParameter("classify"));
        return R.ok().put("result", customerGroupService.searchHomeOwnershipSortCount(classify));
    }

}