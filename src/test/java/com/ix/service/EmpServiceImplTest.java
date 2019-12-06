package com.ix.service;

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
    }

    @Test
    public void empAdd() {
    }

    @Test
    public void empModify() {
    }

    @Test
    public void empDelete() {
    }
}