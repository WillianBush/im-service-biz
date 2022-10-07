package net.chenlin.dp.modules.biz.notice.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
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
	R saveNotice(NoticeEntity notice);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	R getNoticeById(Long id);

    /**
     * 修改
     * @param notice
     * @return
     */
	R updateNotice(NoticeEntity notice);

    /**
     * 删除
     * @param id
     * @return
     */
	R batchRemove(Long[] id);
	
}
