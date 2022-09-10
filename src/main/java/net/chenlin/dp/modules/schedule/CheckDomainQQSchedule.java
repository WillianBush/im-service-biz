package net.chenlin.dp.modules.schedule;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.utils.OKhttpUtil;
import net.chenlin.dp.modules.biz.domain.entity.DomainBlockEnum;
import net.chenlin.dp.modules.biz.domain.entity.DomainEnableEnum;
import net.chenlin.dp.modules.biz.domain.entity.DomainEntity;
import net.chenlin.dp.modules.biz.domain.service.DomainService;
import net.chenlin.dp.modules.schedule.entity.QiLingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component("CheckDomainQQSchedule")
@Slf4j
public class CheckDomainQQSchedule {

    private final static String CHECK_DOMAIN_QQ = "http://api.uouin.com/app/qq?username=sadiiv983&key=csl6kHZSDVztIUW&url=";
    private final static String CHECK_DOMAIN_WECHAT = "http://api.uouin.com/app/wx?username=sadiiv983&key=csl6kHZSDVztIUW&url=";

    private final DomainService domainService;

    @Autowired
    public CheckDomainQQSchedule(DomainService domainService) {
        this.domainService = domainService;
    }

    public void check() {

        List<DomainEntity> domainEnables = domainService.getDomainEnable();
        log.info("[check] 开始检测域名....  domainEnables.size:{}",domainEnables.size());
        for (DomainEntity domain : domainEnables) {
            String checkDomainQQ = CHECK_DOMAIN_QQ + domain.getDomainName();
            synchronized (this) {
                boolean blocked = doCheck(domain, checkDomainQQ);
            }
//            if ( !blocked) {
                // 微信检测不准，去掉
//                String checkDomainWechat = CHECK_DOMAIN_WECHAT + domain.getDomainName();
//                doCheck(domain, checkDomainWechat);
//            }
        }

        List<DomainEntity> domainEnablesChecked = domainService.getDomainEnable();
        log.info("[check] 检测域名完毕....  检测前:{}；检测后：{}",domainEnables.size(),domainEnablesChecked.size());
    }

    private boolean doCheck(DomainEntity domain, String checkDomain) {
        try {
            String resp = OKhttpUtil.httpGet(checkDomain, null);
            QiLingResponse response = JSONObject.parseObject(resp, QiLingResponse.class);
            if (response != null && !response.getStatu()) {
                DomainEntity domainEntity = new DomainEntity();
                domainEntity.setId(domain.getId());
                domainEntity.setIsBlocked(DomainBlockEnum.Blocked.getCode());
                domainEntity.setDomainEnable(DomainEnableEnum.Disable.getCode());
                domainEntity.setUpdateBy("系统检测");
                domainService.updateDomain(domainEntity);
                log.info("域名：{} 已经被封",domainEntity.getDomainName());
                if (response.getCount() != null || response.getDescribe() != null || response.getReason() != null) {
                    log.error("麒麟接口返回异常， resp:{}",response);
                }
                return true;
            }
            Thread.sleep(800);
        } catch (IOException | InterruptedException e) {
            log.error("异常,checkDomain:{}",checkDomain,e);
        }
        return false;
    }
}
