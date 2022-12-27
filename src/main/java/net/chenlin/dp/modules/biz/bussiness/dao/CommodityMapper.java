package net.chenlin.dp.modules.biz.bussiness.dao;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.mapper.TkBaseMapper;
import net.chenlin.dp.modules.biz.bussiness.entity.CommodityCategoryEntity;
import net.chenlin.dp.modules.biz.bussiness.entity.CommodityEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface CommodityMapper extends TkBaseMapper<CommodityEntity> {

    /**
     * 分页查询列表
     * @param page
     * @param query
     * @return
     */
    List<CommodityCategoryEntity> listForPageCC(Page<CommodityCategoryEntity> page, Query query);

    /**
     * 批量删除
     * @param id
     * @return
     */
    int batchRemoveCC(Object[] id);

    /**
     * 根据ID获取实例信息
     * @param id
     * @return
     */
    CommodityCategoryEntity getObjectByIdCC(Object id);

    /**
     * 根据ID获取实例信息
     * @param category_name
     * @return
     */
    CommodityCategoryEntity getObjectByNameCC(Object category_name);

    /**
     * 更新
     * @param t
     * @return
     */
    int updateCC(CommodityCategoryEntity t);

    /**
     * 新增
     * @param t
     * @return
     */
    int saveCC(CommodityCategoryEntity t);
}
