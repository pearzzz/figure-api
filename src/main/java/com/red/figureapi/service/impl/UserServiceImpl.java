package com.red.figureapi.service.impl;

import com.red.figureapi.db.dao.UserDao;
import com.red.figureapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author pearz
 * @Email zhaihonghao317@163.com
 * @Date 10:38 2022-08-04
 */
@Service
@Slf4j
@Scope("prototype")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * TODO 获得有坏帐记录人员（loan_status = "Charged Off"）的相关信息
     * @return: List<Map < Object>> 有坏帐记录客户的相关信息
     */
    @Override
    public List<Map<String, Object>> searchChargedOffPerson() {
        return userDao.searchChargedOffPerson();
    }

    /**
     * TODO 根据贷款id查询贷款相关数据
     * @param id 贷款id
     * @return: List<Map < String, Object>> id对应的贷款数据
     */
    @Override
    public List<Map<String, Object>> searchLoanInfo(int id) {
        return userDao.searchLoanInfo(id);
    }

    /**
     * TODO 根据id查找人物标签
     * @param id
     * @return: jList<Map < String, Object>> 人物标签数据
     */
    @Override
    public List<Map<String, Object>> searchTag(int id) {
        return userDao.searchTag(id);
    }

    /**
     * TODO 根据客户member_id获得客户相关人员
     * @param memberId 客户member_id
     * @return: List<Map < String, Object>> 相关人员集合
     */
    @Override
    public List<Map<String, Object>> getRelation(int memberId) {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Map<String, Object>> relation = userDao.getRelation(memberId);

        return null;
    }


}
