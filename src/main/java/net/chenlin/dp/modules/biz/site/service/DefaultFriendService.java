package net.chenlin.dp.modules.biz.site.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.site.entity.DefaultFriendEntity;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface DefaultFriendService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<DefaultFriendEntity> listDefaultFriend(Map<String, Object> params);

    /**
     * 新增
     * @param defaultFriend
     * @return
     */
	Resp<DefaultFriendEntity> saveDefaultFriend(DefaultFriendEntity defaultFriend);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	Resp<DefaultFriendEntity> getDefaultFriendById(Long id);

    /**
     * 修改
     * @param defaultFriend
     * @return
     */
	Resp<Integer> updateDefaultFriend(DefaultFriendEntity defaultFriend);

    /**
     * 删除
     * @param id
     * @return
     */
	Resp batchRemove(Long[] id);
	
}
