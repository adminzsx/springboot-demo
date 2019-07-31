package com.manage.webmgr.user.dao;

import com.manage.webmgr.user.entity.Newspapser;

public interface NewspapserMapper {
    int deleteByPrimaryKey(Integer fId);

    int insert(Newspapser record);

    int insertSelective(Newspapser record);

    Newspapser selectByPrimaryKey(Integer fId);

    int updateByPrimaryKeySelective(Newspapser record);

    int updateByPrimaryKey(Newspapser record);
}