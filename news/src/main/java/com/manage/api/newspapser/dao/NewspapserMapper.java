package com.manage.api.newspapser.dao;

import com.manage.api.newspapser.entity.Newspapser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewspapserMapper {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(NewspapserMapper record);
//
//    int insertSelective(NewspapserMapper record);
//
//    NewspapserMapper selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(NewspapserMapper record);
//
//    int updateByPrimaryKey(NewspapserMapper record);

    List<Newspapser> selectByType(@Param("type") Integer type);
}