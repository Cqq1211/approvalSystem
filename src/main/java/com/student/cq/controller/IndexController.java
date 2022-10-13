package com.student.cq.controller;

import com.alibaba.fastjson.JSONObject;
import com.student.cq.entity.User;
import com.student.cq.service.IUserService;
import com.student.cq.utils.SecurityUtils;
import com.student.cq.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.xml.bind.ValidationException;
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

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("login")
    public Object login(@Valid @RequestBody User user, BindingResult result) throws ValidationException {
        //密码
        userService.login(user);
        JSONObject value = new JSONObject();
        Date issuedTime = new Date();
        Date expiresTime = new Date(issuedTime.getTime() + 1000 * 60 * 60 * 72);  //过期时间为72个小时
        value.put("token", TokenUtils.generate(user.getAccount(), issuedTime, expiresTime));
        return success("登录成功", value);
    }
}
