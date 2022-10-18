package com.student.cq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.student.cq.entity.LeaveApply;
import com.student.cq.exception.ServiceValidationException;
import com.student.cq.mapper.ILeaveApplyMapper;
import com.student.cq.service.ILeaveApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

/**
 * 请假申请 Service实现类
 */
@Service
public class LeaveApplyServiceImpl extends ServiceImpl<ILeaveApplyMapper, LeaveApply> implements ILeaveApplyService {

    @Resource
    ILeaveApplyMapper leaveApplyMapper;

    @Override
    public IPage<Map<String, Object>> pageLeaveApplyInfo(Integer pageIndex, Integer pageSize, String username) {
        Page page = new Page(pageIndex, pageSize);  //分页对象
        QueryWrapper<LeaveApply> queryWrapper = new QueryWrapper<>();
        if (username != null && !username.equals("")) {
            queryWrapper.and(wrapper -> wrapper.like("username", username)
                    .or()
                    .like("realname", username));
        }
        return leaveApplyMapper.pageLeaveApplyJoinUser(page, queryWrapper);
    }

    @Override
    public void submitApply(Integer userId, Byte typeId, LocalDateTime starttime, LocalDateTime endtime, String reason) {
        long starttimeStamp = starttime.toEpochSecond(ZoneOffset.of("+8"));
        long endtimeStamp = endtime.toEpochSecond(ZoneOffset.of("+8"));
        if (starttimeStamp >= endtimeStamp) {
            throw new ServiceValidationException("开始时间不能在结束时间之后", 402);
        }
        if (endtimeStamp - starttimeStamp < 30 * 60) {
            throw new ServiceValidationException("时间间隔不能小于30分钟", 402);
        }
        LeaveApply apply = new LeaveApply();
        apply.setUserId(userId);
        apply.setTypeId(typeId);
        apply.setStarttime(starttime);
        apply.setEndtime(endtime);
        apply.setReason(reason);
        apply.setStateId((byte) 1);
        if (!save(apply)) {
            throw new ServiceValidationException("请假申请失败，原因未知", 402);
        }
    }

    @Override
    public void callbackApply(Integer userId, Integer applyId) {
        QueryWrapper<LeaveApply> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", applyId)
                .eq("user_id", userId)
                .eq("state", 1);
        LeaveApply apply = new LeaveApply();
        apply.setStateId((byte) 5);
        if (!update(apply, queryWrapper)) {
            throw new ServiceValidationException("请假撤回失败，该申请已不能再撤回，如已审核中", 402);
        }
    }

}
