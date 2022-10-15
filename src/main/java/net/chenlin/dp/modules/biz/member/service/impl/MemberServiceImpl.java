package net.chenlin.dp.modules.biz.member.service.impl;

import java.util.HashMap;
import java.util.Map;

import net.chenlin.dp.common.entity.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
import net.chenlin.dp.modules.biz.member.dao.MemberMapper;
import net.chenlin.dp.modules.biz.member.service.MemberService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
    private MemberMapper memberMapper;

    /**
     * 分页查询
     * @param params
     * @return
     */
	@Override
	public Page<MemberEntity> listMember(Map<String, Object> params) {
		Query query = new Query(params);
		Page<MemberEntity> page = new Page<>(query);
		page.setRows(memberMapper.listForPage(page, query));
		return page;
	}

    /**
     * 新增
     * @param member
     * @return
     */
	@Override
	public Resp saveMember(MemberEntity member) {
		//根据username查询是否存在
		Map para=new HashMap();
		para.put("username",member.getUsername());
		Page<MemberEntity> rsPage=listMember(para);
		if(rsPage.getRows().size()>0){
			return Resp.error(Resp.error,"用户已经存在");
		}
		int count = memberMapper.save(member);
		return CommonUtils.msgResp(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public Resp getMemberById(String id) {
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

}
