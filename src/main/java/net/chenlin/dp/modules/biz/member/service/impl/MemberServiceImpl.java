package net.chenlin.dp.modules.biz.member.service.impl;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.entity.*;
import net.chenlin.dp.common.support.properties.GlobalProperties;
import net.chenlin.dp.common.utils.MD5Utils;
import net.chenlin.dp.common.utils.SnowFlakeIdWorker;
import net.chenlin.dp.modules.biz.member.dao.FriendsMapper;
import net.chenlin.dp.modules.biz.member.entity.FriendsEntity;
import net.chenlin.dp.modules.biz.room.dao.MessageHistoryMapper;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jcajce.provider.digest.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
import net.chenlin.dp.modules.biz.member.dao.MemberMapper;
import net.chenlin.dp.modules.biz.member.service.MemberService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Slf4j
@Service("memberService")
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private MemberMapper memberMapper;

	private OSSModel ossModel;

	private FriendsMapper friendsMapper;

	private MessageHistoryMapper messageHistoryMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<MemberEntity> listMember(Map<String, Object> params) {
		if (null != params && !params.isEmpty()){
			List<String> lastLogin= (ArrayList<String>)params.get("createdate");
			if (lastLogin != null && lastLogin.size() == 2){
				params.put("createdateStart",lastLogin.get(0));
				params.put("createdateEnd",lastLogin.get(1));
			}
		}
		Query query = new Query(params);
		Page<MemberEntity> page = new Page<>(query);
		List<MemberEntity>  list = memberMapper.listForPage(page, query);
		for (MemberEntity m : list) {
			if (StringUtils.isEmpty(m.getHeadpic())){
				m.setHeadpic("https://"+ossModel.getEndpoint() + "/img_sys/defaultHeadPic.jpg");
			}else {
				m.setHeadpic("https://"+ossModel.getEndpoint() +m.getHeadpic());
			}
		}
		page.setRows(list);
		return page;
	}

    /**
     * 新增
     * @param member
     * @return
     */
	@Override
	public Resp saveMember(MemberEntity member) {
		SnowFlakeIdWorker sw=new SnowFlakeIdWorker(1);
		//根据username查询是否存在
		Map para=new HashMap();
		para.put("username",member.getUsername());
		Page<MemberEntity> rsPage=listMember(para);
		if(rsPage.getRows().size()>0){
			return Resp.error(Resp.error,"用户已经存在");
		}
		member.setId(sw.createId());
		int count = memberMapper.save(member);
		if(count>0) {
			/***
			 * 添加官方团队给用户
			 */
			FriendsEntity f = new FriendsEntity();
			f.setId(sw.createId());
			f.setCreatedate(new Date());
			//官方团队ID：-1
			f.setFriendid("-1");
			f.setMid(member.getId());
			friendsMapper.save(f);
		}
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp<MemberEntity> getMemberById(String id) {
		MemberEntity member = memberMapper.getObjectById(id);
		return CommonUtils.msgResp(member);
	}



    /**
     * 修改
     * @param member
     * @return
     */
	@Override
	public Resp updateMember(MemberEntity member) {
		int count = memberMapper.update(member);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(String[] id) {
		int count = memberMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

	/**
	 * 根据用户ID删除所有的聊天记录
	 * @return
	 */
	@Override
	public Resp removeAllHistoryMsgByUid(String uid){
		int count=messageHistoryMapper.deleteByFromUid(uid);
		return CommonUtils.msgResp(count);
	}

	@Override
	public Resp updateMemberPass(MemberEntity member) {
		MemberEntity me = new MemberEntity();
		me.setId(member.getId());
		me.setPassword(MD5Utils.MD5Encode(member.getPassword()));
		int count =memberMapper.update(me);
		return CommonUtils.msgResp(count);
	}
}
