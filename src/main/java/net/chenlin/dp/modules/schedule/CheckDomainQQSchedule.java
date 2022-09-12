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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

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
        long start = System.currentTimeMillis();
        log.info("[check] 开始检测域名....  domainEnables.size:{}",domainEnables.size());
        telegramUtil.sendMessage("[小缘自动检测] 开始检测域名....  域名数量:"+domainEnables.size());
        List<List<DomainEntity>> domainEnableGroup = Lists.partition(domainEnables,group);

        // 微信检测不准，去掉
//                String checkDomainWechat = CHECK_DOMAIN_WECHAT + domain.getDomainName();
//                doCheck(domain, checkDomainWechat);
        CopyOnWriteArrayList<Map<String,String>> checked = new CopyOnWriteArrayList<>();
        for (List<DomainEntity> domainSub : domainEnableGroup) {
            for (DomainEntity domain : domainSub) {
                String checkDomainQQ = CHECK_DOMAIN_QQ + domain.getDomainName();
                CompletableFuture<Map<String,String>>  f =  CompletableFuture.supplyAsync(()->doCheck(domain, checkDomainQQ));
                f.whenComplete(new BiConsumer<Map<String,String>, Throwable>() {
                    @Override
                    public void accept(Map<String,String> amap, Throwable throwable) {
                        checked.add(amap);
                    }
                });
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    log.error("",e);
                }
            }
            try {
                Thread.sleep(1100);
            } catch (InterruptedException e) {
                log.error("",e);
            }
        }
        while (checked.size() != domainEnables.size()){
            log.info("正在执行,请等待结果 ...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.error("",e);
            }
        }
        long spendSeconds = (System.currentTimeMillis() -start) / 1000 ;
        List<DomainEntity> domainEnablesChecked = domainService.getDomainEnable();
        log.info("[check] 检测域名完毕....  检测前:{}；检测后：{}",domainEnables.size(),domainEnablesChecked.size());
        long shortDomainSize = domainEnables.stream().filter(it-> DomainEnum.AdvertiseDomain.getCode().equals(it.getDomainType())).count();
        long shortDomainSizeChecked = domainEnablesChecked.stream().filter(it-> DomainEnum.AdvertiseDomain.getCode().equals(it.getDomainType())).count();
        StringBuilder message = new StringBuilder("[小缘自动检测] 检测域名完毕....,耗时:" + spendSeconds + " 秒 ;检测前:" + domainEnables.size() + "；检测后：" + domainEnablesChecked.size() + ";短域名检测前:" + shortDomainSize + ";短域名检测后:" + shortDomainSizeChecked + " @haoke2022 @dubiying ");
        List<Map<String,String>> mapList = checked.stream().filter(it->!it.isEmpty()).collect(Collectors.toList());
        if (!mapList.isEmpty()) {
            message.append("[以下已经非官方域名: ");
            for (Map<String, String> map : mapList) {
                for (String key :  map.keySet()) {
                    message.append(key).append(":").append(map.get(key)).append("; ");
                }
            }
            message.append("]");
        }
        log.info("telegram 发送消息:{}", message);
        telegramUtil.sendMessage(message.toString());

    }

    private Map<String,String> doCheck(DomainEntity domain, String checkDomain) {
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
                    if (domainEntity.getDomainType().equals(DomainEnum.AdvertiseDomain.getCode())) {
                        return Collections.singletonMap(domainEntity.getAppName(), domainEntity.getDomainName());
                    }
                }
                return Collections.emptyMap();
            } catch (IOException e) {
                log.error("异常,checkDomain:{}",checkDomain,e);
            }
        return Collections.emptyMap();
    }
}
