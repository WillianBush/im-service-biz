package net.chenlin.dp.modules.biz.site.service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.site.entity.HomepageUserEntity;

import java.util.Map;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface HomepageUserService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<HomepageUserEntity> listHomepage(Map<String, Object> params);

    /**
     * 新增
     * @param homepage
     * @return
     */
	Resp<HomepageUserEntity> saveHomepageUser(HomepageUserEntity homepage);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<HomepageUserEntity> getHomepageById(String id);

    /**
     * 修改
     * @param homepage
     * @return
     */
	Resp<Integer> updateHomepage(HomepageUserEntity homepage);

    /**
     * 删除
     * @param id
     * @return
     */
	Resp batchRemove(String[] id);
	
}
