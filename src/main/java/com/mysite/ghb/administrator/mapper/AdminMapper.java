package com.mysite.ghb.administrator.mapper;
import com.mysite.ghb.administrator.entity.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
public interface AdminMapper {

     Administrator CheckUser(String adminname, String password);

}
