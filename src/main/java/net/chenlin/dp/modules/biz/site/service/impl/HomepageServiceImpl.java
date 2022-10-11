package net.chenlin.dp.modules.biz.site.service.impl;

import java.util.Map;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.site.entity.HomepageEntity;
import net.chenlin.dp.modules.biz.site.dao.HomepageMapper;
import net.chenlin.dp.modules.biz.site.service.HomepageService;


/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("homepageService")
@AllArgsConstructor
public class HomepageServiceImpl implements HomepageService {

    private HomepageMapper homepageMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<HomepageEntity> listHomepage(Map<String, Object> params) {
		Query query = new Query(params);
		Page<HomepageEntity> page = new Page<>(query);
		List<HomepageEntity> resp= homepageMapper.listForPage(page, query);
		page.setRows(resp);
		return page;
	}

    /**
     * 新增
     * @param homepage
     * @return
     */
	@Override
	public Resp<HomepageEntity> saveHomepage(HomepageEntity homepage) {
		int count = homepageMapper.save(homepage);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp<HomepageEntity> getHomepageById(String id) {
		HomepageEntity homepage = homepageMapper.getObjectById(id);
		return CommonUtils.msgResp(homepage);
	}

    /**
     * 修改
     * @param homepage
     * @return
     */
	@Override
	public Resp<Integer> updateHomepage(HomepageEntity homepage) {
		int count = homepageMapper.update(homepage);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(String[] id) {
		int count = homepageMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

}
