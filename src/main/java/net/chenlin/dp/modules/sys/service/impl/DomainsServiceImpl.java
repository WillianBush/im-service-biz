package net.chenlin.dp.modules.sys.service.impl;

import net.chenlin.dp.modules.sys.dao.DomainsMapper;
import net.chenlin.dp.modules.sys.entity.DomainsEntity;
import net.chenlin.dp.modules.sys.service.DomainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("domainsService")
public class DomainsServiceImpl implements DomainsService {
    @Autowired
    private DomainsMapper domainsMapper;
    @Override
    public DomainsEntity getDomainsByUrl(String url) {
        DomainsEntity de=new DomainsEntity();
        de.setHttp_domain(url);
        DomainsEntity rs=domainsMapper.selectObj(de);
        return rs;
    }
}
