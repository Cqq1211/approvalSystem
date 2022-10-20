package com.student.cq.controller;

import com.alibaba.fastjson.JSONObject;
import com.student.cq.entity.User;
import com.student.cq.service.IUserService;
import com.student.cq.utils.SecurityUtils;
import com.student.cq.utils.TokenUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@RestController
public class IndexController extends BaseController {

    @PostMapping("test")
    public Object test(@RequestParam String param) {
        return success("hello world,这是我第一个SpringBoot项目，我接收到一个参数是：" + param, null);
    }

    @Resource
    IUserService userService;

    @Data
    static class LoginParam{
        @NotBlank(message = "用户名不能为空")
        private String username;
        @NotBlank(message = "密码不能为空")
        private String password;
    }


    /**
     * 登录
     * @param param
     * @return
     */
    @PostMapping("login")
    public Object login(@Valid @RequestBody LoginParam param, BindingResult result) throws ValidationException {
        User user = new User(param.getUsername(), param.getPassword());
        userService.login(user);
        JSONObject value = new JSONObject();
        Date issuedTime = new Date();
        Date expiresTime = new Date(issuedTime.getTime() + 1000 * 60 * 60 * 72);  //过期时间为72个小时
        value.put("token", TokenUtils.generate(user.getId(), issuedTime, expiresTime));
        return success("登录成功", value);
    }
}
