package net.chenlin.dp.modules.biz.member.dao;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.biz.bussiness.entity.YyMRegisterDayEntity;
import net.chenlin.dp.modules.biz.employee.entity.EmployeeEntity;
import org.apache.ibatis.annotations.Mapper;

import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
import net.chenlin.dp.common.mapper.TkBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface MemberMapper extends TkBaseMapper<MemberEntity> {

    List<MemberEntity> getByIds(Object[] id);

    List<YyMRegisterDayEntity> getObjectGroupByDate(Query query);

    List<MemberEntity> getFriendsByMid(@Param("mid") String mid);
    
    MemberEntity getMemberByMid(@Param("memberId") String memberId);

    List<MemberEntity> getMemberByRoomIdForPage(Page<MemberEntity> page, Query query);

    List<MemberEntity> listForPageByFriend(Page<MemberEntity> page,Query query);

    Long getTotal(Long orgId);

    List<MemberEntity> getRobotList(@Param("room_id") String room_id,@Param("count") Integer count);


    MemberEntity getByUsername(@Param("username") String username);

    Long isExitByNickname(@Param("nickName") String nickName);
}
