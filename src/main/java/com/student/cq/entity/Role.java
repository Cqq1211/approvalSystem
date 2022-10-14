package com.student.cq.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色实体类
 */
@Data
public class Role implements Serializable {

    private Integer id;
    private String name;

}
