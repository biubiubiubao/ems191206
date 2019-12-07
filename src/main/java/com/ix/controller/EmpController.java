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
        //查询数据并返回
        return empService.empQueryAllSplitPage(5, page);
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
    public String emoModify(Emp emp) {
        empService.empModify(emp);
        return "redirect:/emplist.jsp";
    }


}
