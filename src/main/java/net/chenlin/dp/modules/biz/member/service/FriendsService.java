package net.chenlin.dp.modules.biz.member.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.member.entity.FriendsEntity;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface FriendsService {


    /**
     * 新增
     * @return
     */
    Resp saveFriends(String mid,String friendId);

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
     * @param friendsEntity
     * @return
     */
    Resp removeFriend(FriendsEntity friendsEntity) ;


    /**
     * 修改
     * @return
     */
    Resp updateFriendsNote(String mid,String friendId,String friendName);
	
}
