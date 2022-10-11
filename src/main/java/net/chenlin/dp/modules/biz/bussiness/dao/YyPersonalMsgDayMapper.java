package net.chenlin.dp.modules.biz.bussiness.dao;

import org.apache.ibatis.annotations.Mapper;

import net.chenlin.dp.modules.biz.bussiness.entity.YyPersonalMsgDayEntity;
import net.chenlin.dp.common.mapper.TkBaseMapper;

/**
 * 运营-每次私发消息统计
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface YyPersonalMsgDayMapper extends TkBaseMapper<YyPersonalMsgDayEntity> {
	
}
