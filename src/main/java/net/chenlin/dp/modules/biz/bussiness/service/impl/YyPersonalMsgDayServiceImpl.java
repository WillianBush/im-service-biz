package net.chenlin.dp.modules.biz.bussiness.service.impl;

import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
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

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<YyPersonalMsgDayEntity> listYyPersonalMsgDay(Map<String, Object> params) {
		Query query = new Query(params);
		Page<YyPersonalMsgDayEntity> page = new Page<>(query);
		List<YyPersonalMsgDayEntity> resp= yyPersonalMsgDayMapper.listForPage(page, query);
		page.setRows(resp);
		return page;
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
