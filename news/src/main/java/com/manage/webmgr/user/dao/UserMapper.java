package com.manage.webmgr.user.dao;

import java.util.List;

import com.manage.webmgr.user.entity.User;

public interface UserMapper
{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByUserName(String username);

    int updateByPrimaryKeySelective(User record);

    int countByRoleCode(String roleCode);

    List<User> queryList();
}