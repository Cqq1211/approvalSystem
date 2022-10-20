package com.student.cq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.student.cq.entity.LeaveApplyType;
import com.student.cq.mapper.ILeaveApplyTypeMapper;
import com.student.cq.service.ILeaveApplyTypeService;
import org.springframework.stereotype.Service;

/**
 * 请假申请类型 Service实现类
 */
@Service
public class LeaveApplyTypeServiceImpl extends ServiceImpl<ILeaveApplyTypeMapper, LeaveApplyType>
        implements ILeaveApplyTypeService {
}
