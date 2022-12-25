package net.chenlin.dp.modules.biz.bussiness.service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
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
     * 新增
     * @param commodityEntity
     * @return
     */
	Resp<CommodityEntity> saveCommodity(CommodityEntity commodityEntity,String domain);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<CommodityEntity> getCommodityById(Long id);

    /**
     * 修改
     * @param commodityEntity
     * @return
     */
	Resp<Integer> updateCommodity(CommodityEntity commodityEntity);

    /**
     * 删除
     * @param id
     * @return
     */
	Resp batchRemove(Long[] id);

}
