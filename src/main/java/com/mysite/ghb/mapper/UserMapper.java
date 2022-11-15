package com.mysite.ghb.mapper;
import com.mysite.ghb.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
public interface UserMapper {

     User getUserByid(Long id);
    User getUserByMail(String mail);
    int  addUser(User user);
    void updateUserIp(Map map);
 /*   private static Map<Integer,User> userList=null;
    //主键自增
    private static Integer intitid=000001;
    //增加一个用户
    public void sava(User user) {
        if(user.getUserid()==null){
            user.setUserid(intitid++);
        }
        userList.put(user.getUserid(),user);
       // user.setYunPan(YunPanDao.);
    }

    //查询所有用户信息
    public Collection<User> getAll(){
        
        return userList.values();
    }
    //通过值查询员工
    public User getUserByid(Integer id){
        return userList.get(id);
    }
    //删除用户
    public void deleteUserByid(Integer id){
        userList.remove(id);
    }

    User getUserByMailByid(int id) {
        return null;
    }

*/
}
