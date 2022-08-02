package com.red.figureapi.db.dao;

import com.red.figureapi.db.pojo.Result;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResultDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Result record);

    int insertSelective(Result record);

    Result selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Result record);

    int updateByPrimaryKey(Result record);
}