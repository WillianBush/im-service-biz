package net.chenlin.dp.modules.biz.bussiness.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.bussiness.entity.WaitsendmessageEntity;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface WaitsendmessageService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<WaitsendmessageEntity> listWaitsendmessage(Map<String, Object> params);

    /**
     * 新增
     * @param waitsendmessage
     * @return
     */
	Resp<WaitsendmessageEntity> saveWaitsendmessage(WaitsendmessageEntity waitsendmessage);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<WaitsendmessageEntity> getWaitsendmessageById(Long id);

    /**
     * 修改
     * @param waitsendmessage
     * @return
     */
	Resp<Integer> updateWaitsendmessage(WaitsendmessageEntity waitsendmessage);

    /**
     * 删除
     * @param id
     * @return
     */
	Resp batchRemove(Long[] id);
	
}
