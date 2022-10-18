package com.student.cq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.student.cq.entity.LeaveApply;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 请假申请 Service
 */
public interface ILeaveApplyService extends IService<LeaveApply> {


    IPage<Map<String, Object>> pageLeaveApplyInfo(Integer pageIndex, Integer pageSize, String username);

    /**
     * 提交请假
     * @param typeId
     * @param starttime
     * @param endtime
     * @param reason
     */
    void submitApply(Integer userId, Byte typeId, LocalDateTime starttime, LocalDateTime endtime, String reason);


    /**
     * 撤回申请
     * @param userId
     * @param applyId
     */
    void callbackApply(Integer userId, Integer applyId);

}
