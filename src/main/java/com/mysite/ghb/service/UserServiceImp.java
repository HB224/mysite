package com.mysite.ghb.service;
/*
 */

import com.mysite.ghb.entity.User;
import com.mysite.ghb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserByid(Long id) {
        return userMapper.getUserByid(id);
    }

    @Override
    public User addUser(User user) {
        int result = 0;
        result=userMapper.addUser(user);
       if (result!=0){
           return user;
       }else
           {return null;}


    }

    @Override
    public User getUserByMail(String mail) {
        return userMapper.getUserByMail(mail);
    }

    @Override
    public void updateUserIp(Long id, String ipAddress) {
        HashMap<String,Object> map=new HashMap();
        map.put("id",id);
        map.put("ipAddress",ipAddress);
        userMapper.updateUserIp(map);
    }
}
