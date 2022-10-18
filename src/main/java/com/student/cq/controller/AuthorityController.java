package com.student.cq.controller;

import com.student.cq.service.IAuthorityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 权限字典 Controller
 */
@RestController
@RequestMapping("admin/authority")
public class AuthorityController extends BaseController {

    @Resource
    IAuthorityService authorityService;

    @GetMapping
    public Object index() {
        return success(null, authorityService.list());
    }

}
