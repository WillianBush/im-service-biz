package net.chenlin.dp.modules.biz.member.dao;

import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.biz.bussiness.entity.YyMRegisterDayEntity;
import org.apache.ibatis.annotations.Mapper;

import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
import net.chenlin.dp.common.mapper.TkBaseMapper;

import java.util.List;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface MemberMapper extends TkBaseMapper<MemberEntity> {

    List<MemberEntity> getByIds(Object[] id);

    List<YyMRegisterDayEntity> getObjectGroupByDate(Query query);

    List<MemberEntity> getFriendsByMid(String mid);
    
    MemberEntity getMemberByMid(String mid);
}
