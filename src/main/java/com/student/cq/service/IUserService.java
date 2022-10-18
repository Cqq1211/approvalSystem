package com.student.cq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.student.cq.entity.User;
import javax.xml.bind.ValidationException;

public interface IUserService extends IService<User> {

    /**
     * 登录
     * @param user
     */
    void login(User user) throws ValidationException;


    /**
     * 分页查询用户数据
     * @param pageIndex 页码
     * @param pageSize 页大小
     * @param username 用于查询的用户名
     * @return
     */
    IPage pageUserInfo(Integer pageIndex, Integer pageSize, String username);


    /**
     * 保存用户信息
     * @param user
     */
    void saveUser(User user);

    /**
     *
     * @param id
     */
    void removeUser(Integer id);
}
