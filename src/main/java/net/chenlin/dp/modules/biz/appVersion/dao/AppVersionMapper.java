package net.chenlin.dp.modules.biz.appVersion.dao;

import org.apache.ibatis.annotations.Mapper;

import net.chenlin.dp.modules.biz.appVersion.entity.AppVersionEntity;
import net.chenlin.dp.common.mapper.TkBaseMapper;

/**
 * app版本升级
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface AppVersionMapper extends TkBaseMapper<AppVersionEntity> {

    AppVersionEntity getObjectByOSAndAppName(String os, String appName);
}
