package net.chenlin.dp.modules.biz.show.service.impl;

import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.show.entity.ShowConfigEntity;
import net.chenlin.dp.modules.biz.show.dao.ShowConfigMapper;
import net.chenlin.dp.modules.biz.show.service.ShowConfigService;


/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("showConfigService")
@AllArgsConstructor
public class ShowConfigServiceImpl implements ShowConfigService {

    private ShowConfigMapper showConfigMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<ShowConfigEntity> listShowConfig(Map<String, Object> params) {
		Query query = new Query(params);
		Page<ShowConfigEntity> page = new Page<>(query);
		List<ShowConfigEntity> resp= showConfigMapper.listForPage(page, query);
		page.setRows(resp);
		return page;
	}

    /**
     * 新增
     * @param showConfig
     * @return
     */
	@Override
	public Resp<ShowConfigEntity> saveShowConfig(ShowConfigEntity showConfig) {
		int count = showConfigMapper.save(showConfig);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp<ShowConfigEntity> getShowConfigById(Long id) {
		ShowConfigEntity showConfig = showConfigMapper.getObjectById(id);
		return CommonUtils.msgResp(showConfig);
	}

    /**
     * 修改
     * @param showConfig
     * @return
     */
	@Override
	public Resp<Integer> updateShowConfig(ShowConfigEntity showConfig) {
		int count = showConfigMapper.update(showConfig);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(Long[] id) {
		int count = showConfigMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

}
