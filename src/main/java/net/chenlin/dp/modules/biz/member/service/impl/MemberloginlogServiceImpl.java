package net.chenlin.dp.modules.biz.member.service.impl;

import java.util.Map;

import net.chenlin.dp.common.entity.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.member.entity.MemberloginlogEntity;
import net.chenlin.dp.modules.biz.member.dao.MemberloginlogMapper;
import net.chenlin.dp.modules.biz.member.service.MemberloginlogService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("memberloginlogService")
public class MemberloginlogServiceImpl implements MemberloginlogService {

	@Autowired
    private MemberloginlogMapper memberloginlogMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<MemberloginlogEntity> listMemberloginlog(Map<String, Object> params) {
		params.put("org_id", 1);
		Query query = new Query(params);
		Page<MemberloginlogEntity> page = new Page<>(query);
		page.setRows(memberloginlogMapper.listForPage(page, query));
		return page;
	}

    /**
     * 新增
     * @param memberloginlog
     * @return
     */
	@Override
	public Resp saveMemberloginlog(MemberloginlogEntity memberloginlog) {
		memberloginlog.setOrg_id(1);
		int count = memberloginlogMapper.save(memberloginlog);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp getMemberloginlogById(String id) {
		MemberloginlogEntity memberloginlog = memberloginlogMapper.getObjectById(id);
		return CommonUtils.msgResp(memberloginlog);
	}

    /**
     * 修改
     * @param memberloginlog
     * @return
     */
	@Override
	public Resp updateMemberloginlog(MemberloginlogEntity memberloginlog) {
		memberloginlog.setOrg_id(1);
		int count = memberloginlogMapper.update(memberloginlog);
		return CommonUtils.msgResp(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public Resp batchRemove(String[] id) {
		int count = memberloginlogMapper.batchRemove(id);
		return CommonUtils.msgResp(id, count);
	}

}
