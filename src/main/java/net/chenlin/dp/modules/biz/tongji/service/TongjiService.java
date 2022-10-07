package net.chenlin.dp.modules.biz.tongji.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.tongji.entity.TongjiEntity;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface TongjiService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<TongjiEntity> listTongji(Map<String, Object> params);

    /**
     * 新增
     * @param tongji
     * @return
     */
	R saveTongji(TongjiEntity tongji);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	R getTongjiById(Long id);

    /**
     * 修改
     * @param tongji
     * @return
     */
	R updateTongji(TongjiEntity tongji);

    /**
     * 删除
     * @param id
     * @return
     */
	R batchRemove(Long[] id);
	
}
