package net.chenlin.dp.modules.biz.site.service.impl;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.constant.RedisCacheKeys;
import net.chenlin.dp.common.support.redis.RedisCacheManager;
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
@Slf4j
public class FunctionConfigServiceImpl implements FunctionConfigService {

    private FunctionConfigMapper functionConfigMapper;
    private RedisCacheManager redisCacheManager;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<FunctionConfigEntity> listFunctionConfig(Map<String, Object> params) {
		Query query = new Query(params);
		Page<FunctionConfigEntity> page = new Page<>(query);
		List<FunctionConfigEntity> res = functionConfigMapper.listForPage(page, query);
		page.setRows(res);
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
		if(count>0){
			/**更新成功，更新redis**/
			redisCacheManager.set(RedisCacheKeys.REDIS_KEY_FUNCTION_CONFIG, JSON.toJSON(functionConfigMapper.getObjectById(functionConfig.getId())));
		}
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
