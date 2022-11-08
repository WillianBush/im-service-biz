package net.chenlin.dp.modules.biz.bussiness.service.impl;

import java.util.Map;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import net.chenlin.dp.modules.biz.bussiness.entity.YyGroupMsgDayEntity;
import net.chenlin.dp.modules.biz.room.dao.MessageHistoryMapper;
import net.chenlin.dp.modules.biz.room.entity.MessageHistoryEntity;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.bussiness.entity.YyPersonalMsgDayEntity;
import net.chenlin.dp.modules.biz.bussiness.dao.YyPersonalMsgDayMapper;
import net.chenlin.dp.modules.biz.bussiness.service.YyPersonalMsgDayService;


/**
 * 运营-每次私发消息统计
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("yyPersonalMsgDayService")
@AllArgsConstructor
public class YyPersonalMsgDayServiceImpl implements YyPersonalMsgDayService {

    private YyPersonalMsgDayMapper yyPersonalMsgDayMapper;
	private MessageHistoryMapper messageHistoryMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Resp<List<YyPersonalMsgDayEntity>> listYyPersonalMsgDay(Map<String, Object> params) {
		Query query = new Query(params);
		List<YyPersonalMsgDayEntity> resp = messageHistoryMapper.getObjectPersonalMessageGroupByDate(query);
		return Resp.ok(200, "操作成功", resp);
	}

	@Override
	public List<YyGroupMsgDayEntity> listYyGroupMsgDay(Map<String, Object> params) {
		Query query = new Query(params);
		return messageHistoryMapper.getObjectGroupMessageGroupByDate(query);
	}

	/**
     * 新增
     * @param yyPersonalMsgDay
     * @return
     */
	@Override
	public Resp<YyPersonalMsgDayEntity> saveYyPersonalMsgDay(YyPersonalMsgDayEntity yyPersonalMsgDay) {
		int count = yyPersonalMsgDayMapper.save(yyPersonalMsgDay);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp<YyPersonalMsgDayEntity> getYyPersonalMsgDayById(Long id) {
		YyPersonalMsgDayEntity yyPersonalMsgDay = yyPersonalMsgDayMapper.getObjectById(id);
		return CommonUtils.msgResp(yyPersonalMsgDay);
	}

    /**
     * 修改
     * @param yyPersonalMsgDay
     * @return
     */
	@Override
	public Resp<Integer> updateYyPersonalMsgDay(YyPersonalMsgDayEntity yyPersonalMsgDay) {
		int count = yyPersonalMsgDayMapper.update(yyPersonalMsgDay);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(Long[] id) {
		int count = yyPersonalMsgDayMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

}
