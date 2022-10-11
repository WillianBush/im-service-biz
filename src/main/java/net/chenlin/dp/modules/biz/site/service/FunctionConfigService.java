package net.chenlin.dp.modules.biz.site.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.site.entity.FunctionConfigEntity;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface FunctionConfigService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<FunctionConfigEntity> listFunctionConfig(Map<String, Object> params);

    /**
     * 新增
     * @param functionConfig
     * @return
     */
	Resp<FunctionConfigEntity> saveFunctionConfig(FunctionConfigEntity functionConfig);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<FunctionConfigEntity> getFunctionConfigById(String id);

    /**
     * 修改
     * @param functionConfig
     * @return
     */
	Resp<Integer> updateFunctionConfig(FunctionConfigEntity functionConfig);

    /**
     * 删除
     * @param id
     * @return
     */
	Resp batchRemove(Long[] id);
	
}
