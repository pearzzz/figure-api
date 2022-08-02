package com.red.figureapi.db.dao;

import com.red.figureapi.db.pojo.LoanRd;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoanRdDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LoanRd record);

    int insertSelective(LoanRd record);

    LoanRd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoanRd record);

    int updateByPrimaryKey(LoanRd record);
}