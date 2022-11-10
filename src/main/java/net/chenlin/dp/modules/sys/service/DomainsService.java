package net.chenlin.dp.modules.sys.service;

import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.sys.entity.DomainsEntity;

public interface DomainsService {

    DomainsEntity getDomainsByUrl(String url);

    /**
     * 新增域名
     * @param domainsEntity
     * @return
     */
    R save(DomainsEntity domainsEntity);

    /**
     * 查询域名
     * @param org_id
     * @return
     */
    R getDomainByOrgId(int org_id);

}
