package net.chenlin.dp.modules.biz.bussiness.service.impl;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.bussiness.dao.CommodityMapper;
import net.chenlin.dp.modules.biz.bussiness.entity.CommodityEntity;
import net.chenlin.dp.modules.sys.dao.DomainsMapper;
import net.chenlin.dp.modules.sys.entity.SysLogEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public class CommodityServiceImpl {

    private CommodityMapper commodityMapper;
    private DomainsMapper domainsMapper;
    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    Page<CommodityEntity> listPageForCommodity(Map<String, Object> params) {
        params.put("org_id",domainsMapper.getOrgIdByDomain(params.get("domain").toString()));
        Query query = new Query(params);
        Page<CommodityEntity> page = new Page<>(query);
        List<CommodityEntity> resp= commodityMapper.listForPage(page, query);
        page.setRows(resp);
        return page;
    }

    /**
     * 新增
     *
     * @param commodityEntity
     * @return
     */
    Resp<CommodityEntity> saveCommodity(CommodityEntity commodityEntity, String domain) {
        return null;
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Resp<CommodityEntity> getCommodityById(Long id) {
        return null;
    }

    /**
     * 修改
     *
     * @param commodityEntity
     * @return
     */
    Resp<Integer> updateCommodity(CommodityEntity commodityEntity) {
        return null;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    Resp batchRemove(Long[] id) {
        return null;
    }

}
