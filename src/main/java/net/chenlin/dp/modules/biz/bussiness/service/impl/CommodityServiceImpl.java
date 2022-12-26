package net.chenlin.dp.modules.biz.bussiness.service.impl;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.bussiness.dao.CommodityMapper;
import net.chenlin.dp.modules.biz.bussiness.entity.CommodityCategoryEntity;
import net.chenlin.dp.modules.biz.bussiness.entity.CommodityEntity;
import net.chenlin.dp.modules.biz.bussiness.entity.YyIpListEntity;
import net.chenlin.dp.modules.biz.bussiness.service.CommodityService;
import net.chenlin.dp.modules.sys.dao.DomainsMapper;
import net.chenlin.dp.modules.sys.entity.SysLogEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public class CommodityServiceImpl implements CommodityService {

    private CommodityMapper commodityMapper;
    private DomainsMapper domainsMapper;
    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    public Page<CommodityEntity> listPageForCommodity(Map<String, Object> params) {
        params.put("org_id",domainsMapper.getOrgIdByDomain(params.get("domain").toString()));
        Query query = new Query(params);
        Page<CommodityEntity> page = new Page<>(query);
        List<CommodityEntity> resp= commodityMapper.listForPage(page, query);
        page.setRows(resp);
        return page;
    }

    @Override
    public Page<CommodityCategoryEntity> listPageForCommodityCategory(Map<String, Object> params) {
//        params.put("org_id",domainsMapper.getOrgIdByDomain(params.get("domain").toString()));
        Query query = new Query(params);
        Page<CommodityCategoryEntity> page = new Page<>(query);
        List<CommodityCategoryEntity> resp= commodityMapper.listForPageCC(page, query);
        page.setRows(resp);
        return page;
    }

    /**
     * 新增
     *
     * @param commodityEntity
     * @return
     */
    public Resp<CommodityEntity> saveCommodity(CommodityEntity commodityEntity, String domain) {
        commodityEntity.setOrg_id(domainsMapper.getOrgIdByDomain(domain));
        int count = commodityMapper.save(commodityEntity);
        return CommonUtils.msgResp(count);
    }

    @Override
    public Resp<CommodityCategoryEntity> saveCommodityCategory(CommodityCategoryEntity commodityCategoryEntity) {
        int count = commodityMapper.saveCC(commodityCategoryEntity);
        return CommonUtils.msgResp(count);
    }

    @Override
    public Resp<CommodityEntity> getCommodityById(Long id) {
        CommodityEntity commodityEntity = commodityMapper.getObjectById(id);
        return CommonUtils.msgResp(commodityEntity);
    }

    @Override
    public Resp<CommodityCategoryEntity> getCommodityCategoryById(Long id) {
        CommodityCategoryEntity commodityEntity = commodityMapper.getObjectByIdCC(id);
        return CommonUtils.msgResp(commodityEntity);
    }

    /**
     * 修改
     *
     * @param commodityEntity
     * @return
     */
    public Resp<Integer> updateCommodity(CommodityEntity commodityEntity, String domain) {
        commodityEntity.setOrg_id(domainsMapper.getOrgIdByDomain(domain));
        int count = commodityMapper.update(commodityEntity);
        return CommonUtils.msgResp(count);
    }

    @Override
    public Resp<Integer> updateCommodityCategory(CommodityCategoryEntity commodityCategoryEntity) {
        int count = commodityMapper.updateCC(commodityCategoryEntity);
        return CommonUtils.msgResp(count);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public Resp batchRemove(Long[] id) {
        int count = commodityMapper.batchRemove(id);
        return CommonUtils.msgResp(id, count);
    }

    @Override
    public Resp batchRemoveCC(Long[] id) {
        int count = commodityMapper.batchRemoveCC(id);
        return CommonUtils.msgResp(id, count);
    }

}
