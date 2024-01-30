package net.chenlin.dp.modules.biz.member.service.impl;

import lombok.AllArgsConstructor;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.SnowFlakeIdWorker;
import net.chenlin.dp.modules.biz.member.dao.MemberMapper;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.member.entity.FriendsEntity;
import net.chenlin.dp.modules.biz.member.dao.FriendsMapper;
import net.chenlin.dp.modules.biz.member.service.FriendsService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("friendsService")
@AllArgsConstructor
public class FriendsServiceImpl implements FriendsService {

    private FriendsMapper friendsMapper;
	private MemberMapper memberMapper;

    /**
     * 新增
     * @return
     */
	@Override
	public Resp saveFriends(FriendsEntity para) {
		FriendsEntity friends=new FriendsEntity();
		SnowFlakeIdWorker sw=new SnowFlakeIdWorker(1);
		friends.setMid(para.getMid());
		/*** 根据memberId 查询 member 得到 id */
		MemberEntity memberEntity=memberMapper.getMemberByMid(para.getFriendId());
		if(null==memberEntity){
			return Resp.error("添加好友不存在");
		}
		friends.setFriendId(memberEntity.getId());
		friends.setId(sw.createId());
		int count = friendsMapper.save(friends);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp getFriendsById(String id) {
		FriendsEntity friends = friendsMapper.getObjectById(id);
		return CommonUtils.msgResp(friends);
	}

    /**
     * 修改
     * @param friends
     * @return
     */
	@Override
	public Resp updateFriends(FriendsEntity friends) {
		friends.setOrgId(1);
		int count = friendsMapper.update(friends);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param friendsEntity
     * @return
     */
	@Override
	public Resp removeFriend(FriendsEntity friendsEntity) {
		friendsEntity.setOrgId(1);
		int count = friendsMapper.removeFriend(friendsEntity);
		return CommonUtils.msgResp(count);
	}

	@Override
	public Resp updateFriendsNote(String mid, String friendId, String friendName) {
		return null;
	}

}
