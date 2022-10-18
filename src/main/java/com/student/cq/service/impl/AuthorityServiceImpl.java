package com.student.cq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.student.cq.entity.Authority;
import com.student.cq.mapper.IAuthorityMapper;
import com.student.cq.service.IAuthorityService;
import org.springframework.stereotype.Service;

/**
 * 权限字典 Service 实现类
 */
@Service
public class AuthorityServiceImpl extends ServiceImpl<IAuthorityMapper, Authority> implements IAuthorityService {
}
