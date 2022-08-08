package com.red.figureapi.service.impl;

import com.red.figureapi.db.dao.CustomerGroupDao;
import com.red.figureapi.service.CustomerGroupService;
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
 * @Date 10:33 2022-08-08
 */
@Service
@Slf4j
@Scope("prototype")
public class CustomerGroupServiceImpl implements CustomerGroupService {
    @Autowired
    private CustomerGroupDao customerGroupDao;


    /**
     * TODO 根据聚类类别获得贷款金额分布
     * @param classify 聚类类别
     * @return: List<Map < String, Object>> 聚类类型为classify的贷款金额分布
     */
    @Override
    public List<Map<String, Object>> searchAmtDisByClassify(int classify) {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> map0 = new HashMap<>();
        map0.put("min", 0);
        map0.put("max", 10000);
        map0.put("count", customerGroupDao.searchAmtDisByClassify(0, 10000, classify));
        list.add(map0);

        for (int i = 0, min = 10000, max = 15000; i < 4; i++, min = min + 5000, max = max + 5000) {
            Map<String, Object> map = new HashMap<>();
            map.put("min", min);
            map.put("max", max);
            map.put("count", customerGroupDao.searchAmtDisByClassify(min, max, classify));
            list.add(map);
        }

        Map<String, Object> map1 = new HashMap<>();
        map1.put("min", 30000);
        map1.put("count", customerGroupDao.searchAmtDisByClassify(30000, 9999999, classify));
        list.add(map1);

        return list;
    }
}
