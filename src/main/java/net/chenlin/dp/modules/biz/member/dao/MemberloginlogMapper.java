package net.chenlin.dp.modules.biz.member.dao;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Query;
import net.chenlin.dp.modules.biz.bussiness.entity.YyMOnlineDayEntity;
import net.chenlin.dp.modules.biz.member.entity.LastLoginDateEntity;
import org.apache.ibatis.annotations.Mapper;

import net.chenlin.dp.modules.biz.member.entity.MemberloginlogEntity;
import net.chenlin.dp.common.mapper.TkBaseMapper;

import java.util.List;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface MemberloginlogMapper extends TkBaseMapper<MemberloginlogEntity> {

    List<YyMOnlineDayEntity> getObjectGroupByDate(Query query);

    MemberloginlogEntity getLastLoginDate(LastLoginDateEntity lastLoginDateEntity);
}
