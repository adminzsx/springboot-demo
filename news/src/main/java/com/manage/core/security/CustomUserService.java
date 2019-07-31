package com.manage.core.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.manage.core.entity.UserInfo;
import com.manage.webmgr.user.entity.User;
import com.manage.webmgr.user.entity.UserStateEnum;
import com.manage.webmgr.user.service.UserService;

@Service
public class CustomUserService implements UserDetailsService
{

    @Autowired
    UserService userService;
   
    @Override
    public UserDetails loadUserByUsername(String username)
    {

        User user = userService.selectByUserName(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("用户不存在");
        }
        if(UserStateEnum.normal.getState()!=user.getState().intValue()){
        	throw new BadCredentialsException("当前用户未授权登录");
        }
 
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
      
        UserInfo userInfo = new UserInfo(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
        userInfo.setUserid(user.getId());
        userInfo.setName(user.getUsername());
        userInfo.setRealName(user.getRealname());
        userInfo.setRole(user.getRole());
        return userInfo;
    }
}
