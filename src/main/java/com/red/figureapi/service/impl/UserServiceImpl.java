package com.red.figureapi.service.impl;

import com.red.figureapi.db.dao.UserDao;
import com.red.figureapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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

        for (Map map : relation) {
            Map<String, Object> m = new HashMap<>();

            int personA = (int) map.get("person_a");
            int personB = (int) map.get("person_b");

            int relationId = personA != memberId ? personA : personB;
            m.put("memberId", relationId);

            int type = (int) map.get("relation");
            m.put("type", type);

            String name = userDao.getNameByMemberId(relationId);
            m.put("name", name);

            result.add(m);
        }
        return result;
    }

    /**
     * TODO 根据客户ID查询客户姓名
     * @param memberId 客户ID
     * @return: String 客户姓名
     */
    @Override
    public String getNameByMemberId(int memberId) {
        return userDao.getNameByMemberId(memberId);
    }

    /**
     * TODO 查询当前用户既往信贷记录
     * @param memberId 客户id
     * @param id       当前信贷记录id
     * @return: List<Map < String, Object>> 当前用户既往信贷记录
     */
    @Override
    public List<Map<String, Object>> getPastRecord(int memberId, int id) {
        return userDao.getPastRecord(memberId, id);
    }

    /**
     * TODO 查询客户雷达图
     * @param memberId 客户id
     * @return: Map < String, Double> 雷达图各部分得分
     */
    @Override
    public Map<String, Double> getRadarScore(int memberId) {
        Map<String, Double> result = new HashMap<>();

        //特征得分加入结果
        List<Map<String, Double>> features = userDao.getFeature(memberId);
        Map<String, Double> feature = features.get(0);
        for (String key : feature.keySet()) {
            result.put(key, feature.get(key));
        }

        //收入负债比加入结果
        double dti = userDao.getDti(memberId);
        result.put("dti", 100 - dti);

        //亲友信誉分加入结果
        List<Map<String, Object>> relation = userDao.getRelation(memberId);
        ArrayList<Integer> list = new ArrayList<>();
        for (Map map : relation) {
            int personA = (int) map.get("person_a");
            int personB = (int) map.get("person_b");

            int relationId = personA != memberId ? personA : personB;
            list.add(relationId);
        }

        double totalCreditScore = 0;
        double aveCreditScore = 0;

        if (list.size() == 0) {
            for (int person : list) {
                Double score = userDao.getCreditScoreByMemberId(person);
                if (score != null) {
                    totalCreditScore += score;
                }
            }
            aveCreditScore = totalCreditScore/list.size();
        } else {
            aveCreditScore = 1;
        }

        result.put("relationCredit",aveCreditScore);

        return result;
    }

    /**
     * TODO 根据客户id查询客户综合得分
     * @param memberId 客户id
     * @return: Double 客户综合得分
     */
    @Override
    public Double getCreditScoreByMemberId(int memberId) {
        return userDao.getCreditScoreByMemberId(memberId);
    }


}
