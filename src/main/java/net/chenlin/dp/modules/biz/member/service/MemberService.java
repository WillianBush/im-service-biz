package net.chenlin.dp.modules.biz.member.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface MemberService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<MemberEntity> listMember(Map<String, Object> params);

    /**
     * 新增
     * @param member
     * @return
     */
    Resp saveMember(MemberEntity member);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Resp getMemberById(String id);

    /**
     * 修改
     * @param member
     * @return
     */
    Resp updateMember(MemberEntity member);

    /**
     * 删除
     * @param id
     * @return
     */
    Resp batchRemove(String[] id);
	
}
