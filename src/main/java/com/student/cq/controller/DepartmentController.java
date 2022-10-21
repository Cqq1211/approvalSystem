package com.student.cq.controller;

import com.student.cq.entity.Department;
import com.student.cq.service.IDepartmentService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('dep_show')")       //当前请求者只有拥有dep_show权限才能正常请求到该方法
    public Object list() {
        return success(null, departmentService.list());
    }

    @PostMapping("save")
    @PreAuthorize("hasAuthority('dep_save')")       //当前请求者只有拥有dep_save权限才能正常请求到该方法
    public Object save(@Valid @RequestBody Department department, BindingResult result) {
        departmentService.save(department);
        return success("保存成功", null);
    }

    @GetMapping("remove/{id}")
    @PreAuthorize("hasAuthority('dep_remove')")
    public Object remove(@PathVariable Integer id) {
        departmentService.remove(id);
        return success("移除成功", null);
    }

}
