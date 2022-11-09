package net.chenlin.dp.modules.biz.bussiness.dao;

import org.apache.ibatis.annotations.Mapper;

import net.chenlin.dp.modules.biz.bussiness.entity.YyIpListEntity;
import net.chenlin.dp.common.mapper.TkBaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface YyIpListMapper extends TkBaseMapper<YyIpListEntity> {
    YyIpListEntity getObjectByIp(@Param("ipAddress") String ipAddress, @Param("type")Integer type, @Param("org_id")int org_id);
}
