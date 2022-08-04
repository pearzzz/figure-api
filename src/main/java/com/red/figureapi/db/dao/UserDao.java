package com.red.figureapi.db.dao;

import com.red.figureapi.db.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
    /**
     * TODO 获得有坏帐记录人员（loan_status = "Charged Off"）的相关信息
     * @return: List<Map<Object>> 有坏帐记录客户的相关信息
    */
    List<Map<String, Object>> searchChargedOffPerson();

    /**
     * TODO 根据贷款id查询贷款相关数据
     * @param id 贷款id
     * @return: List<Map<String,Object>> id对应的贷款数据
    */
    List<Map<String, Object>> searchLoanInfo(@Param("id") int id);

    /**
     * TODO 根据id查找人物标签
     * @param id
     * @return: List<Map<String,Object>> 人物标签数据
    */
    List<Map<String, Object>> searchTag(@Param("id") int id);

    /**
     * TODO 根据客户member_id获得客户相关人员
     * @param memberId 客户member_id
     * @return: List<Map<String,Object>> 相关人员集合
    */
    List<Map<String, Object>> getRelation(@Param("memberId") int memberId);
}