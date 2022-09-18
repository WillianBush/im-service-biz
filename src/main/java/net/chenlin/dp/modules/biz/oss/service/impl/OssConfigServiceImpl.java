package net.chenlin.dp.modules.biz.oss.service.impl;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.oss.dao.OssConfigMapper;
import net.chenlin.dp.modules.biz.oss.entity.OssConfigEntity;
import net.chenlin.dp.modules.biz.oss.service.OssConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("ossConfigService")
public class OssConfigServiceImpl implements OssConfigService {

	@Autowired
    private OssConfigMapper ossConfigMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<OssConfigEntity> listOssConfig(Map<String, Object> params) {
		Query query = new Query(params);
		Page<OssConfigEntity> page = new Page<>(query);
		ossConfigMapper.listForPage(page, query);
		return page;
	}

    /**
     * 新增
     * @param ossConfig
     * @return
     */
	@Override
	public R saveOssConfig(OssConfigEntity ossConfig) {
		int count = ossConfigMapper.save(ossConfig);
		return CommonUtils.msg(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public R getOssConfigById(Long id) {
		OssConfigEntity ossConfig = ossConfigMapper.getObjectById(id);
		return CommonUtils.msg(ossConfig);
	}

    /**
     * 修改
     * @param ossConfig
     * @return
     */
	@Override
	public R updateOssConfig(OssConfigEntity ossConfig) {
		int count = ossConfigMapper.update(ossConfig);
		return CommonUtils.msg(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public R batchRemove(Long[] id) {
		int count = ossConfigMapper.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
