package net.chenlin.dp.modules.biz.site.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.site.entity.HomepageEntity;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface HomepageService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<HomepageEntity> listHomepage(Map<String, Object> params);

    /**
     * 新增
     * @param homepage
     * @return
     */
	Resp<HomepageEntity> saveHomepage(HomepageEntity homepage);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<HomepageEntity> getHomepageById(String id);

    /**
     * 修改
     * @param homepage
     * @return
     */
	Resp<Integer> updateHomepage(HomepageEntity homepage);

    /**
     * 删除
     * @param id
     * @return
     */
	Resp batchRemove(String[] id);
	
}
