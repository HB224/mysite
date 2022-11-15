package com.mysite.ghb.administrator.service;
/*
 */

import com.mysite.ghb.administrator.entity.Administrator;
import com.mysite.ghb.administrator.mapper.AdminMapper;
import com.mysite.ghb.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public Administrator CheckUser(String adminname, String password) {
        return adminMapper.CheckUser(adminname, password);
    }
}
