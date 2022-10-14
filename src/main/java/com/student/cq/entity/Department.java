package com.student.cq.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 部门实体类
 */
@Data
public class Department implements Serializable {

    @NotNull(message = "必须传递部门ID")
    private Integer id;
    @NotBlank(message = "必须传递部门名称")
    private String name;

}
