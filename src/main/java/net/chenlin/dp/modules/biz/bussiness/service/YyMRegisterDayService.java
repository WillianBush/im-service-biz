package net.chenlin.dp.modules.biz.bussiness.service;

import java.util.List;
import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.bussiness.entity.YyMRegisterDayEntity;

/**
 * 运营-每日注册数据
 * @author wang<fangyuan.co@outlook.com>
 */
public interface YyMRegisterDayService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Resp<List<YyMRegisterDayEntity>> listYyMRegisterDay(Map<String, Object> params);

    /**
     * 新增
     * @param yyMRegisterDay
     * @return
     */
	Resp<YyMRegisterDayEntity> saveYyMRegisterDay(YyMRegisterDayEntity yyMRegisterDay);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<YyMRegisterDayEntity> getYyMRegisterDayById(Long id);

    /**
     * 修改
     * @param yyMRegisterDay
     * @return
     */
	Resp<Integer> updateYyMRegisterDay(YyMRegisterDayEntity yyMRegisterDay);

    /**
     * 删除
     * @param id
     * @return
     */
	Resp batchRemove(Long[] id);
	
}
