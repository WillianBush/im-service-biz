package net.chenlin.dp.modules.biz.site.service.impl;

import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.site.entity.DefaultPortraitEntity;
import net.chenlin.dp.modules.biz.site.dao.DefaultPortraitMapper;
import net.chenlin.dp.modules.biz.site.service.DefaultPortraitService;


/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("defaultPortraitService")
@AllArgsConstructor
public class DefaultPortraitServiceImpl implements DefaultPortraitService {

    private DefaultPortraitMapper defaultPortraitMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<DefaultPortraitEntity> listDefaultPortrait(Map<String, Object> params) {
		Query query = new Query(params);
		Page<DefaultPortraitEntity> page = new Page<>(query);
		List<DefaultPortraitEntity> resp= defaultPortraitMapper.listForPage(page, query);
		page.setRows(resp);
		return page;
	}

    /**
     * 新增
     * @param defaultPortrait
     * @return
     */
	@Override
	public Resp<DefaultPortraitEntity> saveDefaultPortrait(DefaultPortraitEntity defaultPortrait) {
		int count = defaultPortraitMapper.save(defaultPortrait);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp<DefaultPortraitEntity> getDefaultPortraitById(Long id) {
		DefaultPortraitEntity defaultPortrait = defaultPortraitMapper.getObjectById(id);
		return CommonUtils.msgResp(defaultPortrait);
	}

    /**
     * 修改
     * @param defaultPortrait
     * @return
     */
	@Override
	public Resp<Integer> updateDefaultPortrait(DefaultPortraitEntity defaultPortrait) {
		int count = defaultPortraitMapper.update(defaultPortrait);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(Long[] id) {
		int count = defaultPortraitMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

}
