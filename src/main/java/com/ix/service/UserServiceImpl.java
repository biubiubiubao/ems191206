package com.ix.service;

import com.ix.dao.UserDao;
import com.ix.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> userLogin(String username, String password) {
        //赋值给User对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        //根据username和password查询
        User userLogin = userDao.selectOne(user);
        //创建Map
        HashMap<String, Object> map = new HashMap<>();
        //判断username和password是否正确
        if (userLogin == null) {
            //登录失败
            map.put("status","-200");
            map.put("message","账号或密码错误");
        }else{
            //登录成功
            map.put("status","200");
            map.put("userLogin",userLogin);
        }
        return map;
    }

    @Override
    public Map<String, Object> userRegist(User user) {
        //设置id
        user.setId(UUID.randomUUID().toString().replace("-",""));
        //创建Map
        HashMap<String, Object> map = new HashMap<>();
        try {
            //注册
            userDao.insertSelective(user);
            //注册成功
            map.put("status","200");
            map.put("message","注册成功");
        } catch (Exception e) {
            //注册失败
            e.printStackTrace();
            map.put("status","-200");
            map.put("message","注册失败");
        }
        return map;
    }
}
