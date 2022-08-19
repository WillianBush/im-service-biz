package net.chenlin.dp.modules.api.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.exception.RRException;
import net.chenlin.dp.modules.api.entity.Constants;
import net.chenlin.dp.modules.api.service.ForwardService;
import net.chenlin.dp.modules.biz.appDomain.dao.AppDomainMapper;
import net.chenlin.dp.modules.biz.appDomain.entity.AppDomainEntity;
import net.chenlin.dp.modules.biz.domain.entity.DomainEnum;
import net.chenlin.dp.modules.biz.promotion.dao.AppPromotionMapper;
import net.chenlin.dp.modules.biz.promotion.entity.AppPromotionEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ForwardServiceImpl implements ForwardService {

    private final AppDomainMapper appDomainMapper;

    private final AppPromotionMapper appPromotionMapper;

    @Override
    public String getResponseDomain(String requestDomain) throws RRException {
        AppDomainEntity appDomainEntity = new AppDomainEntity();
        appDomainEntity.setDomainName(requestDomain);
        appDomainEntity.setDomainType(DomainEnum.AdvertiseDomain.getCode());
        List<AppDomainEntity> appDomainEntities = appDomainMapper.select(appDomainEntity);
        if (appDomainEntities.isEmpty()) {
            log.error("getResponseDomain 找不到对应的域名，requestDomain：{}", requestDomain);
            return Constants.DEFAULT_DOMAIN;
        }
        AppDomainEntity appResigned = appDomainEntities.get(0);
        return getAppDomain(requestDomain, appResigned.getAppResignedId(), appDomainEntities.isEmpty());
    }

    @Override
    public String getResponseDomain(String requestDomain, String path) throws RRException {
        AppPromotionEntity appPromotion = new AppPromotionEntity();
        appPromotion.setPromotionUrl(path);
        appPromotion.setPromotionDomain(requestDomain);
        List<AppPromotionEntity> appDomainEntities= appPromotionMapper.select(appPromotion);
        if (appDomainEntities.isEmpty()){
            log.error("getResponseDomain 找不到对应的url，requestDomain：{}", appPromotion);
            return Constants.DEFAULT_DOMAIN;
        }
        AppPromotionEntity appPromotionOne = appDomainEntities.get(0);
        if (appPromotionOne.getExpireTime().before(new Date())) {
            return Constants.DEFAULT_DOMAIN;
        }
        return getAppDomain(requestDomain, appPromotionOne.getAppResignedD(), appDomainEntities.isEmpty());
    }

    private String getAppDomain(String requestDomain, Long appResignedD, boolean empty) {
//        List<AppDomainEntity> appDomainEntitiesEnable = appDomainMapper.getDomainsEnabled(DomainEnum.ServerDomain.getCode(), appResignedD);
//        if (empty) {
//            log.error("getResponseDomain 已经找不到能使用的落地页域名。。。，requestDomain：{}，AppResignedId：{}", requestDomain, appResignedD);
//            return Constants.DEFAULT_DOMAIN;
//        }
//        int domainSize = appDomainEntitiesEnable.size();
//        if (domainSize > 1) {
//            Random random = new Random();
//            AppDomainEntity resp = appDomainEntitiesEnable.get(random.nextInt(domainSize));
//            return resp.getDomainName();
//        }
        AppDomainEntity appDomainEntityEnable = appDomainMapper.getDomainsEnabledOne(DomainEnum.ServerDomain.getCode(), appResignedD);
        if (appDomainEntityEnable != null) {
            return appDomainEntityEnable.getDomainName();
        }
        log.error("getResponseDomain 已经找不到能使用的落地页域名。。。，requestDomain：{}，AppResignedId：{}", requestDomain, appResignedD);
        return Constants.DEFAULT_DOMAIN;
    }

}
