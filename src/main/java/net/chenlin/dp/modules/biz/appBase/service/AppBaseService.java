package net.chenlin.dp.modules.biz.appBase.service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.appBase.entity.AppBaseEntity;

import java.util.Map;

/**
 * app基本信息
 * @author wang<fangyuan.co@outlook.com>
 */
public interface AppBaseService {

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    Page<AppBaseEntity> listAppBase(Map<String, Object> params);

    /**
     * 新增
     *
     * @param appBase
     * @return
     */
    R saveAppBase(AppBaseEntity appBase);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    R getAppBaseById(Long id);

    /**
     * 修改
     *
     * @param appBase
     * @return
     */
    R updateAppBase(AppBaseEntity appBase);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    R batchRemove(Long[] id);
}

