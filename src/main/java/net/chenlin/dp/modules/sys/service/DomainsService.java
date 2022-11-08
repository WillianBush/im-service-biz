package net.chenlin.dp.modules.sys.service;

import net.chenlin.dp.modules.sys.entity.DomainsEntity;

public interface DomainsService {

    DomainsEntity getDomainsByUrl(String url);
}
