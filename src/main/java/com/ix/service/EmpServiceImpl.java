package com.ix.service;

import com.ix.annotation.EmpAddCacheAnnotation;
import com.ix.annotation.EmpDeleteCacheAnnotation;
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
    @EmpAddCacheAnnotation("添加或查询emp缓存")
    public Map<String, Object> empQueryAllSplitPage(Integer step,Integer page) {
        //创建map
        HashMap<String, Object> map = new HashMap<>();
        //查询数据
        List<Emp> emps = empDao.selectByRowBounds(null, new RowBounds((page-1)*step, step));
        //查询总条数
        int selectCount = empDao.selectCount(null);
        //判断总页数
        int pageCount = 0;
        if (selectCount % step == 0) {
            pageCount = selectCount / step;
        }else{
            pageCount = selectCount / step + 1;
        }
        //存入map
        map.put("status","200");
        map.put("emps",emps);
        map.put("pageCount",pageCount);
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
    @EmpDeleteCacheAnnotation("删除缓存")
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
    @EmpDeleteCacheAnnotation("删除缓存")
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
    @EmpDeleteCacheAnnotation("删除缓存")
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
