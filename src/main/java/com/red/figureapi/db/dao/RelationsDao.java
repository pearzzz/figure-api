package com.red.figureapi.db.dao;

import com.red.figureapi.db.pojo.Relations;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RelationsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Relations record);

    int insertSelective(Relations record);

    Relations selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Relations record);

    int updateByPrimaryKey(Relations record);
}