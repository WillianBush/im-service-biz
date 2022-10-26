package net.chenlin.dp.modules.biz.bussiness.service;

import net.chenlin.dp.common.entity.Resp;

public interface SendMessageService {


    /**
     * 给好友发送消息
     * @param userId
     * @param txt
     * @param imgPatch
     * @return
     */
    Resp sendMsgToFriends(String memberId,String txt,String imgPatch);
}
