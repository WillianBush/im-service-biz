package net.chenlin.dp.modules.biz.domain.dao;

import net.chenlin.dp.common.mapper.TkBaseMapper;
import net.chenlin.dp.modules.biz.domain.entity.DomainEntity;
import net.chenlin.dp.modules.biz.domain.entity.DomainLinkCounts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface DomainMapper extends TkBaseMapper<DomainEntity> {

   Integer selectDomainServer();

   List<DomainLinkCounts> listDomainLinkCounts(@Param("appBaseName") String appBaseName);
}
