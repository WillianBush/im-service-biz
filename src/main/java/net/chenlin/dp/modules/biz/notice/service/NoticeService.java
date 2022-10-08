package net.chenlin.dp.modules.biz.notice.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.notice.entity.NoticeEntity;

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
	Page<NoticeEntity> listNotice(Map<String, Object> params);

    /**
     * 新增
     * @param notice
     * @return
     */
	Resp<NoticeEntity> saveNotice(NoticeEntity notice);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<NoticeEntity> getNoticeById(Long id);

    /**
     * 修改
     * @param notice
     * @return
     */
	Resp<Integer> updateNotice(NoticeEntity notice);

    /**
     * 删除
     * @param id
     * @return
     */
	Resp batchRemove(Long[] id);
	
}
