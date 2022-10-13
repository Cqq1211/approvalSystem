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
    @NotBlank(message = "账号不能为空")
    private String account;
    @NotBlank(message = "密码不能为空")
    private String password;
    private Integer jiFen;   //积分

    public User() {
    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public void setAccount(String account) {
        this.account = account;
        log.debug("执行User类的setAccount函数了，传进来的值是：" + account);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", jiFen=" + jiFen +
                '}';
    }
}
