package net.chenlin.dp.modules.biz.bussiness.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import net.chenlin.dp.common.constant.RedisCacheKeys;
import net.chenlin.dp.common.support.redis.RedisCacheManager;
import net.chenlin.dp.modules.biz.member.dao.MemberMapper;
import net.chenlin.dp.modules.biz.member.dao.MemberloginlogMapper;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
import net.chenlin.dp.modules.biz.member.entity.MemberloginlogEntity;
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
public class YyMOnlineDayServiceImpl implements YyMOnlineDayService {

    private YyMOnlineDayMapper yyMOnlineDayMapper;

    private RedisCacheManager redisCacheManager;

	private MemberloginlogMapper memberloginlogMapper;
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
		Map map=  redisCacheManager.hmget(RedisCacheKeys.ONLINE_MEMBER);
		List<String> ids = new ArrayList<>();
		map.keySet().stream().forEach(k-> {
			String key = k.toString();
			if (params.get("device") != null) {
				if (key.indexOf(params.get("device").toString()) >= 0) {
					ids.add(key.substring(0, key.indexOf("#")));
				}
			} else {
				ids.add(key.substring(0, key.indexOf("#")));
			}
		});
		if (ids.isEmpty()) {
			return new Page<>();
		}
		params.put("ids",ids);
		Query query = new Query(params);
		Page<MemberloginlogEntity> page = new Page<>(query);
		List<MemberloginlogEntity> resp = memberloginlogMapper.listForPage(page, query);
		page.setRows(resp);
		return page;
	}

}
