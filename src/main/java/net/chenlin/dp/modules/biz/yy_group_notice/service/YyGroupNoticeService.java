package net.chenlin.dp.modules.biz.yy_group_notice.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.yy_group_notice.entity.YyGroupNoticeEntity;

/**
 * 运营-消息群发
 * @author wang<fangyuan.co@outlook.com>
 */
public interface YyGroupNoticeService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<YyGroupNoticeEntity> listYyGroupNotice(Map<String, Object> params);

    /**
     * 新增
     * @param yyGroupNotice
     * @return
     */
	Resp<YyGroupNoticeEntity> saveYyGroupNotice(YyGroupNoticeEntity yyGroupNotice);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<YyGroupNoticeEntity> getYyGroupNoticeById(Long id);

    /**
     * 修改
     * @param yyGroupNotice
     * @return
     */
	Resp<Integer> updateYyGroupNotice(YyGroupNoticeEntity yyGroupNotice);

    /**
     * 删除
     * @param id
     * @return
     */
	Resp batchRemove(Long[] id);
	
}
