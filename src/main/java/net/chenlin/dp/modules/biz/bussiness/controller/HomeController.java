package net.chenlin.dp.modules.biz.bussiness.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.bussiness.entity.YyPersonalMsgDayEntity;
import net.chenlin.dp.modules.biz.member.service.MemberService;
import net.chenlin.dp.modules.biz.room.service.MessageHistoryService;
import net.chenlin.dp.modules.biz.room.service.RoomService;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.modules.sys.entity.DomainsEntity;
import net.chenlin.dp.modules.sys.service.DomainsService;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/home_page_data")
@Api(tags = "首页-第一行统计数据")
@DependsOn("springContextUtils")
public class HomeController extends AbstractController {

    private MemberService memberService;

    private MessageHistoryService messageHistoryService;

    private RoomService roomService;



    /**
     * 用户总量
     * @param
     * @return
     */
    @SysLog("首页-用户总量")
    @PostMapping("/getMemberSum")
    @ApiOperation(value = "用户总量")
    public Resp<Long> getMemberSum() {
        return memberService.getTotalNumber(getServerName());
    }

    /**
     * 用户消息总量
     * @param
     * @return
     */
    @SysLog("首页-用户消息总量")
    @PostMapping("/getPersonalMessageSum")
    @ApiOperation(value = "用户消息总量")
    public Resp<Long> getPersonalMessageSum() {
        return Resp.ok(messageHistoryService.getPersonalMessageTotal(getServerName()));
    }

    /**
     * 群组消息总量
     * @param
     * @return
     */
    @SysLog("首页-群组消息总量")
    @PostMapping("/getGroupMessageSum")
    @ApiOperation(value = "群组消息总量")
    public Resp<Long> getGroupMessageSum() {
        return Resp.ok(messageHistoryService.getGroupMessageTotal(getServerName()));
    }

    /**
     * 房间总量
     * @param
     * @return
     */
    @SysLog("首页-房间总量")
    @PostMapping("/getRoomSum")
    @ApiOperation(value = "用户消息总量")
    public Resp<Long> getRoomSum() {
        return Resp.ok(roomService.getTotal(getServerName()));
    }
}
