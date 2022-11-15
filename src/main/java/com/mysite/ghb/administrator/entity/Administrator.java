package com.mysite.ghb.administrator.entity;/*
 * @author GongHb
*/

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Administrator {

    private String adminname;

    private String password;

    private String avatar;

    public Administrator(String adminname, String password) {
       // this.userid = userid;
        this.adminname=adminname;

        this.password = password;
    }
}
