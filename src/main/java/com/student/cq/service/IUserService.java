package com.student.cq.service;

import com.student.cq.entity.User;
import javax.xml.bind.ValidationException;

public interface IUserService {

    /**
     * 登录
     * @param user
     */
    void login(User user) throws ValidationException;

}
