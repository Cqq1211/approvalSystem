package com.student.cq.controller;

import com.student.cq.entity.Role;
import com.student.cq.service.IRoleService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 角色类控制器
 */
@RestController
@RequestMapping("admin/role")
public class RoleController extends BaseController {

    @Resource
    IRoleService roleService;

    @GetMapping
    public Object list(){
        return success(null, roleService.listRole());
    }

    @GetMapping("get/{id}")
    public Object get(@PathVariable Integer id) {
        Role role = roleService.getById(id);
        return success(null, role);
    }

    @PostMapping("save")
    public Object save(@Valid @RequestBody Role role, BindingResult result) {
        roleService.saveRole(role);
        return success("保存成功", null);
    }


    @PostMapping("remove")
    public Object remove(@RequestParam Integer id) {
        roleService.removeRole(id);
        return success("移除成功", null);
    }
}
