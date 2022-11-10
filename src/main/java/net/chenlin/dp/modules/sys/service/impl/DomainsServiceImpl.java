package net.chenlin.dp.modules.sys.service.impl;

import com.aliyun.oss.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.CommonUtils;
import net.chenlin.dp.modules.sys.dao.DomainsMapper;
import net.chenlin.dp.modules.sys.entity.DomainsEntity;
import net.chenlin.dp.modules.sys.service.DomainsService;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service("domainsService")
public class DomainsServiceImpl implements DomainsService {

    private DomainsMapper domainsMapper;

    @Override
    public DomainsEntity getDomainsByUrl(String url) {
        DomainsEntity de = new DomainsEntity();
        de.setHttp_domain(url);
        DomainsEntity rs = domainsMapper.selectObj(de);
        return rs;
    }

    @Override
    public R save(DomainsEntity domainsEntity) {
        if (StringUtils.isNullOrEmpty(domainsEntity.getOrg_id().toString())
                || StringUtils.isNullOrEmpty(domainsEntity.getHttp_domain())
                || StringUtils.isNullOrEmpty(domainsEntity.getWs_domain())
                || StringUtils.isNullOrEmpty(domainsEntity.getOss_domain())) {
            return CommonUtils.msg("参数不全");
        }

        int count = domainsMapper.save(domainsEntity);
        return CommonUtils.msg(count);
    }

    @Override
    public R getDomainByOrgId(int org_id) {
        DomainsEntity domainsEntity = domainsMapper.getDomainByOrgId(org_id);
        return CommonUtils.msg(domainsEntity);
    }
}
