package net.chenlin.dp.common.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.modules.schedule.entity.QiLingResponse;

import java.util.*;

@Slf4j
public class GenerateRandomCode {

    private static final String STR = "0123456789abcdefghijklmnopqrstuvwxyz";
    private final static String CHECK_DOMAIN_QQ = "http://api.uouin.com/app/qq?username=sadiiv983&key=csl6kHZSDVztIUW&url=";

    private static final String SHORT_STR = "0123456789";
    private static final String[] SHORT_DOMAIN_STUFF = {"tv","pw","co","cc","nl","in","io","bz","im","it","la","nz","ph"
    ,"pw","ws"};

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
        Set<String> listStringNormal = new HashSet<>();
        // 短域名后缀 15 个
        System.out.println("生成 300 个短域名");
        for (int i = 0; i <= count / SHORT_DOMAIN_STUFF.length; i++) {
            String str = getRandomCodeMath(4,1).get(0);
            for (String domainStuff : SHORT_DOMAIN_STUFF) {
                String domain = str + "." + domainStuff;
                try {
                    String checkDomainQQ = CHECK_DOMAIN_QQ + domain;
                    String resp = OKhttpUtil.httpGet(checkDomainQQ, null);
                    QiLingResponse response = JSONObject.parseObject(resp, QiLingResponse.class);
                    if (response != null && !response.getStatu()) {
                        log.info("域名：{} 已经被封",domain);
                        if (response.getCount() != null || response.getDescribe() != null || response.getReason() != null) {
                            log.error("麒麟接口返回异常， resp:{}",response);
                        }
                    }else {
                        listStringNormal.add(domain);
                    }

//                    listStringNormal.add(domain);
                    Thread.sleep(1600);
                } catch (Exception e) {
                    log.error("",e);
                }
            }
        }
        for (String str :  listStringNormal) {
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
        List<String> listStringNormal = new ArrayList<>();
        for(String domain : listString){
            try {
                String checkDomainQQ = CHECK_DOMAIN_QQ + domain;
                String resp = OKhttpUtil.httpGet(checkDomainQQ, null);
                QiLingResponse response = JSONObject.parseObject(resp, QiLingResponse.class);
                if (response != null && !response.getStatu()) {
                    log.info("域名：{} 已经被封",domain);
                    if (response.getCount() != null || response.getDescribe() != null || response.getReason() != null) {
                        log.error("麒麟接口返回异常， resp:{}",response);
                    }
                }else {
                    listStringNormal.add(domain);
                }
//                listStringNormal.add(domain);
                Thread.sleep(1600);
            } catch (Exception e) {
                log.error("",e);
            }
        }
        for (String str :  listStringNormal) {
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        // 生成随机落地页
//        generateRandomAndChechedQQDoamins();
        // 生成随机短域名
//        generateRandomAndChechedShortDoamins();
        // 生成 qq绿标
        generateRandomAndChechedQQDoamins();
    }

}
