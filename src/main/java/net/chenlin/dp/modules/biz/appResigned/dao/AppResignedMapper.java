package net.chenlin.dp.modules.biz.appResigned.dao;

import net.chenlin.dp.common.mapper.TkBaseMapper;
import net.chenlin.dp.modules.biz.appResigned.entity.AppResignedEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 重签后的app信息
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface AppResignedMapper extends TkBaseMapper<AppResignedEntity> {
    AppResignedEntity getLastResigned( @Param("appName") String appName);
    int increaseDownloadTimes( AppResignedEntity appResigned);
}
