package net.chenlin.dp.modules.biz.bussiness.dao;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.mapper.TkBaseMapper;
//import net.chenlin.dp.modules.biz.bussiness.entity.CommodityCategoryEntity;
//import net.chenlin.dp.modules.biz.bussiness.entity.CommodityEntity;
import net.chenlin.dp.modules.biz.bussiness.entity.NoticeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface NoticeMapper extends TkBaseMapper<NoticeEntity> {

    /**
     * 分页查询列表
     * @param page
     * @param query
     * @return
     */
    List<NoticeEntity> listForPage(Page<NoticeEntity> page, Query query);

    /**
     * 批量删除
     * @param id
     * @return
     */
    int batchRemove(Object[] id);

    /**
     * 根据ID获取实例信息
     * @param id
     * @return
     */
    NoticeEntity getObjectById(Object id);

    /**
     * 根据ID获取实例信息
     * @param title
     * @return
     */
    NoticeEntity getObjectByTitle(Object title);

    /**
     * 更新
     * @param notice
     * @return
     */
    int update(NoticeEntity notice);

    /**
     * 新增
     * @param t
     * @return
     */
    int save(NoticeEntity t);
}
