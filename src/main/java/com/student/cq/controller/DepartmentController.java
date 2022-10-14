package com.student.cq.controller;

import com.student.cq.entity.Department;
import com.student.cq.service.IDepartmentService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 部门类控制器
 */
@RestController
@RequestMapping("admin/department")
public class DepartmentController extends BaseController {

    @Resource
    IDepartmentService departmentService;

    @GetMapping
    public Object list() {
        return success(null, departmentService.list());
    }

    @PostMapping("save")
    public Object save(@Valid @RequestBody Department department, BindingResult result) {
        departmentService.save(department);
        return success("保存成功", null);
    }

    @GetMapping("remove/{id}")
    public Object remove(@PathVariable Integer id) {
        departmentService.remove(id);
        return success("移除成功", null);
    }

}
