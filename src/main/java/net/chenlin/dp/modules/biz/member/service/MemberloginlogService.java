package net.chenlin.dp.modules.biz.member.service;

import java.util.Map;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.member.entity.MemberloginlogEntity;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
public interface MemberloginlogService {

    /**
     * 分页查询
     * @param params
     * @return
     */
	Page<MemberloginlogEntity> listMemberloginlog(Map<String, Object> params);

    /**
     * 新增
     * @param memberloginlog
     * @return
     */
    Resp saveMemberloginlog(MemberloginlogEntity memberloginlog,String domain);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Resp getMemberloginlogById(String id);

    /**
     * 修改
     * @param memberloginlog
     * @return
     */
    Resp updateMemberloginlog(MemberloginlogEntity memberloginlog);

    /**
     * 删除
     * @param id
     * @return
     */
    Resp batchRemove(String[] id);
	
}
