package net.chenlin.dp.modules.biz.ipwhite.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.ipwhite.entity.IpwhiteEntity;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface IpwhiteService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<IpwhiteEntity> listIpwhite(Map<String, Object> params);

    /**
     * 新增
     * @param ipwhite
     * @return
     */
	R saveIpwhite(IpwhiteEntity ipwhite);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	R getIpwhiteById(Long id);

    /**
     * 修改
     * @param ipwhite
     * @return
     */
	R updateIpwhite(IpwhiteEntity ipwhite);

    /**
     * 删除
     * @param id
     * @return
     */
	R batchRemove(Long[] id);
	
}
