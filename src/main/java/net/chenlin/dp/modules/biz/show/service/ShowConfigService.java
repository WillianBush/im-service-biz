package net.chenlin.dp.modules.biz.show.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.show.entity.ShowConfigEntity;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface ShowConfigService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<ShowConfigEntity> listShowConfig(Map<String, Object> params);

    /**
     * 新增
     * @param showConfig
     * @return
     */
	Resp<ShowConfigEntity> saveShowConfig(ShowConfigEntity showConfig);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<ShowConfigEntity> getShowConfigById(Long id);

    /**
     * 修改
     * @param showConfig
     * @return
     */
	Resp<Integer> updateShowConfig(ShowConfigEntity showConfig);

    /**
     * 删除
     * @param id
     * @return
     */
	Resp batchRemove(Long[] id);
	
}
