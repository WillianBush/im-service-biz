package net.chenlin.dp.modules.biz.site.service.impl;

import java.util.Map;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.site.entity.FunctionConfigEntity;
import net.chenlin.dp.modules.biz.site.dao.FunctionConfigMapper;
import net.chenlin.dp.modules.biz.site.service.FunctionConfigService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("functionConfigService")
@AllArgsConstructor
public class FunctionConfigServiceImpl implements FunctionConfigService {

    private FunctionConfigMapper functionConfigMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<FunctionConfigEntity> listFunctionConfig(Map<String, Object> params) {
		Query query = new Query(params);
		Page<FunctionConfigEntity> page = new Page<>(query);
		functionConfigMapper.listForPage(page, query);
		return page;
	}

    /**
     * 新增
     * @param functionConfig
     * @return
     */
	@Override
	public Resp<FunctionConfigEntity> saveFunctionConfig(FunctionConfigEntity functionConfig) {
		int count = functionConfigMapper.save(functionConfig);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp<FunctionConfigEntity> getFunctionConfigById(String id) {
		FunctionConfigEntity functionConfig = functionConfigMapper.getObjectById(id);
		return CommonUtils.msgResp(functionConfig);
	}

    /**
     * 修改
     * @param functionConfig
     * @return
     */
	@Override
	public Resp<Integer> updateFunctionConfig(FunctionConfigEntity functionConfig) {
		int count = functionConfigMapper.update(functionConfig);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(Long[] id) {
		int count = functionConfigMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

}
