package net.chenlin.dp.modules.biz.appVersion.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.appVersion.entity.AppVersionEntity;

/**
 * app版本升级
 * @author wang<fangyuan.co@outlook.com>
 */
public interface AppVersionService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<AppVersionEntity> listAppVersion(Map<String, Object> params);

    /**
     * 新增
     * @param appVersion
     * @return
     */
	Resp<AppVersionEntity> saveAppVersion(AppVersionEntity appVersion);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<AppVersionEntity> getAppVersionById(Long id);

    /**
     * 修改
     * @param appVersion
     * @return
     */
	Resp<Integer> updateAppVersion(AppVersionEntity appVersion);

    /**
     * 删除
     * @param ids
     * @return
     */
    Resp<Integer> batchRemove(Long[] ids);

    AppVersionEntity selectByUniqueKey(String version,Integer siteId,String os ,String appId);

    Resp<AppVersionEntity> getAppVersionByOSAndAppName(String os, String appName);
}
