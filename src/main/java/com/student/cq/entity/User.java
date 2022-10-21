package com.student.cq.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户表
 */
@Data
@Slf4j

public class User implements Serializable {

    @NotNull(message = "ID不能为空")
    @TableId
    private Integer id;
    @NotBlank(message = "用户名不能为空")
    private String username;
    private String password;
    @NotNull(message = "部门ID不能为空")
    private Integer departmentId;
    @NotNull(message = "角色ID不能为空")
    private Integer roleId;
    @NotBlank(message = "用户姓名不能为空")//单独增加的
    private String realname;
    private String sex;
    @NotNull(message = "年龄不能为空")
    @Max(value = 40,message = "年龄不能超过40岁")
    @Min(value = 22,message = "年龄不能小于22岁")
    private Short age;
    @NotBlank(message = "电话号码不能为空")
    private String mobile;
    @NotNull(message = "状态不能为空")
    private Byte state;
    private String authority;




    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password,
                Integer departmentId, Integer roleId, String realname, String sex, Short age,
                String mobile, Byte state, String authority) {
        this.username = username;
        this.password = password;
        this.departmentId = departmentId;
        this.roleId = roleId;
        this.realname = realname;
        this.sex = sex;
        this.age = age;
        this.mobile = mobile;
        this.state = state;
        this.authority = authority;
    }

    public User(Integer id, String username, String password,
                Integer departmentId, Integer roleId, String realname, String sex, Short age, String mobile, Byte state, String authority) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.departmentId = departmentId;
        this.roleId = roleId;
        this.realname = realname;
        this.sex = sex;
        this.age = age;
        this.mobile = mobile;
        this.state = state;
        this.authority = authority;
    }



}
