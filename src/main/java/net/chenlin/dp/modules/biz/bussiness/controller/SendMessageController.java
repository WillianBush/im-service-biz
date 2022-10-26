package net.chenlin.dp.modules.biz.bussiness.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.bussiness.service.SendMessageService;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sendmsg")
@Api(tags = "消息发送控制器")
@DependsOn("springContextUtils")
public class SendMessageController {

    private SendMessageService sendMessageService;

    /**
     * 新增
     * @return
     */
    @SysLog("群发好友")
    @PostMapping("/sendFriends")
    @ApiOperation(value = "群发好友")
    public Resp sendMsgToFriends(@RequestParam String memberId,@RequestParam String txt,@RequestParam String imgPatch) {
        return sendMessageService.sendMsgToFriends(memberId,txt,imgPatch);
    }
}
