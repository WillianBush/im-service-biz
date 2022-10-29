package net.chenlin.dp.modules.biz.member.service;

import java.util.List;
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

    Resp batchSaveMember(List<MemberEntity> member);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Resp<MemberEntity> getMemberById(String id);

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

    /**
     * 根据用户ID删除所有的聊天记录
     *  @param uid
     * @return
     */
    Resp removeAllHistoryMsgByUid(String uid);

    /**
     * 修改密码
     * @param member
     * @return
     */
    Resp updateMemberPass(MemberEntity member);

    /**
     * 根据群组ID 分页查询成员
     * @param params
     * @return
     */
    Page<MemberEntity> gettMemberByRoomId(Map<String, Object> params);

    /**
     * 分页查询
     * @param params
     * @return
     */
    Page<MemberEntity> listFriends(Map<String, Object> params);

    /**
     * 分页查询
     * @param params
     * @return
     */
    Page<MemberEntity> gettMemberByRoomIdForPage(Map<String, Object> params);

    /**
     * 数据总数量
     * @param
     * @return
     */
    Resp<Double> getTotalNumber();

}
