package com.student.cq.service;

import com.student.cq.entity.Department;

import java.util.List;

/**
 * 部门服务接口
 */
public interface IDepartmentService {

    /**
     * 获取所有部门
     * @return
     */
    List<Department> list();

    /**
     * 保存部门
     * @param department
     * @return
     */
    boolean save(Department department);

    /**
     * 移除部门
     * @param id
     * @return
     */
    boolean remove(Integer id);

}
