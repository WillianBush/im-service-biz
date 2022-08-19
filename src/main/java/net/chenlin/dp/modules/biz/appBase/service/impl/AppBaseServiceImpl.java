package net.chenlin.dp.modules.biz.appBase.service.impl;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.appBase.dao.AppBaseMapper;
import net.chenlin.dp.modules.biz.appBase.entity.AppBaseEntity;
import net.chenlin.dp.modules.biz.appBase.service.AppBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * app基本信息
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("appBaseService")
public class AppBaseServiceImpl implements AppBaseService {

	@Autowired
    private AppBaseMapper appBaseMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<AppBaseEntity> listAppBase(Map<String, Object> params) {
		Query query = new Query(params);
		Page<AppBaseEntity> page = new Page<>(query);
		appBaseMapper.listForPage(page, query);
		return page;
	}

    /**
     * 新增
     * @param appBase
     * @return
     */
	@Override
	public R saveAppBase(AppBaseEntity appBase) {
		int count = appBaseMapper.save(appBase);
		return CommonUtils.msg(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public R getAppBaseById(Long id) {
		AppBaseEntity appBase = appBaseMapper.getObjectById(id);
		return CommonUtils.msg(appBase);
	}

    /**
     * 修改
     * @param appBase
     * @return
     */
	@Override
	public R updateAppBase(AppBaseEntity appBase) {
		int count = appBaseMapper.update(appBase);
		return CommonUtils.msg(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public R batchRemove(Long[] id) {
		int count = appBaseMapper.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
