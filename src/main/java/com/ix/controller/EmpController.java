package com.ix.controller;

import com.ix.entity.Emp;
import com.ix.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("emp")
public class EmpController {
    @Autowired
    private EmpService empService;

    @ResponseBody
    @RequestMapping("empQueryAllSplitPage")
    public Map<String,Object> empQueryAllSplitPage(Integer page) {
//        long start = System.currentTimeMillis();
        Map<String, Object> map = empService.empQueryAllSplitPage(5, page);
//        long end = System.currentTimeMillis();
//        System.out.println("时间差 "+(end-start));
        //查询数据并返回
        return map;
    }

    @ResponseBody
    @RequestMapping("empAdd")
    public Map<String,Object> empAdd(Emp emp) {
        //添加数据并返回
        return empService.empAdd(emp);
    }

    @RequestMapping("empQueryOne")
    public String empQueryOne(HttpServletRequest request, Emp emp) {
        Map<String, Object> map = empService.empQueryOne(emp);
        Emp empSelect = (Emp) map.get("empSelect");
        request.getSession().setAttribute("empQueryOne",empSelect);
        //添加数据并返回
        return "forward:/updateEmp.jsp";
    }

    @RequestMapping("empModify")
    public String empModify(Emp emp) {
        empService.empModify(emp);
        return "redirect:/emplist.jsp";
    }

    @RequestMapping("empDelete")
    public String empDelete(Emp emp) {
        empService.empDelete(emp);
        return "redirect:/emplist.jsp";
    }


}
