package net.chenlin.dp.common.utils;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.entity.TelegramModel;
import net.chenlin.dp.common.entity.TelegramSendMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class TelegramUtil {

    private final TelegramModel telegramModel;

    private static final String TELEGRAM_URL = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s&disable_notification=false";

    @Autowired
    public TelegramUtil(TelegramModel telegramModel) {
        this.telegramModel = telegramModel;
    }

    public void sendMessage(String message) {
            String url = String.format(TELEGRAM_URL, telegramModel.getRobotToken(), telegramModel.getGroupId(), message);
            try {
                String resp = OKhttpUtil.httpGet(url, null);
                TelegramSendMessageResponse response = JSONObject.parseObject(resp, TelegramSendMessageResponse.class);
                log.info("telegram 发送消息:{}",response);
            } catch (IOException e) {
                log.info("telegram 发送消息失败", e);
            }
    }

//    public static void main(String[] args) {
//        //   robotToken: 5509412236:AAEVKk13si4gxudv7KA79JFXtiyuByhaXjk
//        //  groupId: -1001718074576
//        String url = String.format(TELEGRAM_URL, "5509412236:AAEVKk13si4gxudv7KA79JFXtiyuByhaXjk", "-1001718074576", "消息测试......请忽略, 域名检测,发现有 20 个域名已经QQ非官方 @haoke2022");
//
//
//    }
}
