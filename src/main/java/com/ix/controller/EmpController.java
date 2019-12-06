package com.ix.controller;

import com.ix.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("emp")
public class EmpController {
    @Autowired
    private EmpService empService;

    @RequestMapping("empQueryAllSplitPage")
    public Map<String,Object> empQueryAllSplitPage(Integer page) {
        //查询数据并返回
        return empService.empQueryAllSplitPage(5, page);
    }

}
