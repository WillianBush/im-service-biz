package net.chenlin.dp.modules.biz.yy.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.yy.entity.YyGroupMsgDayEntity;

/**
 * 运营-每日群消息数量统计
 * @author wang<fangyuan.co@outlook.com>
 */
public interface YyGroupMsgDayService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<YyGroupMsgDayEntity> listYyGroupMsgDay(Map<String, Object> params);

    /**
     * 新增
     * @param yyGroupMsgDay
     * @return
     */
	Resp<YyGroupMsgDayEntity> saveYyGroupMsgDay(YyGroupMsgDayEntity yyGroupMsgDay);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<YyGroupMsgDayEntity> getYyGroupMsgDayById(Long id);

    /**
     * 修改
     * @param yyGroupMsgDay
     * @return
     */
	Resp<Integer> updateYyGroupMsgDay(YyGroupMsgDayEntity yyGroupMsgDay);

    /**
     * 删除
     * @param id
     * @return
     */
	Resp batchRemove(Long[] id);
	
}
