package com.suneee.controller;

import com.suneee.model.User;
import com.suneee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/findall")
    @ResponseBody
    public Object findAll(){
        List<User> userList = userService.findAll();
        //List<String> collect = userList.stream().filter(user -> user.getAge() > 20).map(User::getName).collect(Collectors.toList());
        return userList;

    }


    @RequestMapping("/save")
    @ResponseBody
    public Object save(){
       int i  = userService.save();
        //List<String> collect = userList.stream().filter(user -> user.getAge() > 20).map(User::getName).collect(Collectors.toList());
        return i;

    }


    @RequestMapping("/update")
    @ResponseBody
    public Object update(){
        int i  = userService.update();
        //List<String> collect = userList.stream().filter(user -> user.getAge() > 20).map(User::getName).collect(Collectors.toList());
        return i;

    }


    @RequestMapping("/remove")
    @ResponseBody
    public Object remove(){
        int i  = userService.remove();
        //List<String> collect = userList.stream().filter(user -> user.getAge() > 20).map(User::getName).collect(Collectors.toList());
        return i;

    }
}
