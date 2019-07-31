package com.manage.webmgr.user.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manage.webmgr.user.dao.UserMapper;
import com.manage.webmgr.user.entity.User;
import com.manage.webmgr.user.service.UserService;

@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insertSelective(User user)
    {

        return userMapper.insertSelective(user);
    }

    @Override
    public User selectByUserName(String name)
    {
        return userMapper.selectByUserName(name);
    }

    @Override
    public PageInfo<User> getUserList(HttpServletRequest request, Integer page, Integer pageSize)
    {
        PageHelper.startPage(page, pageSize);
        List<User> list = userMapper.queryList();
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        return pageInfo;
    }

    @Override
    public int deleteByPrimaryKey(Integer id)
    {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User selectByPrimaryKey(Integer id)
    {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record)
    {
        return userMapper.updateByPrimaryKeySelective(record);
    }
}
