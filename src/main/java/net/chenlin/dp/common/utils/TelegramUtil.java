package net.chenlin.dp.common.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.entity.TelegramModel;
import net.chenlin.dp.common.entity.TelegramSendMessageResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class TelegramUtil {

    private TelegramModel telegramModel;

    private static final String TELEGRAM_URL = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s&disable_notification=false";

    public void sendMessage(String message) {
        synchronized (this) {
            String url = String.format(TELEGRAM_URL, telegramModel.getRobotToken(), telegramModel.getGroupId(), message);
            try {
                String resp = OKhttpUtil.httpGet(url, null);
                TelegramSendMessageResponse response = JSONObject.parseObject(resp, TelegramSendMessageResponse.class);
                log.info("telegram 发送消息:{}",response);
            } catch (IOException e) {
                log.info("", e);
            }
        }
    }

    public static void main(String[] args) {
        //   robotToken: 5509412236:AAEVKk13si4gxudv7KA79JFXtiyuByhaXjk
        //  groupId: -1001718074576
        String url = String.format(TELEGRAM_URL, "5509412236:AAEVKk13si4gxudv7KA79JFXtiyuByhaXjk", "-1001718074576", "消息测试......请忽略, 域名检测,发现有 20 个域名已经QQ非官方 @haoke2022");


    }
}
