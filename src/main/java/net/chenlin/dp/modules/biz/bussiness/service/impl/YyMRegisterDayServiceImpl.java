package net.chenlin.dp.modules.biz.bussiness.service.impl;

import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
import net.chenlin.dp.modules.biz.member.dao.MemberMapper;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.bussiness.entity.YyMRegisterDayEntity;
import net.chenlin.dp.modules.biz.bussiness.dao.YyMRegisterDayMapper;
import net.chenlin.dp.modules.biz.bussiness.service.YyMRegisterDayService;


/**
 * 运营-每日注册数据
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("yyMRegisterDayService")
@AllArgsConstructor
public class YyMRegisterDayServiceImpl implements YyMRegisterDayService {

    private YyMRegisterDayMapper yyMRegisterDayMapper;
	private MemberMapper memberMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public List<YyMRegisterDayEntity> listYyMRegisterDay(Map<String, Object> params) {
		Query query = new Query(params);
		List<YyMRegisterDayEntity> resp= memberMapper.getObjectGroupByDate(query);
		return resp;
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
