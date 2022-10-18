package com.student.cq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.student.cq.entity.Notice;
import java.util.Map;

/**
 * 公告 Service
 */
public interface INoticeService extends IService<Notice> {

    /**
     * 分页获取公告数据
     * @return
     */
    IPage<Map<String, Object>> pageNotice(Integer pageIndex, Integer pageSize, String title);


    /**
     * 保存Notice
     * @param notice
     */
    void saveNotice(Notice notice);

    /**
     * 移除公告
     * @param id 公告ID
     * @param userId 当前请求者用户的ID
     */
    void removeNotice(Integer id, Integer userId);

}
