package net.chenlin.dp.modules.biz.promotion.service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.promotion.entity.AppPromotionEntity;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;

import java.util.Map;

/**
 * 域名URL
 * @author wang<fangyuan.co@outlook.com>
 */
public interface AppPromotionService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<AppPromotionEntity> listAppPromotion(Map<String, Object> params);

    /**
     * 新增
     * @param appPromotion
     * @return
     */
	R saveAppPromotion(AppPromotionEntity appPromotion);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	R getAppPromotionById(Long id);

    /**
     * 修改
     * @param appPromotion
     * @return
     */
	R updateAppPromotion(AppPromotionEntity appPromotion);

    /**
     * 删除
     * @param id
     * @return
     */
	R batchRemove(Long[] id);

    R applyUrl(String appName, SysUserEntity user);

    R applyUrl(String appName, SysUserEntity user,Integer advertiseDomain);
}
