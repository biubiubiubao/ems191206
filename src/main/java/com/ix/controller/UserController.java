package com.ix.controller;

import com.ix.entity.User;
import com.ix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("userLogin")
    public Map<String,Object> userLogin(String username,String password) {
        Map<String, Object> map = userService.userLogin(username, password);
        return map;
    }

//    public Map<String,Object> userRigst(User user,)

}
