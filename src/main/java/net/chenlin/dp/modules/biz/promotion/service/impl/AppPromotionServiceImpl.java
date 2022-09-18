package net.chenlin.dp.modules.biz.promotion.service.impl;

import lombok.AllArgsConstructor;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.common.utils.DateUtils;
import net.chenlin.dp.modules.biz.appBase.dao.AppBaseMapper;
import net.chenlin.dp.modules.biz.appBase.entity.AppBaseEntity;
import net.chenlin.dp.modules.biz.appDomain.dao.AppDomainMapper;
import net.chenlin.dp.modules.biz.appDomain.entity.AppDomainEntity;
import net.chenlin.dp.modules.biz.domain.dao.DomainMapper;
import net.chenlin.dp.modules.biz.domain.entity.DomainCheckEnum;
import net.chenlin.dp.modules.biz.domain.entity.DomainEnum;
import net.chenlin.dp.modules.biz.promotion.dao.AppPromotionMapper;
import net.chenlin.dp.modules.biz.promotion.entity.AppPromotionEntity;
import net.chenlin.dp.modules.biz.promotion.service.AppPromotionService;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 域名URL
 *
 * @author wang<fangyuan.co @ outlook.com>
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
     *
     * @param params
     * @return
     */
    @Override
    public Page<AppPromotionEntity> listAppPromotion(Map<String, Object> params) {
        Query query = new Query(params);
        Page<AppPromotionEntity> page = new Page<>(query);
        appPromotionMapper.listForPage(page, query);
//        TODO @shenghong 垃圾代码
//        if (null != params.get("isBlocked")) {
//            Page<AppPromotionEntity> pageMore = page;
//			List<AppPromotionEntity> list = new ArrayList<>();
//            page.getRows().forEach( it -> {
//                        if (domainMapper.getObjectByName(it.getPromotionDomain()).getIsBlocked().toString().equals(params.get("isBlocked"))) {
//							list.add(it);
//                        }
//                    }
//            );
//			pageMore.setRows(list);
//            pageMore.setTotal(list.size());
//            pageMore.setTotalPages((list.size() / page.getPageSize() + 1));
//            return pageMore;
//        } else
            return page;
    }

    /**
     * 新增
     *
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
     *
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
     *
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
     *
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
        List<AppDomainEntity> appDomainEntitiesEnable = appDomainMapper.getDomainsEnabledByBaseAppName(DomainEnum.AdvertiseDomain.getCode(), appName, 4,DomainCheckEnum.NormalDomain.getCode(),1);
        // 随机生成6位字符串
//		List<String> randomCodes = GenerateRandomCode.getRandomCode(6,appDomainEntitiesEnable.size());
        for (int i = 0; i < appDomainEntitiesEnable.size(); i++) {
            AppDomainEntity appDomainEntity = appDomainEntitiesEnable.get(i);
            AppPromotionEntity appPromotion = new AppPromotionEntity();
            appPromotion.setPromotionDomain(appDomainEntity.getDomainName());
//			appPromotion.setPromotionUrl(randomCodes.get(i));
            appPromotion.setPromotionUrl("");
            appPromotion.setAppName(appDomainEntity.getAppBaseName());
            AppBaseEntity appBase = appBaseMapper.selectOne(AppBaseEntity.builder().appName(appName).build());
            appPromotion.setAppBaseId(appBase.getId());
            LocalDateTime now = LocalDateTime.now();
            appPromotion.setAppResignedD(appDomainEntity.getAppResignedId());
            appPromotion.setCreateTime(DateUtils.LocalDateTimeToDate(now));
            appPromotion.setExpireTime(DateUtils.LocalDateTimeToDate(now.plusHours(48)));
            this.saveAppPromotion(appPromotion);
        }
        return R.ok();
    }

    @Override
    public R applyUrl(String appName, SysUserEntity user, Integer advertiseDomain) {
        return applyUrl( appName,  user,  advertiseDomain,  DomainCheckEnum.NormalDomain.getCode());
    }

    @Override
    public R applyUrl(String appName, SysUserEntity user, Integer advertiseDomain, Integer qqChecked) {
        return applyUrl( appName,  user,  advertiseDomain,  DomainCheckEnum.NormalDomain.getCode(),1);
    }

    @Override
    public R applyUrl(String appName, SysUserEntity user, Integer advertiseDomain, Integer qqChecked, Integer shortLink) {
        // 获取可以的推广域名
        AppPromotionEntity appPromotionExist = new AppPromotionEntity();
        appPromotionExist.setAppName(appName);
        List<AppPromotionEntity> appPromotionExists = appPromotionMapper.select(appPromotionExist);
        List<AppDomainEntity> appDomainEntitiesEnable;
        if (appPromotionExists.isEmpty()) {
            appDomainEntitiesEnable = appDomainMapper.getDomainsEnabledByBaseAppName(DomainEnum.AdvertiseDomain.getCode(), appName, advertiseDomain,qqChecked,shortLink);
        } else {
            List<String> domainUsedNames = appPromotionExists.stream().map(AppPromotionEntity::getPromotionDomain).collect(Collectors.toList());
            appDomainEntitiesEnable = appDomainMapper.getDomainsEnabledByBaseAppNameNoUse(DomainEnum.AdvertiseDomain.getCode(), appName, advertiseDomain,qqChecked, domainUsedNames,shortLink);
            if (appDomainEntitiesEnable.isEmpty()) {
                return R.error(appName + "已经没有短域名,请尽快补充");
            }
        }
        // 随机生成6位字符串
//		List<String> randomCodes = GenerateRandomCode.getRandomCode(6,appDomainEntitiesEnable.size());
        for (int i = 0; i < appDomainEntitiesEnable.size(); i++) {
            AppDomainEntity appDomainEntity = appDomainEntitiesEnable.get(i);
            AppPromotionEntity appPromotion = new AppPromotionEntity();
            appPromotion.setPromotionDomain(appDomainEntity.getDomainName());
//			appPromotion.setPromotionUrl(randomCodes.get(i));
            appPromotion.setPromotionUrl("");
            appPromotion.setAppName(appDomainEntity.getAppBaseName());
            AppBaseEntity appBase = appBaseMapper.selectOne(AppBaseEntity.builder().appName(appName).build());
            appPromotion.setAppBaseId(appBase.getId());
            LocalDateTime now = LocalDateTime.now();
            appPromotion.setAppResignedD(appDomainEntity.getAppResignedId());
            appPromotion.setCreateTime(DateUtils.LocalDateTimeToDate(now));
            appPromotion.setExpireTime(DateUtils.LocalDateTimeToDate(now.plusDays(30)));
            this.saveAppPromotion(appPromotion);
        }
        return R.ok("成功获取短域名 " + appDomainEntitiesEnable.size() + " 个");
    }
}
