package com.ix.controller;

import com.ix.entity.User;
import com.ix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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

    @RequestMapping("userRegist")
    public Map<String, Object> userRigst(User user,String number,HttpServletRequest request) {
        //创建map
        Map<String, Object> map = new HashMap<>();
        //获取验证码
        String securityCode = (String) request.getSession().getAttribute("securityCode");
        //判断验证是否正确
        if (securityCode.equals(number)) {
            map = userService.userRegist(user);
        } else {
            map.put("status","-200");
            map.put("message","验证码错误");
        }
        return map;
    }

}
