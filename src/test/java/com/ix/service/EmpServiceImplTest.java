package com.ix.service;

import com.ix.entity.Emp;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.Assert.*;

public class EmpServiceImplTest extends BaseTest {

    @Autowired
    private EmpService empService;

    @Test
    public void empQueryAllSplitPage() {
        Map<String, Object> map = empService.empQueryAllSplitPage(5,2);
        System.out.println(map);
    }

    @Test
    public void empQueryOne() {
        Emp emp = new Emp();
        emp.setId("1");
        Map<String, Object> map = empService.empQueryOne(emp);
        System.out.println(map);
    }

    @Test
    public void empAdd() {
        Emp emp = new Emp();
        emp.setName("卡卡罗特");
        emp.setAge(20);
        emp.setSalary(2365.8);
        Map<String, Object> map = empService.empAdd(emp);
        System.out.println(map);
    }

    @Test
    public void empModify() {
        Emp emp = new Emp();
        emp.setId("1");
        emp.setSalary(12.2);
        Map<String, Object> map = empService.empModify(emp);
        System.out.println(map);
    }

    @Test
    public void empDelete() {
        Emp emp = new Emp();
        emp.setId("1");
        Map<String, Object> map = empService.empDelete(emp);
        System.out.println(map);
    }
}