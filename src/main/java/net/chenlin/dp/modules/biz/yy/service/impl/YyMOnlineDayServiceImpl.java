package net.chenlin.dp.modules.biz.yy.service.impl;

import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.yy.entity.YyMOnlineDayEntity;
import net.chenlin.dp.modules.biz.yy.dao.YyMOnlineDayMapper;
import net.chenlin.dp.modules.biz.yy.service.YyMOnlineDayService;


/**
 * 运营-每日用户在线统计
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("yyMOnlineDayService")
@AllArgsConstructor
public class YyMOnlineDayServiceImpl implements YyMOnlineDayService {

    private YyMOnlineDayMapper yyMOnlineDayMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<YyMOnlineDayEntity> listYyMOnlineDay(Map<String, Object> params) {
		Query query = new Query(params);
		Page<YyMOnlineDayEntity> page = new Page<>(query);
		List<YyMOnlineDayEntity> resp= yyMOnlineDayMapper.listForPage(page, query);
		page.setRows(resp);
		return page;
	}

    /**
     * 新增
     * @param yyMOnlineDay
     * @return
     */
	@Override
	public Resp<YyMOnlineDayEntity> saveYyMOnlineDay(YyMOnlineDayEntity yyMOnlineDay) {
		int count = yyMOnlineDayMapper.save(yyMOnlineDay);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp<YyMOnlineDayEntity> getYyMOnlineDayById(Long id) {
		YyMOnlineDayEntity yyMOnlineDay = yyMOnlineDayMapper.getObjectById(id);
		return CommonUtils.msgResp(yyMOnlineDay);
	}

    /**
     * 修改
     * @param yyMOnlineDay
     * @return
     */
	@Override
	public Resp<Integer> updateYyMOnlineDay(YyMOnlineDayEntity yyMOnlineDay) {
		int count = yyMOnlineDayMapper.update(yyMOnlineDay);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(Long[] id) {
		int count = yyMOnlineDayMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

}
