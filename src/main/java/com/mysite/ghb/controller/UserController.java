package com.mysite.ghb.controller;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.mysite.ghb.entity.User;
import com.mysite.ghb.service.UserService;
import com.mysite.ghb.util.DateUtil;
import com.mysite.ghb.util.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    /*暂定username为数据库的Email进行验证*/
    @RequestMapping(value = "/user/login")
    @ResponseBody
    public Map<String,Object> login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpSession session,
                        HttpServletRequest request) {
        //返回data
        Map<String, Object> data = new HashMap();
        // 使用 shiro 进行登录
        Subject subject = SecurityUtils.getSubject();
        //获取登录ip地址
        String ipAddress = request.getRemoteAddr();
        //获取token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, ipAddress);
        token.setRememberMe(true);//默认设置记住我



        try {
            subject.login(token);
            // 登录成功
            User user = (User) subject.getPrincipal();
            //这里将user加入到了session中
           session.setAttribute("user", user);
            data.put("code",1);
            data.put("url","/index.html");
            //data.put("message","登陆成功");
            logger.info(user.getUsername()+"登陆成功");
            userService.updateUserIp(user.getUserid(),ipAddress);
        } catch (UnknownAccountException e) {
            data.put("code", 0);
            data.put("message", username + "账号不存在");
            logger.error(username + "账号不存在");
            return data;
        } catch (DisabledAccountException e) {
            data.put("code", 0);
            data.put("message", username + "账号被锁定");
            logger.error(username + "账号被锁定");
            return data;
        } catch (AuthenticationException e) {
            data.put("code", 0);
            data.put("message", username + "密码错误");
            logger.error(username + "密码错误");
            return data;
        }

        return data;

    }

    /**
     *
     * 功能描述: 修改密码
     *
     */
    @RequestMapping("/setPwd")
    @ResponseBody
    public Map<String,Object> setP(String pwd, String isPwd){
        logger.info("进行密码重置");
        Map<String,Object> data = new HashMap();
        if(!pwd.equals(isPwd)){
            data.put("code",0);
            data.put("message","两次输入的密码不一致!");
            logger.error("两次输入的密码不一致!");
            return data;
        }
        //获取当前登陆的用户信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        int result = 1;//(user.getPassword(),pwd);
        if(result == 0){
            data.put("code",0);
            data.put("msg","修改密码失败！");
            logger.error("用户修改密码失败！");
            return data;
        }
        data.put("code",1);
        data.put("msg","修改密码成功！");
        logger.info("用户修改密码成功！");
        return data;
    }



    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> register(@RequestParam("username") String username,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                            HttpServletRequest request){

        Map<String, Object> data = new HashMap();

        User tmpuser=userService.getUserByMail(email);
        if (tmpuser!=null){
            data.put("code",0);
            data.put("error",email+"已经注册过啦，您可以直接登录");
        }else {
            User user=new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setCreateDate(DateUtil.getCurrentTime());
            user.setPassword(MD5Util.myMd5(email,password));
            user.setStatus(true);
            user.setIpAddress(request.getRemoteAddr());
            user.setUserid(IdWorker.getId(User.class));
            if (userService.addUser(user)!=null){
                data.put("code",1);
            }else {
                data.put("code",0);
                data.put("error","未知错误，请联系管理员");
            }
        }
        return data;
    }

}
