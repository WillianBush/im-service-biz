package net.chenlin.dp.modules.biz.yy.service.impl;

import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.yy.entity.YyMRegisterDayEntity;
import net.chenlin.dp.modules.biz.yy.dao.YyMRegisterDayMapper;
import net.chenlin.dp.modules.biz.yy.service.YyMRegisterDayService;


/**
 * 运营-每日注册数据
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("yyMRegisterDayService")
@AllArgsConstructor
public class YyMRegisterDayServiceImpl implements YyMRegisterDayService {

    private YyMRegisterDayMapper yyMRegisterDayMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<YyMRegisterDayEntity> listYyMRegisterDay(Map<String, Object> params) {
		Query query = new Query(params);
		Page<YyMRegisterDayEntity> page = new Page<>(query);
		List<YyMRegisterDayEntity> resp= yyMRegisterDayMapper.listForPage(page, query);
		page.setRows(resp);
		return page;
	}

    /**
     * 新增
     * @param yyMRegisterDay
     * @return
     */
	@Override
	public Resp<YyMRegisterDayEntity> saveYyMRegisterDay(YyMRegisterDayEntity yyMRegisterDay) {
		int count = yyMRegisterDayMapper.save(yyMRegisterDay);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp<YyMRegisterDayEntity> getYyMRegisterDayById(Long id) {
		YyMRegisterDayEntity yyMRegisterDay = yyMRegisterDayMapper.getObjectById(id);
		return CommonUtils.msgResp(yyMRegisterDay);
	}

    /**
     * 修改
     * @param yyMRegisterDay
     * @return
     */
	@Override
	public Resp<Integer> updateYyMRegisterDay(YyMRegisterDayEntity yyMRegisterDay) {
		int count = yyMRegisterDayMapper.update(yyMRegisterDay);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(Long[] id) {
		int count = yyMRegisterDayMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

}
