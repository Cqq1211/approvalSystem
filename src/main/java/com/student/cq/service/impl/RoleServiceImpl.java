package com.student.cq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.student.cq.entity.Role;
import com.student.cq.exception.ServiceValidationException;
import com.student.cq.mapper.IRoleMapper;
import com.student.cq.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<IRoleMapper, Role> implements IRoleService {

    @Override
    public List<Role> listRole() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.gt("id", 1);
        return list(queryWrapper);
    }

    @Override
    public boolean saveRole(Role role) {
        //判断角色名称是否已存在

        if (role.getId() == 0) {
            //执行新增
            if (!save(role)) {
                throw new ServiceValidationException("保存失败", 402);
            }
        } else {
            //执行修改
            if (!updateById(role)) {
                throw new ServiceValidationException("传递的角色ID不存在，请检查", 402);
            }
        }
//        if (!saveOrUpdate(role)) {
//            throw new ServiceValidationException("保存失败", 402);
//        }
        return true;
    }

    @Override
    public boolean removeRole(Integer id) {
        if (!removeById(id)) {
            throw new ServiceValidationException("传递的角色ID不存在，请检查", 402);
        }
        return false;
    }


}
