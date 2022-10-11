package net.chenlin.dp.modules.biz.member.dao;

import org.apache.ibatis.annotations.Mapper;

import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
import net.chenlin.dp.common.mapper.TkBaseMapper;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface MemberMapper extends TkBaseMapper<MemberEntity> {
	
}