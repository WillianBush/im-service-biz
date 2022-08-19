package net.chenlin.dp.modules.biz.server.dao;

import net.chenlin.dp.common.mapper.TkBaseMapper;
import net.chenlin.dp.modules.biz.server.entity.ServerEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 服务器基本信息
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface ServerMapper extends TkBaseMapper<ServerEntity> {
	
}
