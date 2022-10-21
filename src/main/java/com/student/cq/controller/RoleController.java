package com.student.cq.controller;

import com.student.cq.service.IRoleService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;


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
}
