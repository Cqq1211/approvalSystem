package com.student.cq.service.impl;

import com.student.cq.entity.User;
import com.student.cq.exception.ServiceValidationException;
import com.student.cq.mapper.IUserMapper;
import com.student.cq.service.IUserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    IUserMapper userMapper;


    @Override
    public void login(User user) throws ServiceValidationException {
        User loginUser = userMapper.selectByUsername(user.getUsername());
        if (loginUser == null) {
            //未找到该用户
            throw new ServiceValidationException("登录失败，账号不存在，请前往注册", 401);
        }
        if (!loginUser.getPassword().equals(user.getPassword())) {
            //检测到用户，但是密码错误
            throw new ServiceValidationException("登录失败，账号或密码错误", 401);
        }
    }


}
