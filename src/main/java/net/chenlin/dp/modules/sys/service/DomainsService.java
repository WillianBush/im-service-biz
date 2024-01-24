package net.chenlin.dp.modules.sys.service;

import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.sys.entity.DomainsEntity;

import java.util.Map;

public interface DomainsService {

    DomainsEntity getDomainsByUrl(String url);

    /**
     * 分页查询站点列表
     * @param params
     * @return
     */
    Page<DomainsEntity> listDomain(Map<String, Object> params);

    /**
     * 新增域名
     * @param domainsEntity
     * @return
     */
    R save(DomainsEntity domainsEntity);

    /**
     * 查询域名
     * @param orgId
     * @return
     */
    R getDomainByOrgId(Long orgId);

    /**
     * 修改站点域名
     * @param domainsEntity
     * @return
     */
    R update(DomainsEntity domainsEntity);

    /**
     * 删除站点
     * @param domain_id
     * @return
     */
    R remove(Long[] domain_id);
}
