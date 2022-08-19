package net.chenlin.dp.modules.biz.appBase.dao;

import net.chenlin.dp.common.mapper.TkBaseMapper;
import net.chenlin.dp.modules.biz.appBase.entity.AppBaseEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * app基本信息
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface AppBaseMapper extends TkBaseMapper<AppBaseEntity> {
	
}
