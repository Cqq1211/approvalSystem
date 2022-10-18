package com.student.cq.controller;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.student.cq.entity.Notice;
import com.student.cq.service.INoticeService;
import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 公告 Controller
 */
@RestController
@RequestMapping("admin/notice")
public class NoticeController extends BaseController {

    @Resource
    INoticeService noticeService;

    @Data
    static class NoticeParamBody{
        @NotNull(message = "请传递页码：pageIndex")
        private Integer pageIndex;
        @Max(value = 12, message = "页大小不能超过12")
        @NotNull(message = "请传递页大小：pageSize")
        private Integer pageSize;
        private String title;
    }

    @PostMapping
    public Object index(@Valid @RequestBody NoticeParamBody paramBody, BindingResult result) {
        return success(null, noticeService.pageNotice(paramBody.getPageIndex(),
                paramBody.getPageSize(), paramBody.getTitle()));
    }

    /**
     * 根据ID获取公告
     * @param id
     * @return
     */
    @GetMapping("get/{id}")
    public Object get(@PathVariable Integer id) {
        return success(null, noticeService.getById(id));
    }

    @Data
    static class NoticeSaveParamBody{
        @NotNull(message = "ID必须传递")
        @TableId(value = "id", type = IdType.AUTO)
        private Integer id;
        @NotBlank(message = "必须传递公告标题")
        private String title;
        @NotBlank(message = "必须传递公告内容")
        private String content;
    }

    /**
     * 保存公告
     * @param paramBody
     * @param result
     * @return
     */
    @PostMapping("save")
    public Object save(@Valid @RequestBody NoticeSaveParamBody paramBody, BindingResult result) {
        Integer userId = Integer.parseInt(request.getAttribute("sign").toString()); //从请求属性里面获取标识，然后转换为用户ID
        noticeService.saveNotice(new Notice(paramBody.getId(), userId, paramBody.getTitle(), paramBody.getContent()));
        return success("保存成功", null);
    }

    /**
     * 移除公告
     * @param id
     * @return
     */
    @PostMapping("remove")
    public Object remove(@RequestParam Integer id) {
        Integer userId = Integer.parseInt(request.getAttribute("sign").toString());
        noticeService.removeNotice(id, userId);
        return success("移除成功", null);
    }

}
