package net.chenlin.dp.modules.biz.appDomain.dao;

import net.chenlin.dp.common.mapper.TkBaseMapper;
import net.chenlin.dp.modules.biz.appDomain.entity.AppDomainEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface AppDomainMapper extends TkBaseMapper<AppDomainEntity> {

    List<AppDomainEntity> getDomainsEnabled(@Param("domainType") Integer domainType,@Param("appResignedId")  Long appResignedId);

    AppDomainEntity getDomainsEnabledOne(@Param("domainType") Integer domainType,@Param("appResignedId")  Long appResignedId);

    List<AppDomainEntity>  getDomainsEnabledByBaseAppName(@Param("domainType") Integer domainType,@Param("appBaseName")  String appBaseName,@Param("count")  int count);

    List<AppDomainEntity>  getDomainsEnabledByBaseAppNameNoUse(@Param("domainType") Integer domainType,@Param("appBaseName")  String appBaseName,@Param("count")  int count,@Param("domainUsedNames")  List<String> domainUsedNames);
}
