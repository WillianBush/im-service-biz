package net.chenlin.dp.modules.biz.notice.service.impl;

import java.util.Map;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.notice.entity.NoticeEntity;
import net.chenlin.dp.modules.biz.notice.dao.NoticeMapper;
import net.chenlin.dp.modules.biz.notice.service.NoticeService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("noticeService")
@AllArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private NoticeMapper noticeMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<NoticeEntity> listNotice(Map<String, Object> params) {
		Query query = new Query(params);
		Page<NoticeEntity> page = new Page<>(query);
		noticeMapper.listForPage(page, query);
		return page;
	}

    /**
     * 新增
     * @param notice
     * @return
     */
	@Override
	public R saveNotice(NoticeEntity notice) {
		int count = noticeMapper.save(notice);
		return CommonUtils.msg(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public R getNoticeById(Long id) {
		NoticeEntity notice = noticeMapper.getObjectById(id);
		return CommonUtils.msg(notice);
	}

    /**
     * 修改
     * @param notice
     * @return
     */
	@Override
	public R updateNotice(NoticeEntity notice) {
		int count = noticeMapper.update(notice);
		return CommonUtils.msg(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public R batchRemove(Long[] id) {
		int count = noticeMapper.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
