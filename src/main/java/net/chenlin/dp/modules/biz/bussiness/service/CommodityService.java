package net.chenlin.dp.modules.biz.bussiness.service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.bussiness.entity.CommodityCategoryEntity;
import net.chenlin.dp.modules.biz.bussiness.entity.CommodityEntity;

import java.util.Map;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface CommodityService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<CommodityEntity> listPageForCommodity(Map<String, Object> params);

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<CommodityCategoryEntity> listPageForCommodityCategory(Map<String, Object> params);

    /**
     * 新增
     * @param commodityEntity
     * @return
     */
	Resp<CommodityEntity> saveCommodity(CommodityEntity commodityEntity,String domain);

    /**
     * 新增
     * @param commodityCategoryEntity
     * @return
     */
    Resp<CommodityCategoryEntity> saveCommodityCategory(CommodityCategoryEntity commodityCategoryEntity);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<CommodityEntity> getCommodityById(Long id);

    /**
     * 根据id查询
     * @param name
     * @return
     */
    Resp<CommodityEntity> getCommodityByName(String name);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Resp<CommodityCategoryEntity> getCommodityCategoryById(Long id);

    /**
     * 修改
     * @param commodityEntity
     * @return
     */
	Resp<Integer> updateCommodity(CommodityEntity commodityEntity, String domain);

    /**
     * 修改
     * @param commodityCategoryEntity
     * @return
     */
    Resp<Integer> updateCommodityCategory(CommodityCategoryEntity commodityCategoryEntity);

    /**
     * 删除
     * @param id
     * @return
     */
	Resp batchRemove(Long[] id);

    /**
     * 删除
     * @param id
     * @return
     */
    Resp batchRemoveCC(Long[] id);

}
