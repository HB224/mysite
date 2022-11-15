package com.mysite.ghb.administrator.service;
/*
 */

import com.mysite.ghb.administrator.entity.Administrator;

public interface AdminService {
    Administrator CheckUser(String adminname,String password);
}
