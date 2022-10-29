package net.chenlin.dp.modules.biz.bussiness.service;

import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;

public interface SendMessageService {


    /**
     * 给好友发送消息
     * @param memberId
     * @param txt
     * @param imgPatch
     * @return
     */
    Resp sendMsgToFriends(String memberId, String txt, String imgPatch, SysUserEntity sysUserEntity);
}
