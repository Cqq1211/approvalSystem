package com.student.cq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student.cq.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

/**
 * 公告 Mapper
 */
@Mapper
public interface INoticeMapper extends BaseMapper<Notice> {
}
