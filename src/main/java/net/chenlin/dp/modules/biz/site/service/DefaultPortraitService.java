package net.chenlin.dp.modules.biz.site.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.site.entity.DefaultPortraitEntity;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface DefaultPortraitService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<DefaultPortraitEntity> listDefaultPortrait(Map<String, Object> params);

    /**
     * 新增
     * @param defaultPortrait
     * @return
     */
	Resp<DefaultPortraitEntity> saveDefaultPortrait(DefaultPortraitEntity defaultPortrait);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<DefaultPortraitEntity> getDefaultPortraitById(String id);

    /**
     * 修改
     * @param defaultPortrait
     * @return
     */
	Resp<Integer> updateDefaultPortrait(DefaultPortraitEntity defaultPortrait);

    /**
     * 删除
     * @param id
     * @return
     */
	Resp batchRemove(String[] id);
	
}
