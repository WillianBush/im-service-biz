package net.chenlin.dp.modules.biz.bussiness.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.constant.RedisCacheKeys;
import net.chenlin.dp.common.support.redis.RedisCacheManager;
import net.chenlin.dp.modules.biz.member.dao.MemberMapper;
import net.chenlin.dp.modules.biz.member.dao.MemberloginlogMapper;
import net.chenlin.dp.modules.biz.member.entity.LastLoginDateEntity;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
import net.chenlin.dp.modules.biz.member.entity.MemberloginlogEntity;
import net.chenlin.dp.modules.biz.member.service.MemberService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.bussiness.entity.YyMOnlineDayEntity;
import net.chenlin.dp.modules.biz.bussiness.dao.YyMOnlineDayMapper;
import net.chenlin.dp.modules.biz.bussiness.service.YyMOnlineDayService;


/**
 * 运营-每日用户在线统计
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("yyMOnlineDayService")
@AllArgsConstructor
@Slf4j
public class YyMOnlineDayServiceImpl implements YyMOnlineDayService {

    private YyMOnlineDayMapper yyMOnlineDayMapper;

    private RedisCacheManager redisCacheManager;

	private MemberloginlogMapper memberloginlogMapper;

	private MemberService memberService;
    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public List<YyMOnlineDayEntity> listYyMOnlineDay(Map<String, Object> params) {
		Query query = new Query(params);

//		Page<MemberloginlogEntity> page = new Page<>(query);
		List<YyMOnlineDayEntity> resp = memberloginlogMapper.getObjectGroupByDate(query);
		return resp;
	}


    /**
     * 新增
     * @param yyMOnlineDay
     * @return
     */
	@Override
	public Resp<YyMOnlineDayEntity> saveYyMOnlineDay(YyMOnlineDayEntity yyMOnlineDay) {
		int count = yyMOnlineDayMapper.save(yyMOnlineDay);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp<YyMOnlineDayEntity> getYyMOnlineDayById(Long id) {
		YyMOnlineDayEntity yyMOnlineDay = yyMOnlineDayMapper.getObjectById(id);
		return CommonUtils.msgResp(yyMOnlineDay);
	}

    /**
     * 修改
     * @param yyMOnlineDay
     * @return
     */
	@Override
	public Resp<Integer> updateYyMOnlineDay(YyMOnlineDayEntity yyMOnlineDay) {
		int count = yyMOnlineDayMapper.update(yyMOnlineDay);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(Long[] id) {
		int count = yyMOnlineDayMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

	/**
	 * 获取当日在线用户数据
	 * @param params
	 * @return
	 * */
	@Override
	public Page<MemberloginlogEntity> getYyMOnline(Map<String, Object> params) {
		Map<Object,Object> onlineMembersWithDevices=  redisCacheManager.hmget(RedisCacheKeys.ONLINE_MEMBER);
		LastLoginDateEntity lastLoginDateEntity = new LastLoginDateEntity();
		if ( null != params.get("mnickname")){
			lastLoginDateEntity.setMnickname(params.get("mnickname").toString());
		}
		if ( null != params.get("username")){
			lastLoginDateEntity.setUsername(params.get("username").toString());
		}
		if ( null != params.get("ip")){
			lastLoginDateEntity.setIp(params.get("ip").toString());
		}
		if ( null != params.get("startdate") && null != params.get("enddate")){
			lastLoginDateEntity.setStartdate(params.get("startdate").toString());
			lastLoginDateEntity.setEnddate(params.get("enddate").toString());
		}

		List<MemberloginlogEntity> resp =new ArrayList<>();
		if (!onlineMembersWithDevices.isEmpty()) {
			for (Object obj : onlineMembersWithDevices.keySet()) {
				String key = obj.toString();
				String memberUUID = key.substring(0, key.indexOf("#"));
				String device = key.substring( key.indexOf("#")+1);
				log.info("获取当日在线用户数据,device:{};memberUUID:{}",device,memberUUID);
				if (params.get("device") != null && !params.get("device").toString().equals(device)) {
					continue;
				}
				Resp<MemberEntity> respMember = memberService.getMemberById(memberUUID);
				if (respMember.getData() == null) {
					continue;
				}
				lastLoginDateEntity.setMemberId(respMember.getData().getMemberid());
				MemberloginlogEntity memberloginlogEntity = memberloginlogMapper.getLastLoginDate(lastLoginDateEntity);
				resp.add(memberloginlogEntity);
			}
		}
		Query query = new Query(params);
		Page<MemberloginlogEntity> page = new Page<>(query);
//		log.info("ids:{}",ids);

//		List<MemberloginlogEntity> resp = memberloginlogMapper.listForPage(page, query);
		page.setRows(resp);
		return page;
	}

}
