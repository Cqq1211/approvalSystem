package com.student.cq.controller;

import com.student.cq.entity.User;
import com.student.cq.service.IUserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("admin/user")
public class UserController extends BaseController{
    @Resource
    IUserService userService;

    @Data
    static class UserListParamBody{
        @NotNull(message = "必须传递pageIndex")
        private Integer pageIndex;

        @Max(value = 20, message = "页大小不能超过10条")
        @NotNull(message = "必须传递pageSize")
        private Integer pageSize;

        private Integer departmentId;
        private Integer roleId;
        private String username;
    }

    @PostMapping
    public Object index(@Valid @RequestBody UserListParamBody paramBody, BindingResult result) {
        return success(null, userService.pageUserInfo(paramBody.getPageIndex(),
                paramBody.getPageSize(), paramBody.getDepartmentId(), paramBody.getRoleId(), paramBody.getUsername()));
    }

    @GetMapping("get/{id}")
    public Object get(@PathVariable Integer id) {
        return success(null ,userService.getById(id));
    }


    @PostMapping("save")
    public Object save(@Valid @RequestBody User user, BindingResult result) {
        return success(userService.saveUser(user), null);
    }

    @PostMapping("remove")
    public Object remove(@RequestParam Integer id) {
        userService.removeUser(id);
        return success("移除成功", null);
    }


}
