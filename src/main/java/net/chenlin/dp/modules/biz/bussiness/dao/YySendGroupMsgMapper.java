package net.chenlin.dp.modules.biz.bussiness.dao;

import org.apache.ibatis.annotations.Mapper;

import net.chenlin.dp.modules.biz.bussiness.entity.YySendGroupMsgEntity;
import net.chenlin.dp.common.mapper.TkBaseMapper;

/**
 * 运营-消息群发
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface YySendGroupMsgMapper extends TkBaseMapper<YySendGroupMsgEntity> {
	
}
