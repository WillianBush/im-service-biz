package net.chenlin.dp.modules.biz.appDomain.service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.appDomain.entity.AppDomainEntity;

import java.util.Map;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface AppDomainService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<AppDomainEntity> listAppDomain(Map<String, Object> params);

    /**
     * 新增
     * @param appDomain
     * @return
     */
	R saveAppDomain(AppDomainEntity appDomain);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	R getAppDomainById(Long id);

    /**
     * 修改
     * @param appDomain
     * @return
     */
	R updateAppDomain(AppDomainEntity appDomain);

    /**
     * 删除
     * @param id
     * @return
     */
	R batchRemove(Long[] id);
	
}
