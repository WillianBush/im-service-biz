package net.chenlin.dp.modules.biz.bussiness.service.impl;

import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.bussiness.entity.WaitsendmessageEntity;
import net.chenlin.dp.modules.biz.bussiness.dao.WaitsendmessageMapper;
import net.chenlin.dp.modules.biz.bussiness.service.WaitsendmessageService;


/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("waitsendmessageService")
@AllArgsConstructor
public class WaitsendmessageServiceImpl implements WaitsendmessageService {

    private WaitsendmessageMapper waitsendmessageMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<WaitsendmessageEntity> listWaitsendmessage(Map<String, Object> params) {
		Query query = new Query(params);
		Page<WaitsendmessageEntity> page = new Page<>(query);
		List<WaitsendmessageEntity> resp= waitsendmessageMapper.listForPage(page, query);
		page.setRows(resp);
		return page;
	}

    /**
     * 新增
     * @param waitsendmessage
     * @return
     */
	@Override
	public Resp<WaitsendmessageEntity> saveWaitsendmessage(WaitsendmessageEntity waitsendmessage) {
		int count = waitsendmessageMapper.save(waitsendmessage);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp<WaitsendmessageEntity> getWaitsendmessageById(Long id) {
		WaitsendmessageEntity waitsendmessage = waitsendmessageMapper.getObjectById(id);
		return CommonUtils.msgResp(waitsendmessage);
	}

    /**
     * 修改
     * @param waitsendmessage
     * @return
     */
	@Override
	public Resp<Integer> updateWaitsendmessage(WaitsendmessageEntity waitsendmessage) {
		int count = waitsendmessageMapper.update(waitsendmessage);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(Long[] id) {
		int count = waitsendmessageMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

}
