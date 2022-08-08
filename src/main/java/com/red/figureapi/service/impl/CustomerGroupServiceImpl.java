package com.red.figureapi.service.impl;

import com.red.figureapi.db.dao.CustomerGroupDao;
import com.red.figureapi.service.CustomerGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author pearz
 * @Email zhaihonghao317@163.com
 * @Date 10:33 2022-08-08
 */
@Service
@Slf4j
@Scope("prototype")
public class CustomerGroupServiceImpl implements CustomerGroupService {
    @Autowired
    private CustomerGroupDao customerGroupDao;


}
