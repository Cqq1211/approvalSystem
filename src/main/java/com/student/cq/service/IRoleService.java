package com.student.cq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.student.cq.entity.Role;

import java.util.List;

/**
 * 角色 Service
 */
public interface IRoleService extends IService<Role> {

    /**
     * 获取权限列表
     * @return
     */
    List<Role> listRole();

}
