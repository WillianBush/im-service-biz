package net.chenlin.dp.common.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.modules.schedule.entity.QiLingResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class GenerateRandomCode {

    private static final String STR = "0123456789abcdefghijklmnopqrstuvwxyz";
    private final static String CHECK_DOMAIN_QQ = "http://api.uouin.com/app/qq?username=sadiiv983&key=csl6kHZSDVztIUW&url=";
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

    public static List<String> getRandomCodeDomain(int passLength, int strCount,String domainStuff) {
        List<String>  strs =  getRandomCode(passLength,strCount);
        List<String> listString = new ArrayList<>();
        for (String str : strs){
            listString.add(str +"." +domainStuff );
        }
        return listString;
    }

    public static void generateRandomAndChechedQQDoamins(){
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
                Thread.sleep(1600);
            } catch (Exception e) {
                log.error("",e);
            }
        }
        for (String str :  listStringNormal) {
            System.out.println(str);
        }
    }

}
