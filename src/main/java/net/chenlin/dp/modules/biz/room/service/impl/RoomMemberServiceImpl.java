package net.chenlin.dp.modules.biz.room.service.impl;

import java.util.*;

import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.constant.RedisCacheKeys;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.support.redis.RedisCacheManager;
import net.chenlin.dp.common.utils.SnowFlakeIdWorker;
import net.chenlin.dp.modules.biz.member.dao.MemberMapper;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
import net.chenlin.dp.modules.biz.room.entity.RoomBean;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.room.entity.RoomMemberEntity;
import net.chenlin.dp.modules.biz.room.dao.RoomMemberMapper;
import net.chenlin.dp.modules.biz.room.service.RoomMemberService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Slf4j
@Service("roomMemberService")
@AllArgsConstructor
public class RoomMemberServiceImpl implements RoomMemberService {

    private RoomMemberMapper roomMemberMapper;
    private MemberMapper memberMapper;
    private RedisCacheManager redisCacheManager;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<MemberEntity> listRoomMember(Map<String, Object> params) {
		params.put("org_id", 1);
		Query query = new Query(params);
		Page<MemberEntity> page = new Page<>(query);
		page.setRows(memberMapper.getMemberByRoomIdForPage(page, query));
		return page;
	}

    /**
     * 新增
     * @param roomMember
     * @return
     */
	@Override
	public Resp saveRoomMember(RoomMemberEntity roomMember) {
		SnowFlakeIdWorker sw=new SnowFlakeIdWorker(1);
		roomMember.setId(sw.createId());
		roomMember.setCreateDate(new Date());
		/*** 根据memberId 查询 member 得到 id */
		MemberEntity memberEntity=memberMapper.getMemberByMid(roomMember.getMember_id(), 1);
		roomMember.setMember_id(memberEntity.getId());
		int count = roomMemberMapper.save(roomMember);
		return CommonUtils.msgResp(count);
	}


    /**
     * 修改
     * @param roomMember
     * @return
     */
	@Override
	public Resp updateRoomMember(RoomMemberEntity roomMember) {
		roomMember.setModifyDate(new Date());
		int count = roomMemberMapper.update(roomMember);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(String[] id) {
		int count = roomMemberMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

	@Override
	public Resp batchSaveRoomMember(List<RoomMemberEntity> roomMembers) {
		roomMembers.forEach(rm->{
			saveRoomMember(rm);
		});
		return Resp.ok();
	}

	/***
	 * 批量添加机器人到群里
	 * @param para roomId 房间号
	 * @param para count 机器人数量
	 * @return
	 */
	@Override
	public Resp batchAddRobot(HashMap para) {
//		try {
			String roomId = para.get("roomId").toString();
			Integer count = Integer.parseInt(para.get("count").toString());
			SnowFlakeIdWorker sfw = new SnowFlakeIdWorker(1);
			/**获取不在群组的机器人**/
			List<MemberEntity> robotList = memberMapper.getRobotList(roomId, count);
			List<RoomMemberEntity> rmList = new ArrayList<>();
			robotList.forEach(robot -> {
				RoomMemberEntity rm = new RoomMemberEntity(sfw.createId(), roomId, robot.getId(), 0, 0);
				rmList.add(rm);
			});
			int counts = roomMemberMapper.batchAddRobot(rmList);
			if(counts>0){
				refreshRedis(roomId);
			}
			return CommonUtils.msgResp(counts);
//		} catch (Exception e) {
//			return Resp.error("执行异常：" + e);
//		}
	}

	/***
	 * 更新redis
	 * @param roomId
	 */
	public void refreshRedis(String roomId){
		Object obj= redisCacheManager.hget(RedisCacheKeys.REDIS_KEY_ROOMB_BEAN_MAP,roomId);
		if(obj!=null){
			String roomMemberIds=roomMemberMapper.getRoomMemberIds(roomId);
			if(!roomMemberIds.isEmpty()) {
				RoomBean roomBean = JSON.parseObject(obj.toString(), RoomBean.class);
				roomBean.setMember_ids(roomMemberIds+"#");
				log.error(JSON.toJSONString(roomBean));
				redisCacheManager.hset(RedisCacheKeys.REDIS_KEY_ROOMB_BEAN_MAP, roomId, JSON.toJSONString(roomBean));
			}
		}
	}

}
