package com.ix.service;

import com.ix.dao.EmpDao;
import com.ix.entity.Emp;
import org.apache.ibatis.session.RowBounds;
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
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpDao empDao;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> empQueryAllSplitPage(Integer step,Integer page) {
        HashMap<String, Object> map = new HashMap<>();
        List<Emp> emps = empDao.selectByRowBounds(null, new RowBounds((page-1)*step, step));
        map.put("status","200");
        map.put("emps",emps);
        return map;
    }

    @Override
    public Map<String, Object> empQueryOne(Emp emp) {
        //创建map
        HashMap<String, Object> map = new HashMap<>();
        //查询
        Emp empSelect = empDao.selectByPrimaryKey(emp);
        //存入map信息
        map.put("status","200");
        map.put("empSelect",empSelect);
        return map;
    }

    @Override
    public Map<String, Object> empAdd(Emp emp) {
        //创建map
        HashMap<String, Object> map = new HashMap<>();
        //设置id
        emp.setId(UUID.randomUUID().toString().replace("-",""));
        //添加
        empDao.insertSelective(emp);
        //存入map信息
        map.put("status","200");
        return map;
    }

    @Override
    public Map<String, Object> empModify(Emp emp) {
        //创建map
        HashMap<String, Object> map = new HashMap<>();
        //修改
        empDao.updateByPrimaryKeySelective(emp);
        //存入map信息
        map.put("status","200");
        return map;
    }

    @Override
    public Map<String, Object> empDelete(Emp emp) {
        //创建map
        HashMap<String, Object> map = new HashMap<>();
        //删除
        empDao.delete(emp);
        //存入map信息
        map.put("status","200");
        return map;
    }
}
