package net.chenlin.dp.modules.biz.appResigned.service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.appBase.entity.AppBaseEntity;
import net.chenlin.dp.modules.biz.appResigned.entity.AppResignedEntity;

import java.util.Map;

/**
 * 重签后的app信息
 * @author wang<fangyuan.co@outlook.com>
 */
public interface AppResignedService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<AppResignedEntity> listAppResigned(Map<String, Object> params);

    /**
     * 新增
     * @param appResigned
     * @return
     */
	R saveAppResigned(AppResignedEntity appResigned);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	R getAppResignedById(Long id);

    /**
     * 修改
     * @param appResigned
     * @return
     */
	R updateAppResigned(AppResignedEntity appResigned);

    /**
     * 删除
     * @param id
     * @return
     */
	R batchRemove(Long[] id);

    AppResignedEntity getLastResigned(String appBaseName);

    void increaseDownloadTimesIOS(AppResignedEntity appResigned);

    void increaseDownloadTimesAndroid(AppResignedEntity appResigned);

    /**
     * 根据name查询
     * @param appName
     * @return
     */
     AppResignedEntity getAppResignedByAppName(String appName);
}
