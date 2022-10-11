package net.chenlin.dp.modules.biz.member.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.entity.Resp;
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
    Resp saveFriends(FriendsEntity friends);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Resp getFriendsById(String id);

    /**
     * 修改
     * @param friends
     * @return
     */
    Resp updateFriends(FriendsEntity friends);

    /**
     * 删除
     * @param id
     * @return
     */
    Resp batchRemove(String[] id);
	
}
