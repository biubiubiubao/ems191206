package com.ix.service;

import com.ix.entity.User;

import java.util.Map;

public interface UserService {
    /**
     * User登录
     * 参数usernmae password
     */
    Map<String,Object> userLogin(String username,String password);

    /**
     * User注册
     * 参数User
     */
    Map<String,Object> userRegist(User user);

}
