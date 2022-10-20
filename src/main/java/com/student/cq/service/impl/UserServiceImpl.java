package com.student.cq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.student.cq.entity.User;
import com.student.cq.exception.ServiceValidationException;
import com.student.cq.mapper.IUserMapper;
import com.student.cq.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("userService")
public class UserServiceImpl extends ServiceImpl<IUserMapper, User> implements IUserService {

    @Resource
    IUserMapper userMapper;

    @Override
    public void login(User user) throws ServiceValidationException {
        //1.判断user是否存在
        QueryWrapper userQueryWrapper = new QueryWrapper();
        userQueryWrapper.eq("username", user.getUsername());
        if (count(userQueryWrapper) == 0) {
            //未找到该用户
            throw new ServiceValidationException("登录失败，账号不存在，请前往注册", 401);
        }
        //2.密码判断
        QueryWrapper<User> loginUserQueryWrapper = new QueryWrapper();
        loginUserQueryWrapper.select("id");
        loginUserQueryWrapper.eq("username", user.getUsername());
        loginUserQueryWrapper.inSql("password", "MD5('" + user.getUsername() + user.getPassword() + "')");
        Integer loginUserId = getObj(loginUserQueryWrapper, o -> Integer.parseInt(o.toString()));
        if (loginUserId == null) {
            //检测到用户，但是密码错误
            throw new ServiceValidationException("登录失败，账号或密码错误", 401);
        }
        user.setId(loginUserId);
    }

    @Override
    public IPage pageUserInfo(Integer pageIndex, Integer pageSize, Integer departmentId, Integer roleId, String username) {
        Page page = new Page(pageIndex, pageSize);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("user.id", "username", "realname", "sex", "age", "mobile");
        userQueryWrapper.gt("user.id", 1);
        if (departmentId != null && departmentId != 0) {
            userQueryWrapper.eq("department_id", departmentId);
        }
        if (roleId != null && roleId != 0) {
            userQueryWrapper.eq("role_id", roleId);
        }
        if (username != null && !username.equals("")) {

            userQueryWrapper.and(warpper -> warpper.like("username", username)
                    .or()
                    .like("realname", username));
        }
        return userMapper.selectUserJoinDetAndRolPage(page, userQueryWrapper);
    }

    @Override
    public String saveUser(User user) {
        //1.先判断用户名是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", user.getUsername())
                .ne("id", user.getId());
        if (count(queryWrapper) > 0) {
            throw new ServiceValidationException("用户名已存在", 402);
        }
        if (user.getId() == 0) {
            //新增
            String defaultPassword = "123456";  //默认密码
            user.setPassword(defaultPassword);
            if (userMapper.insertUser(user) == 0) {
                throw new ServiceValidationException("新增失败", 402);
            }
            log.info("新增后用户的ID：" + user.getId());
            return "新增成功，用户初始密码为：" + defaultPassword;
        } else {
            //修改
            if (!updateById(user)) {
                throw new ServiceValidationException("修改失败，可能ID不存在", 402);
            }
            return "修改成功";
        }
    }

    @Override
    public void removeUser(Integer id) {
        if (!removeById(id)) {
            throw new ServiceValidationException("移除失败，可能ID不存在", 402);
        }
    }


}
