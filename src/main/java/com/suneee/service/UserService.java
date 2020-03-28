package com.suneee.service;

;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.suneee.mapper.UserMapper;
import com.suneee.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> findAll(){
        int age =20;
        return  userMapper.selectList(null);
    }



    public  int save(){
        User user =new User();
        user.setAge(2000);
        user.setName("修文");
        user.setEmail("gbx_2015@163.com");
        int i = userMapper.insert(user);
        System.out.println(i);
        return  i;

    }


    public int update() {
        User user =new User();
        user.setAge(20);
        user.setName("你好");
        user.setId(2L);
        int update = userMapper.update(user, Wrappers.<User>lambdaUpdate().eq(User::getId,user.getId()));
        return  update;
    }


    public int remove() {
        User user =new User();
        user.setAge(20);
        user.setName("你好");
        user.setId(2L);
        int remove = userMapper.delete(Wrappers.<User>lambdaQuery().eq(User::getAge,user.getAge()));
        return   remove;
    }
}
