package com.manage.webmgr.user.service;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;
import com.manage.webmgr.user.entity.User;

public interface UserService
{

    int insertSelective(User user);

    User selectByUserName(String name);

    public PageInfo<User> getUserList(HttpServletRequest request, Integer page, Integer pageSize);

    int deleteByPrimaryKey(Integer id);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

}
