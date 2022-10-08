package net.chenlin.dp.modules.biz.yy_group_notice.service.impl;

import java.util.Map;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.yy_group_notice.entity.YyGroupNoticeEntity;
import net.chenlin.dp.modules.biz.yy_group_notice.dao.YyGroupNoticeMapper;
import net.chenlin.dp.modules.biz.yy_group_notice.service.YyGroupNoticeService;

/**
 * 运营-消息群发
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("yyGroupNoticeService")
@AllArgsConstructor
public class YyGroupNoticeServiceImpl implements YyGroupNoticeService {

    private YyGroupNoticeMapper yyGroupNoticeMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<YyGroupNoticeEntity> listYyGroupNotice(Map<String, Object> params) {
		Query query = new Query(params);
		Page<YyGroupNoticeEntity> page = new Page<>(query);
		yyGroupNoticeMapper.listForPage(page, query);
		return page;
	}

    /**
     * 新增
     * @param yyGroupNotice
     * @return
     */
	@Override
	public Resp<YyGroupNoticeEntity> saveYyGroupNotice(YyGroupNoticeEntity yyGroupNotice) {
		int count = yyGroupNoticeMapper.save(yyGroupNotice);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp<YyGroupNoticeEntity> getYyGroupNoticeById(Long id) {
		YyGroupNoticeEntity yyGroupNotice = yyGroupNoticeMapper.getObjectById(id);
		return CommonUtils.msgResp(yyGroupNotice);
	}

    /**
     * 修改
     * @param yyGroupNotice
     * @return
     */
	@Override
	public Resp<Integer> updateYyGroupNotice(YyGroupNoticeEntity yyGroupNotice) {
		int count = yyGroupNoticeMapper.update(yyGroupNotice);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(Long[] id) {
		int count = yyGroupNoticeMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

}
