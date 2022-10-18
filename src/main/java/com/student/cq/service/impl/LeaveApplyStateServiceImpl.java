package com.student.cq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.student.cq.entity.LeaveApplyState;
import com.student.cq.mapper.ILeaveApplyStateMapper;
import com.student.cq.service.ILeaveApplyStateService;
import org.springframework.stereotype.Service;

/**
 * 请假申请状态 Service实现类
 */
@Service
public class LeaveApplyStateServiceImpl extends ServiceImpl<ILeaveApplyStateMapper, LeaveApplyState>
        implements ILeaveApplyStateService {
}
