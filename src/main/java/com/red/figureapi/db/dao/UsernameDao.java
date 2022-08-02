package com.red.figureapi.db.dao;

import com.red.figureapi.db.pojo.Username;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsernameDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Username record);

    int insertSelective(Username record);

    Username selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Username record);

    int updateByPrimaryKey(Username record);
}