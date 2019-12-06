package com.ix.service;

import com.ix.entity.Emp;

import java.util.Map;

public interface EmpService {
    /**
     * emp 查询全部 分页
     */
    Map<String,Object> empQueryAllSplitPage(Integer step,Integer page);

    /**
     * emp 查询一个Emp
     * 参数emp
     */
    Map<String,Object> empQueryOne(Emp emp);

    /**
     * 添加Emp
     * 参数emp
     */
    Map<String,Object> empAdd(Emp emp);

    /**
     *  修改Emp
     *  参数emp
     */
    Map<String,Object> empModify(Emp emp);

    /**
     * 删除Emp
     * 参数emp
     */
    Map<String,Object> empDelete(Emp emp);


}
