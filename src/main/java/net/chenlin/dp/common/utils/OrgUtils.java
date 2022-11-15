package net.chenlin.dp.common.utils;

import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.modules.sys.entity.DomainsEntity;
import net.chenlin.dp.modules.sys.service.DomainsService;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn("springContextUtils")
@Slf4j
public class OrgUtils {

    private static final DomainsService domainsService = (DomainsService) SpringContextUtils.getBean("domainsService");

    public static Long getOrgIdByDomain(String domain){
        DomainsEntity domainsEntity=domainsService.getDomainsByUrl(domain);
        if(null==domainsEntity){
            return 1L;
        }
        return domainsEntity.getOrg_id();
    }
}
