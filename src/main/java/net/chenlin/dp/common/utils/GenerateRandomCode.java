package net.chenlin.dp.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.modules.schedule.entity.QiLingResponse;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@Slf4j
public class GenerateRandomCode {

    private static final String STR = "0123456789abcdefghijklmnopqrstuvwxyz";
    private final static String CHECK_DOMAIN_QQ = "http://api.uouin.com/app/qq?username=sadiiv983&key=csl6kHZSDVztIUW&url=";

    private static final String SHORT_STR = "0123456789";
    private static final String[] SHORT_DOMAIN_STUFF = {"tv","pw","co","cc","nl","in","io","bz","im","it","nz","ph","pw","ws"};

    private static final String qqRegisterDomain = "syxicecream.com";

    /**
     *
     * @param passLength 数据书长度
     * @param strCount  生成多少个
     * @return
     */
    public static List<String> getRandomCode(int passLength, int strCount) {
        Random random = new Random();
        List<String> listString = new ArrayList<>();
        //生成10条长度为1-10的随机字符串
        for (int i = 0; i < strCount ; i++) {
            StringBuffer stringBuffer = new StringBuffer();
            //确定字符串长度
//            int stringLength = (int) (Math.random()* (passLength+1));
            for (int j = 0; j < passLength; j++) {
                //先随机生成初始定义的字符串 str 的某个索引，以获取相应的字符
                int index = random.nextInt(STR.length());
                char c = STR.charAt(index);
                stringBuffer.append(c);
            }
            //判断当前的list容器中是否已有刚生成的字符串，满足每条字符串不可重复性
            if (!(listString.contains(stringBuffer.toString()))) {
                listString.add(stringBuffer.toString());
            }else {
                i--;
            }
        }
        return listString;
    }


    /**
     *
     * @param passLength 数据书长度
     * @param strCount  生成多少个
     * @return
     */
    public static List<String> getRandomCodeMath(int passLength, int strCount) {
        Random random = new Random();
        List<String> listString = new ArrayList<>();
        //生成10条长度为1-10的随机字符串
        for (int i = 0; i < strCount ; i++) {
            StringBuffer stringBuffer = new StringBuffer();
            //确定字符串长度
//            int stringLength = (int) (Math.random()* (passLength+1));
            for (int j = 0; j < passLength; j++) {
                //先随机生成初始定义的字符串 str 的某个索引，以获取相应的字符
                int index = random.nextInt(SHORT_STR.length());
                char c = SHORT_STR.charAt(index);
                stringBuffer.append(c);
            }
            //判断当前的list容器中是否已有刚生成的字符串，满足每条字符串不可重复性
            if (!(listString.contains(stringBuffer.toString()))) {
                listString.add(stringBuffer.toString());
            }else {
                i--;
            }
        }
        return listString;
    }

    public static List<String> getRandomCodeDomain(int passLength, int strCount,String domainStuff) {
        List<String>  strs =  getRandomCode(passLength,strCount);
        List<String> listString = new ArrayList<>();
        for (String str : strs){
            listString.add(str +"." +domainStuff );
        }
        return listString;
    }

    public static List<String> getRandomCodeDomainMath(int passLength, int strCount,String domainStuff) {
        List<String>  strs =  getRandomCodeMath(passLength,strCount);
        List<String> listString = new ArrayList<>();
        for (String str : strs){
            listString.add(str +"." +domainStuff );
        }
        return listString;
    }

    public static void generateRandomAndChechedShortDoamins(){
        int count = 300;
        Set<String> shortLinkBeforeCheck = new HashSet<>();
        // 短域名后缀 15 个
        System.out.println("生成 300 个短域名");
        for (int i = 0; i <= count / SHORT_DOMAIN_STUFF.length; i++) {
            String str = getRandomCodeMath(4,1).get(0);
            for (String domainStuff : SHORT_DOMAIN_STUFF) {
                String domain = str + "." + domainStuff;
                shortLinkBeforeCheck.add(domain);
            }
        }
        List<String> shortLinkListChecked = check(new ArrayList<>(shortLinkBeforeCheck));
        for (String str :  shortLinkListChecked) {
            System.out.println(str);
        }
    }

    public static void  generateRandomAndChechedQQDoamins(){
        List<String> listString = getRandomCodeDomain(6,91,qqRegisterDomain);
        List<String> listStringNormal = new ArrayList<>();
        for(String domain : listString){
            try {
//                String checkDomainQQ = CHECK_DOMAIN_QQ + domain;
//                String resp = OKhttpUtil.httpGet(checkDomainQQ, null);
//                QiLingResponse response = JSONObject.parseObject(resp, QiLingResponse.class);
//                if (response != null && !response.getStatu()) {
//                    log.info("域名：{} 已经被封",domain);
//                    if (response.getCount() != null || response.getDescribe() != null || response.getReason() != null) {
//                        log.error("麒麟接口返回异常， resp:{}",response);
//                    }
//                }else {
//                    listStringNormal.add(domain);
//                }
                listStringNormal.add(domain);
//                Thread.sleep(1600);
            } catch (Exception e) {
                log.error("",e);
            }
        }
        for (String str :  listStringNormal) {
            System.out.println(str);
        }
    }


    public static void  generateRandomAndChechedqqRegisterDomain(){
        List<String> listString = getRandomCodeDomain(6,200,"xyz");
        List<String> listStringNormal = check(listString);
        for (String str :  listStringNormal) {
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        // 生成随机落地页
//        generateRandomAndChechedqqRegisterDomain();
        // 生成随机短域名
        generateRandomAndChechedShortDoamins();
        // 生成 qq绿标
//        generateRandomAndChechedQQDoamins();
    }



    public static List<String>  check(List<String> domainEnables) {

        int group = domainEnables.size()/ 25;
        int left = domainEnables.size() % 25;
        if (left != 0){
            group ++;
        }
        long start = System.currentTimeMillis();
        List<List<String>> domainEnableGroup = Lists.partition(domainEnables,group);

        // 微信检测不准，去掉
//                String checkDomainWechat = CHECK_DOMAIN_WECHAT + domain.getDomainName();
//                doCheck(domain, checkDomainWechat);
        CopyOnWriteArrayList<String> checked = new CopyOnWriteArrayList<>();
        for (List<String> domainSub : domainEnableGroup) {
            for (String domain : domainSub) {
                String checkDomainQQ = CHECK_DOMAIN_QQ + domain;
                CompletableFuture<String> f =  CompletableFuture.supplyAsync(()->doCheck( checkDomainQQ,domain));
                f.whenComplete(new BiConsumer<String, Throwable>() {
                    @Override
                    public void accept(String amap, Throwable throwable) {
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
        List<String> domainEnablesChecked = checked.stream().filter(it->it != null).collect(Collectors.toList());
        log.info("[check] 检测域名完毕....  检测前:{}；检测后：{}",domainEnables.size(),domainEnablesChecked.size());
        return domainEnablesChecked;
    }

    private static String doCheck( String checkDomain,String domain) {
        try {
            String resp = OKhttpUtil.httpGet(checkDomain, null);
            QiLingResponse response = JSONObject.parseObject(resp, QiLingResponse.class);
            if (response != null && !response.getStatu()) {
                log.info("域名：{} 已经被封",checkDomain);
                if (response.getCount() != null || response.getDescribe() != null || response.getReason() != null) {
                    log.error("麒麟接口返回异常， resp:{}",response);
                }
            }else {
                return domain;
            }
        } catch (IOException e) {
            log.error("异常,checkDomain:{}",domain,e);
        }
        return null;
    }
}
