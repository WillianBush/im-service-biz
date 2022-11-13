package net.chenlin.dp.modules.sys.dao;

import net.chenlin.dp.common.mapper.TkBaseMapper;
import net.chenlin.dp.modules.sys.entity.DomainsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统日志
 * @author wang<fangyuan.co@outlook.com>
 */
@Mapper
public interface DomainsMapper extends TkBaseMapper<DomainsEntity> {

	DomainsEntity selectObj(DomainsEntity domainsEntity);

	DomainsEntity getDomainByOrgId(@Param("org_id") int org_id);
}
