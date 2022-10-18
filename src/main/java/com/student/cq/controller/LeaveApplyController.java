package com.student.cq.controller;

import com.student.cq.service.ILeaveApplyService;
import com.student.cq.service.ILeaveApplyStateService;
import com.student.cq.service.ILeaveApplyTypeService;
import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 请假申请 Controller
 */
@RestController
@RequestMapping("admin/leaveApply")
public class LeaveApplyController extends BaseController {

    @Resource
    ILeaveApplyService leaveApplyService;
    @Resource
    ILeaveApplyTypeService leaveApplyTypeService;
    @Resource
    ILeaveApplyStateService leaveApplyStateService;

    @Data
    static class LeaveApplyParamBody{
        @NotNull(message = "请传递页码：pageIndex")
        private Integer pageIndex;
        @Max(value = 12, message = "页大小不能超过12")
        @NotNull(message = "请传递页大小：pageSize")
        private Integer pageSize;
        private String username;
    }

    @PostMapping
    public Object index(@Valid @RequestBody LeaveApplyParamBody paramBody, BindingResult result) {
        return success(null, leaveApplyService.pageLeaveApplyInfo(paramBody.getPageIndex(),
                paramBody.getPageSize(), paramBody.getUsername()));
    }

    @GetMapping("types")
    public Object types() {
        return success(null, leaveApplyTypeService.list());
    }

    @GetMapping("states")
    public Object states() {
        return success(null, leaveApplyStateService.list());
    }

    @Data
    static class LeaveApplySubmitParam {
        @NotNull(message = "请传递请假类型")
        private Byte typeId;
        @NotNull(message = "请传递请假开始时间")
        private LocalDateTime starttime;
        @NotNull(message = "请传递请假结束时间")
        private LocalDateTime endtime;
        @NotBlank(message = "请传递请假原因")
        private String reason;  //请假原因
    }

    /**
     * 提交申请
     * @param param
     * @param result
     * @return
     */
    @PostMapping("submit")
    public Object submit(@Valid @RequestBody LeaveApplySubmitParam param, BindingResult result) {
        Integer userId = Integer.parseInt(request.getAttribute("sign").toString()); //从请求属性里面获取标识，然后转换为用户ID
        leaveApplyService.submitApply(userId, param.getTypeId(), param.getStarttime(), param.getEndtime(), param.getReason());
        return success("提交成功", null);
    }

    /**
     * 撤回申请
     * @param id
     * @return
     */
    @PostMapping("callback")
    public Object callback(@RequestParam Integer id) {
        Integer userId = Integer.parseInt(request.getAttribute("sign").toString());
        leaveApplyService.callbackApply(userId, id);
        return success("撤回成功", null);
    }



}
