package com.mysite.ghb.service;
/*
 */

import com.mysite.ghb.entity.User;

public interface UserService {
    User getUserByid(Long id);
    User addUser(User user);
    User getUserByMail(String mail);
    void updateUserIp(Long id,String ipAddress);
}
