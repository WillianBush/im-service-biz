package net.chenlin.dp.modules.biz.yy_group_notice.dao;

import org.apache.ibatis.annotations.Mapper;

import net.chenlin.dp.modules.biz.yy_group_notice.entity.YyGroupNoticeEntity;
import net.chenlin.dp.common.mapper.TkBaseMapper;

/**
 * 运营-消息群发
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface YyGroupNoticeMapper extends TkBaseMapper<YyGroupNoticeEntity> {
	
}
