package com.student.cq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student.cq.entity.Role;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色 Mapper
 */
@Mapper
@CacheNamespace
public interface IRoleMapper extends BaseMapper<Role> {
}
