package net.chenlin.dp.modules.biz.appResigned.service.impl;

import lombok.AllArgsConstructor;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.support.redis.RedisCacheManager;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.api.entity.Constants;
import net.chenlin.dp.modules.biz.appResigned.dao.AppResignedMapper;
import net.chenlin.dp.modules.biz.appResigned.entity.AppResignedEntity;
import net.chenlin.dp.modules.biz.appResigned.service.AppResignedService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 重签后的app信息
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("appResignedService")
@AllArgsConstructor
public class AppResignedServiceImpl implements AppResignedService {

    private AppResignedMapper appResignedMapper;

	private RedisCacheManager redisCacheManager;


    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<AppResignedEntity> listAppResigned(Map<String, Object> params) {
		Query query = new Query(params);
		Page<AppResignedEntity> page = new Page<>(query);
		appResignedMapper.listForPage(page, query);
		return page;
	}

    /**
     * 新增
     * @param appResigned
     * @return
     */
	@Override
	public R saveAppResigned(AppResignedEntity appResigned) {
		int count = appResignedMapper.save(appResigned);
		return CommonUtils.msg(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public R getAppResignedById(Long id) {
		AppResignedEntity appResigned = appResignedMapper.getObjectById(id);
		return CommonUtils.msg(appResigned);
	}

    /**
     * 修改
     * @param appResigned
     * @return
     */
	@Override
	public R updateAppResigned(AppResignedEntity appResigned) {
		int count = appResignedMapper.update(appResigned);
		return CommonUtils.msg(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public R batchRemove(Long[] id) {
		int count = appResignedMapper.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

	@Override
	public AppResignedEntity getLastResigned(String appBaseName) {
		return appResignedMapper.getLastResigned(appBaseName);
	}

	@Override
	public void increaseDownloadTimesIOS(AppResignedEntity appResigned) {
		String redisCache = Constants.downloadRedisCacheIos(appResigned.getAppBaseName(),appResigned.getId());
		Boolean exists = redisCacheManager.sismember(Constants.DOWNLOAD_KEYS_LIST,redisCache);
		if (!exists){
			redisCacheManager.sadd(Constants.DOWNLOAD_KEYS_LIST,redisCache);
		}
		redisCacheManager.incr(redisCache,1);
	}

	@Override
	public void increaseDownloadTimesAndroid(AppResignedEntity appResigned) {
		String redisCache = Constants.downloadRedisCacheAndroid(appResigned.getAppBaseName(),appResigned.getId());
		Boolean exists = redisCacheManager.sismember(Constants.DOWNLOAD_KEYS_LIST,redisCache);
		if (!exists){
			redisCacheManager.sadd(Constants.DOWNLOAD_KEYS_LIST,redisCache);
		}
		redisCacheManager.incr(redisCache,1);
	}
}
