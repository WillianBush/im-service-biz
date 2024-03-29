package net.chenlin.dp.modules.biz.member.dao;

import org.apache.ibatis.annotations.Mapper;

import net.chenlin.dp.modules.biz.member.entity.FriendsEntity;
import net.chenlin.dp.common.mapper.TkBaseMapper;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
        */
@Mapper
public interface FriendsMapper extends TkBaseMapper<FriendsEntity> {

    int removeFriend(FriendsEntity friendsEntity);
}
