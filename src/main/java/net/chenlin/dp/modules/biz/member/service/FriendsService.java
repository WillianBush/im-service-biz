package net.chenlin.dp.modules.biz.member.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.member.entity.FriendsEntity;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface FriendsService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<FriendsEntity> listFriends(Map<String, Object> params);

    /**
     * 新增
     * @param friends
     * @return
     */
	R saveFriends(FriendsEntity friends);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	R getFriendsById(Long id);

    /**
     * 修改
     * @param friends
     * @return
     */
	R updateFriends(FriendsEntity friends);

    /**
     * 删除
     * @param id
     * @return
     */
	R batchRemove(Long[] id);
	
}
