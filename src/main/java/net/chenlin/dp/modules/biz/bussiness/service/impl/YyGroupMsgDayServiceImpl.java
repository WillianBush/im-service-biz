package net.chenlin.dp.modules.biz.bussiness.service.impl;

import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
import net.chenlin.dp.modules.biz.bussiness.entity.YyPersonalMsgDayEntity;
import net.chenlin.dp.modules.biz.room.dao.MessageHistoryMapper;
import net.chenlin.dp.modules.sys.service.DomainsService;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.bussiness.entity.YyGroupMsgDayEntity;
import net.chenlin.dp.modules.biz.bussiness.dao.YyGroupMsgDayMapper;
import net.chenlin.dp.modules.biz.bussiness.service.YyGroupMsgDayService;


/**
 * 运营-每日群消息数量统计
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("yyGroupMsgDayService")
@AllArgsConstructor
public class YyGroupMsgDayServiceImpl implements YyGroupMsgDayService {

    private YyGroupMsgDayMapper yyGroupMsgDayMapper;
	private MessageHistoryMapper messageHistoryMapper;
	private DomainsService domainsService;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Resp<List<YyGroupMsgDayEntity>> listYyGroupMsgDay(Map<String, Object> params) {
		Query query = new Query(params);
		List<YyGroupMsgDayEntity> resp = messageHistoryMapper.getObjectGroupMessageGroupByDate(query);
		return Resp.ok(200, "操作成功", resp);
	}

    /**
     * 新增
     * @param yyGroupMsgDay
     * @return
     */
	@Override
	public Resp<YyGroupMsgDayEntity> saveYyGroupMsgDay(YyGroupMsgDayEntity yyGroupMsgDay) {
		int count = yyGroupMsgDayMapper.save(yyGroupMsgDay);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp<YyGroupMsgDayEntity> getYyGroupMsgDayById(Long id) {
		YyGroupMsgDayEntity yyGroupMsgDay = yyGroupMsgDayMapper.getObjectById(id);
		return CommonUtils.msgResp(yyGroupMsgDay);
	}

    /**
     * 修改
     * @param yyGroupMsgDay
     * @return
     */
	@Override
	public Resp<Integer> updateYyGroupMsgDay(YyGroupMsgDayEntity yyGroupMsgDay) {
		int count = yyGroupMsgDayMapper.update(yyGroupMsgDay);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(Long[] id) {
		int count = yyGroupMsgDayMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

}
