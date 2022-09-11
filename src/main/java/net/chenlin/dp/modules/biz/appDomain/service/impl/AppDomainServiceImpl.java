package net.chenlin.dp.modules.biz.appDomain.service.impl;

import lombok.AllArgsConstructor;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.appDomain.dao.AppDomainMapper;
import net.chenlin.dp.modules.biz.appDomain.entity.AppDomainEntity;
import net.chenlin.dp.modules.biz.appDomain.service.AppDomainService;
import net.chenlin.dp.modules.biz.appResigned.entity.AppResignedEntity;
import net.chenlin.dp.modules.biz.appResigned.service.AppResignedService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("appDomainService")
@AllArgsConstructor
public class AppDomainServiceImpl implements AppDomainService {

    private AppDomainMapper appDomainMapper;

//	private AppBaseService appBaseService;

	private AppResignedService appResignedService;
    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<AppDomainEntity> listAppDomain(Map<String, Object> params) {
		Query query = new Query(params);
		Page<AppDomainEntity> page = new Page<>(query);
		appDomainMapper.listForPage(page, query);
		return page;
	}

    /**
     * 新增
     * @param appDomain
     * @return
     */
	@Override
	public R saveAppDomain(AppDomainEntity appDomain) {
		int count = appDomainMapper.save(appDomain);
		return CommonUtils.msg(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public R getAppDomainById(Long id) {
		AppDomainEntity appDomain = appDomainMapper.getObjectById(id);
		return CommonUtils.msg(appDomain);
	}

	/**
     * 修改
     * @param appDomain
     * @return
     */
	@Override
	public R updateAppDomain(AppDomainEntity appDomain) {
		int count = appDomainMapper.update(appDomain);
		return CommonUtils.msg(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public R batchRemove(Long[] id) {
		int count = appDomainMapper.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

	@Override
	public AppResignedEntity getAppResignedByAppName(String appName) {
		return appResignedService.getAppResignedByAppName(appName);
	}

}
