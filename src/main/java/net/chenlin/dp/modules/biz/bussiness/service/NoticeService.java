package net.chenlin.dp.modules.biz.bussiness.service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.bussiness.entity.CommodityCategoryEntity;
import net.chenlin.dp.modules.biz.bussiness.entity.CommodityEntity;
import net.chenlin.dp.modules.biz.bussiness.entity.NoticeEntity;

import java.util.Map;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface NoticeService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<NoticeEntity> listPage(Map<String, Object> params);

    /**
     * 新增
     * @param notice
     * @return
     */
	Resp<NoticeEntity> save(NoticeEntity notice,String domain);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<NoticeEntity> getById(String id);

    /**
     * 根据id查询
     * @param title
     * @return
     */
    Resp<NoticeEntity> getByTitle(String title);

    /**
     * 修改
     * @param notice
     * @return
     */
	Resp<Integer> update(NoticeEntity notice, String domain);

    /**
     * 删除
     * @param id
     * @return
     */
	Resp batchRemove(String[] id);

}
