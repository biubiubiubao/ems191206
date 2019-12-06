package com.ix.service;

import com.ix.Ems191206Application;
import com.ix.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;


@SpringBootTest(classes = Ems191206Application.class)
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void userLogin() {
        Map<String, Object> map = userService.userLogin("Tom", "123456");
        System.out.println(map);
    }

    @Test
    public void userRegist() {
        User user = new User();
        user.setUsername("Ligs");
        user.setPassword("123456");
        user.setSex("男");
        user.setName("李光沭");
        Map<String, Object> map = userService.userRegist(user);
        System.out.println(map);
    }
}