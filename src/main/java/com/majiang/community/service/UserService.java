package com.majiang.community.service;

import com.majiang.community.mapper.UserMapper;
import com.majiang.community.model.User;
import com.majiang.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user){

        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> dbUsers = userMapper.selectByExample(userExample);
        if(dbUsers.size() != 0){
            User dbUser = dbUsers.get(0);
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            userMapper.updateByPrimaryKeySelective(dbUser);
        }else{
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insertSelective(user);
        }

    }

}
