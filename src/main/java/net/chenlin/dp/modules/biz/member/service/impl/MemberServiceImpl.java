package net.chenlin.dp.modules.biz.member.service.impl;

import java.util.Map;

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
		memberMapper.listForPage(page, query);
		return page;
	}

    /**
     * 新增
     * @param member
     * @return
     */
	@Override
	public R saveMember(MemberEntity member) {
		int count = memberMapper.save(member);
		return CommonUtils.msg(count);
	}

    /**
     * 根据id查询
     * @param id
     * @return
     */
	@Override
	public R getMemberById(Long id) {
		MemberEntity member = memberMapper.getObjectById(id);
		return CommonUtils.msg(member);
	}

    /**
     * 修改
     * @param member
     * @return
     */
	@Override
	public R updateMember(MemberEntity member) {
		int count = memberMapper.update(member);
		return CommonUtils.msg(count);
	}

    /**
     * 删除
     * @param id
     * @return
     */
	@Override
	public R batchRemove(Long[] id) {
		int count = memberMapper.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

}
