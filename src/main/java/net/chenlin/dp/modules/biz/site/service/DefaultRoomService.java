package net.chenlin.dp.modules.biz.site.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.site.entity.DefaultRoomEntity;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface DefaultRoomService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<DefaultRoomEntity> listDefaultRoom(Map<String, Object> params);

    /**
     * 新增
     * @param defaultRoom
     * @return
     */
	Resp<DefaultRoomEntity> saveDefaultRoom(DefaultRoomEntity defaultRoom);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<DefaultRoomEntity> getDefaultRoomById(Long id);

    /**
     * 修改
     * @param defaultRoom
     * @return
     */
	Resp<Integer> updateDefaultRoom(DefaultRoomEntity defaultRoom);

    /**
     * 删除
     * @param id
     * @return
     */
	Resp batchRemove(Long[] id);
	
}
