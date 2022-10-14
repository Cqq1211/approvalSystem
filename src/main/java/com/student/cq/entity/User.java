package com.student.cq.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户表
 */
@Data
@Slf4j
public class User implements Serializable {

    private Integer id;
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    private Integer departmentId;
    private String departmentName;  //单独增加的
    private Integer roleId;
    private String roleName;    //单独增加的
    private String realname;
    private String sex;
    private Short age;
    private String mobile;
    private Byte state;
    private String authority;



}
