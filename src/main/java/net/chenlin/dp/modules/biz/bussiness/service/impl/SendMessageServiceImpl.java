package net.chenlin.dp.modules.biz.bussiness.service.impl;

import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import net.chenlin.dp.common.constant.MessageType;
import net.chenlin.dp.common.constant.RedisCacheKeys;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.mq.RabbitmqConfig;
import net.chenlin.dp.common.support.redis.RedisCacheManager;
import net.chenlin.dp.common.utils.SnowFlakeIdWorker;
import net.chenlin.dp.modules.biz.bussiness.dao.WaitsendmessageMapper;
import net.chenlin.dp.modules.biz.bussiness.entity.ChatMsgEntity;
import net.chenlin.dp.modules.biz.bussiness.entity.WaitsendmessageEntity;
import net.chenlin.dp.modules.biz.bussiness.service.SendMessageService;
import net.chenlin.dp.modules.biz.member.dao.MemberMapper;
import net.chenlin.dp.modules.biz.member.entity.MemberEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class SendMessageServiceImpl implements SendMessageService {

    private RedisCacheManager redisCacheManager;

    private RabbitTemplate rabbitTemplate;

    private MemberMapper memberMapper;

    private WaitsendmessageMapper waitsendmessageMapper;


    @Override
    public Resp sendMsgToFriends(String userId, String txt, String imgPatch) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm");
        /**获取发送人信息*/
        MemberEntity fromMember=memberMapper.getObjectById(userId);
        if(null==fromMember){return Resp.error("发送人不存在");}
        /**获取发送人所有的好友，排除掉官方团队*/
        List<MemberEntity> listFriend=memberMapper.getFriendsByMid(userId);
        listFriend.forEach(memberEntity -> {
            //排除掉官方团队
            if(!"-1".equals(memberEntity.getId())) {
                ChatMsgEntity chatMsgEntity = new ChatMsgEntity();
                chatMsgEntity.setChatid(memberEntity.getId());
                chatMsgEntity.setDate(sdf.format(new Date()));
                chatMsgEntity.setDateTime(System.currentTimeMillis());
                chatMsgEntity.setFromHeadpic(fromMember.getHeadpic());
                chatMsgEntity.setFromUid(fromMember.getId());
                chatMsgEntity.setFromName(fromMember.getNickname());
                chatMsgEntity.setOldTxt(txt);
                chatMsgEntity.setSimple_content(txt);
                chatMsgEntity.setTxt(txt);
                chatMsgEntity.setToUid(memberEntity.getId());
                chatMsgEntity.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
                /**如果没发送到队列，则保存到数据库*/
                if(!processRemoteUser(chatMsgEntity)){
                    saveWaitSendMsg(chatMsgEntity);
                }
            }
        });
        return null;
    }

    /***
     * 保存到待发送表
     * @param cme
     */
    public void saveWaitSendMsg(ChatMsgEntity cme){
        SnowFlakeIdWorker sfw=new SnowFlakeIdWorker(1);
        WaitsendmessageEntity wsm=JSON.parseObject(JSON.toJSONString(cme),WaitsendmessageEntity.class);
        wsm.setOldContent(cme.getOldTxt());
        wsm.setHeadpic(cme.getFromHeadpic());
        wsm.setName(cme.getFromName());
        wsm.setId(sfw.createId());
        wsm.setContent(cme.getTxt());
        wsm.setCreateDate(new Date());
        wsm.setDate(cme.getDate());
        wsm.setType(MessageType.USER_TXT.name());
        wsm.setPsr(cme.getPsr());
        waitsendmessageMapper.save(wsm);
    }


    /**
     * 对发送对象为其它服务器时将信息发送到队列进行处理
     * @param bean {"aite":"","chatid":"4028a0838379f2ac01837a607a0a0001","date":"22/10/25 20:39","dateTime":1666701540281,
     * "fromHeadpic":"/img_sys/defaultHeadPic.jpg","fromName":"mu_test1","fromUid":"2c918082831d36b801833b67ed7f002e",
     * "oldTxt":"啊啊","psr":"","read":0,"simple_content":"啊啊","toUid":"4028a0838379f2ac01837a607a0a0001","txt":"啊啊",
     * "uuid":"ab227c3a1-877b-4f7c-9c42-f73e96f1d197"}
     */
    public boolean processRemoteUser(ChatMsgEntity bean) {
        //从redis缓存中取出来，如果存在，则判断所在服务器
        Object ipObj= redisCacheManager.hget(RedisCacheKeys.REDIS_WSS_KEY,(bean.getToUid()));
        if(ipObj!=null){
                rabbitTemplate.convertAndSend(RabbitmqConfig.BASIC_EXCHANGE,
                        RabbitmqConfig.USER_ROUTING_KEY+ "." + ipObj.toString(), JSON.toJSONString(bean));
                return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
