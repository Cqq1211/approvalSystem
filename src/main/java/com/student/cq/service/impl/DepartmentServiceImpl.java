package com.student.cq.service.impl;

import com.student.cq.entity.Department;
import com.student.cq.exception.ServiceValidationException;
import com.student.cq.mapper.IDepartmentMapper;
import com.student.cq.service.IDepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Resource
    IDepartmentMapper departmentMapper;

    @Override
    public List<Department> list() {
        return departmentMapper.select();
    }

    @Override
    public boolean save(Department department) {
        //判断部门名是否已存在
        if (departmentMapper.selectCountByName(department.getName()) > 0) {
            throw new ServiceValidationException("部门名称已存在，请修改", 402);
        }
        if (department.getId() == 0) {
            //执行新增
            return departmentMapper.insert(department) > 0;
        } else {
            //执行修改
            if (departmentMapper.update(department) == 0) {
                //未修改到任何数据
                throw new ServiceValidationException("传递的部门ID不存在，请检查", 402);
            }
            return true;
        }
    }

    @Override
    public boolean remove(Integer id) {
        if (departmentMapper.delete(id) == 0) {
            //未删除到任何数据
            throw new ServiceValidationException("传递的部门ID不存在，请检查", 402);
        }
        return true;
    }
}
