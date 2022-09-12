package net.chenlin.dp.modules.schedule;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.utils.OKhttpUtil;
import net.chenlin.dp.common.utils.TelegramUtil;
import net.chenlin.dp.modules.biz.domain.entity.DomainBlockEnum;
import net.chenlin.dp.modules.biz.domain.entity.DomainEnableEnum;
import net.chenlin.dp.modules.biz.domain.entity.DomainEntity;
import net.chenlin.dp.modules.biz.domain.entity.DomainEnum;
import net.chenlin.dp.modules.biz.domain.service.DomainService;
import net.chenlin.dp.modules.schedule.entity.QiLingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;

@Component("CheckDomainQQSchedule")
@Slf4j
public class CheckDomainQQSchedule {

    private final static String CHECK_DOMAIN_QQ = "http://api.uouin.com/app/qq?username=sadiiv983&key=csl6kHZSDVztIUW&url=";
    private final static String CHECK_DOMAIN_WECHAT = "http://api.uouin.com/app/wx?username=sadiiv983&key=csl6kHZSDVztIUW&url=";

    private final DomainService domainService;

    private final TelegramUtil telegramUtil;

    @Autowired
    public CheckDomainQQSchedule(DomainService domainService,TelegramUtil telegramUtil) {
        this.domainService = domainService;
        this.telegramUtil = telegramUtil ;
    }

    public void check() {

        List<DomainEntity> domainEnables = domainService.getDomainEnable();
        int group = domainEnables.size()/ 25;
        int left = domainEnables.size() % 25;
        if (left != 0){
            group ++;
        }
        log.info("[check] 开始检测域名....  domainEnables.size:{}",domainEnables.size());
        telegramUtil.sendMessage("测试[小缘自动检测] 开始检测域名....  域名数量:"+domainEnables.size());
        List<List<DomainEntity>> domainEnableGroup = Lists.partition(domainEnables,group);

        // 微信检测不准，去掉
//                String checkDomainWechat = CHECK_DOMAIN_WECHAT + domain.getDomainName();
//                doCheck(domain, checkDomainWechat);
        CopyOnWriteArrayList<Boolean> checked = new CopyOnWriteArrayList<>();
        for (List<DomainEntity> domainSub : domainEnableGroup) {
            for (DomainEntity domain : domainEnables) {
                String checkDomainQQ = CHECK_DOMAIN_QQ + domain.getDomainName();
                CompletableFuture<Boolean>  f =  CompletableFuture.supplyAsync(()->doCheck(domain, checkDomainQQ));
                f.whenComplete(new BiConsumer<Boolean, Throwable>() {
                    @Override
                    public void accept(Boolean aBoolean, Throwable throwable) {
                        checked.add(aBoolean);
                    }
                });
            }
            try {
                Thread.sleep(1100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (checked.size() != domainEnables.size()){
            log.info("正在执行,请等待结果 ...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        List<DomainEntity> domainEnablesChecked = domainService.getDomainEnable();
        log.info("[check] 检测域名完毕....  检测前:{}；检测后：{}",domainEnables.size(),domainEnablesChecked.size());
        long shortDomainSize = domainEnables.stream().filter(it-> DomainEnum.AdvertiseDomain.getCode().equals(it.getDomainType())).count();
        long shortDomainSizeChecked = domainEnablesChecked.stream().filter(it-> DomainEnum.AdvertiseDomain.getCode().equals(it.getDomainType())).count();
        String message = "测试:[小缘自动检测] 检测域名完毕....  检测前:"+domainEnables.size()+"；检测后："+ domainEnablesChecked.size()+";短域名检测前:" +shortDomainSize +";短域名检测后:" +  shortDomainSizeChecked+" @haoke2022 @dubiying ";
        log.info("telegram 发送消息:{}",message);
        telegramUtil.sendMessage(message);

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
                }
                return true;
            } catch (IOException e) {
                log.error("异常,checkDomain:{}",checkDomain,e);
            }
        return false;
    }
}
