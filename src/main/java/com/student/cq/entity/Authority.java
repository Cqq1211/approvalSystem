package com.student.cq.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * 权限字典
 */
@Data
public class Authority implements Serializable {

    private String code;
    private String name;

}
