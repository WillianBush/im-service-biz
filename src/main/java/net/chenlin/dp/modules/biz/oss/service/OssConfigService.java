package net.chenlin.dp.modules.biz.oss.service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.oss.entity.OssConfigEntity;

import java.util.Map;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface OssConfigService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<OssConfigEntity> listOssConfig(Map<String, Object> params);

    /**
     * 新增
     * @param ossConfig
     * @return
     */
	R saveOssConfig(OssConfigEntity ossConfig);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	R getOssConfigById(Long id);

    /**
     * 修改
     * @param ossConfig
     * @return
     */
	R updateOssConfig(OssConfigEntity ossConfig);

    /**
     * 删除
     * @param id
     * @return
     */
	R batchRemove(Long[] id);
	
}
