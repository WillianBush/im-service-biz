package net.chenlin.dp.modules.biz.bussiness.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.bussiness.entity.YyMOnlineDayEntity;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
import net.chenlin.dp.modules.biz.member.entity.MemberloginlogEntity;

/**
 * 运营-每日用户在线统计
 * @author wang<fangyuan.co@outlook.com>
 */
public interface YyMOnlineDayService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<YyMOnlineDayEntity> listYyMOnlineDay(Map<String, Object> params);

    /**
     * 新增
     * @param yyMOnlineDay
     * @return
     */
	Resp<YyMOnlineDayEntity> saveYyMOnlineDay(YyMOnlineDayEntity yyMOnlineDay);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<YyMOnlineDayEntity> getYyMOnlineDayById(Long id);

    /**
     * 修改
     * @param yyMOnlineDay
     * @return
     */
	Resp<Integer> updateYyMOnlineDay(YyMOnlineDayEntity yyMOnlineDay);

    /**
     * 删除
     * @param id
     * @return
     */
	Resp batchRemove(Long[] id);

    /**
     * 根据id查询
     * @param params
     * @return
     */
    Page<MemberloginlogEntity> getYyMOnline(Map<String, Object> params);
	
}
