package net.chenlin.dp.modules.biz.bussiness.dao;

import org.apache.ibatis.annotations.Mapper;

import net.chenlin.dp.modules.biz.bussiness.entity.YyGroupMsgDayEntity;
import net.chenlin.dp.common.mapper.TkBaseMapper;

/**
 * 运营-每日群消息数量统计
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface YyGroupMsgDayMapper extends TkBaseMapper<YyGroupMsgDayEntity> {
	
}
