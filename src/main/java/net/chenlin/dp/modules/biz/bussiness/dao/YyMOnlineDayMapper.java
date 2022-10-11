package net.chenlin.dp.modules.biz.bussiness.dao;

import org.apache.ibatis.annotations.Mapper;

import net.chenlin.dp.modules.biz.bussiness.entity.YyMOnlineDayEntity;
import net.chenlin.dp.common.mapper.TkBaseMapper;

/**
 * 运营-每日用户在线统计
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface YyMOnlineDayMapper extends TkBaseMapper<YyMOnlineDayEntity> {
	
}
