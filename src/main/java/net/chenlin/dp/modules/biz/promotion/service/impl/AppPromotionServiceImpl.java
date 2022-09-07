package net.chenlin.dp.modules.biz.promotion.service.impl;

import lombok.AllArgsConstructor;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.common.utils.DateUtils;
import net.chenlin.dp.common.utils.GenerateRandomCode;
import net.chenlin.dp.modules.biz.appBase.dao.AppBaseMapper;
import net.chenlin.dp.modules.biz.appBase.entity.AppBaseEntity;
import net.chenlin.dp.modules.biz.appDomain.dao.AppDomainMapper;
import net.chenlin.dp.modules.biz.appDomain.entity.AppDomainEntity;
import net.chenlin.dp.modules.biz.domain.dao.DomainMapper;
import net.chenlin.dp.modules.biz.domain.entity.DomainEnum;
import net.chenlin.dp.modules.biz.promotion.dao.AppPromotionMapper;
import net.chenlin.dp.modules.biz.promotion.entity.AppPromotionEntity;
import net.chenlin.dp.modules.biz.promotion.service.AppPromotionService;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 域名URL
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("appPromotionService")
@AllArgsConstructor
public class AppPromotionServiceImpl implements AppPromotionService {

    private final AppPromotionMapper appPromotionMapper;

	private final AppDomainMapper appDomainMapper;

	private final AppBaseMapper appBaseMapper;

	private final DomainMapper domainMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<AppPromotionEntity> listAppPromotion(Map<String, Object> params) {
		Query query = new Query(params);
		Page<AppPromotionEntity> page = new Page<>(query);
		appPromotionMapper.listForPage(page, query);
		return page;
	}

    /**
     * 新增
     * @param appPromotion
     * @return
     */
	@Override
	public R saveAppPromotion(AppPromotionEntity appPromotion) {
		int count = appPromotionMapper.save(appPromotion);
		return CommonUtils.msg(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public R getAppPromotionById(Long id) {
		AppPromotionEntity appPromotion = appPromotionMapper.getObjectById(id);
		return CommonUtils.msg(appPromotion);
	}

    /**
     * 修改
     * @param appPromotion
     * @return
     */
	@Override
	public R updateAppPromotion(AppPromotionEntity appPromotion) {
		int count = appPromotionMapper.update(appPromotion);
		return CommonUtils.msg(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public R batchRemove(Long[] id) {
		int count = appPromotionMapper.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

	@Override
	public R applyUrl(String appName, SysUserEntity user) {

		// 获取可以的推广域名
		List<AppDomainEntity> appDomainEntitiesEnable = appDomainMapper.getDomainsEnabledByBaseAppName(DomainEnum.AdvertiseDomain.getCode(), appName,4);
		// 随机生成6位字符串
		List<String> randomCodes = GenerateRandomCode.getRandomCode(6,appDomainEntitiesEnable.size());
		for (int i = 0; i < appDomainEntitiesEnable.size(); i++) {
			AppDomainEntity appDomainEntity = appDomainEntitiesEnable.get(i);
			AppPromotionEntity appPromotion = new AppPromotionEntity();
			appPromotion.setPromotionDomain(appDomainEntity.getDomainName());
			appPromotion.setPromotionUrl(randomCodes.get(i));
			appPromotion.setAppName(appDomainEntity.getAppBaseName());
			AppBaseEntity  appBase = appBaseMapper.selectOne(AppBaseEntity.builder().appName(appName).build());
			appPromotion.setAppBaseId(appBase.getId());
			LocalDateTime now = LocalDateTime.now();
			appPromotion.setAppResignedD(appDomainEntity.getAppResignedId());
			appPromotion.setCreateTime(DateUtils.LocalDateTimeToDate(now));
			appPromotion.setExpireTime(DateUtils.LocalDateTimeToDate(now.plusHours(48)));
			this.saveAppPromotion(appPromotion);
		}
		return R.ok();
	}
}
