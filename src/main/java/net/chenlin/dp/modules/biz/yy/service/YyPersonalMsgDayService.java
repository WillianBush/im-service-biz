package net.chenlin.dp.modules.biz.yy.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.yy.entity.YyPersonalMsgDayEntity;

/**
 * 运营-每次私发消息统计
 * @author wang<fangyuan.co@outlook.com>
 */
public interface YyPersonalMsgDayService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<YyPersonalMsgDayEntity> listYyPersonalMsgDay(Map<String, Object> params);

    /**
     * 新增
     * @param yyPersonalMsgDay
     * @return
     */
	Resp<YyPersonalMsgDayEntity> saveYyPersonalMsgDay(YyPersonalMsgDayEntity yyPersonalMsgDay);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<YyPersonalMsgDayEntity> getYyPersonalMsgDayById(Long id);

    /**
     * 修改
     * @param yyPersonalMsgDay
     * @return
     */
	Resp<Integer> updateYyPersonalMsgDay(YyPersonalMsgDayEntity yyPersonalMsgDay);

    /**
     * 删除
     * @param id
     * @return
     */
	Resp batchRemove(Long[] id);
	
}
