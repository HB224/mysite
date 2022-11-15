package com.mysite.ghb.entity;/*
 * @author GongHb
*/

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
public class User {
    private Long userid;
    private String username;
    private String mobile;
    private String email;
    private String password;
    private boolean status;
    private String avatar;
    private Date createDate;
    private String ipAddress;
   // private YunPan yunPan;

    private List<Blog> blogs=new LinkedList<>();

}
