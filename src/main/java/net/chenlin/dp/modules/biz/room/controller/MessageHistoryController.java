package net.chenlin.dp.modules.biz.room.controller;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.context.annotation.DependsOn;
import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.room.entity.MessageHistoryEntity;
import net.chenlin.dp.modules.biz.room.service.MessageHistoryService;
/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mr/msghistory")
@Api(tags = "聊天记录")
@DependsOn("springContextUtils")
public class MessageHistoryController extends AbstractController {


    private MessageHistoryService messageHistoryService;

    /**
     * 列表
     * @param params
     * @return
     */
    @PostMapping("/list")
    @ApiOperation(value = "列表")
    public Page<MessageHistoryEntity> list(@RequestBody Map<String, Object> params) {
        return messageHistoryService.listMessageHistory(params);
    }

    /**
     * 新增
     * @param messageHistory
     * @return
     */
    @SysLog("新增")
    @PostMapping("/save")
    @ApiOperation(value = "新增")
    public Resp<MessageHistoryEntity> save(@RequestBody MessageHistoryEntity messageHistory) {
        return messageHistoryService.saveMessageHistory(messageHistory);
    }

    /**
     * 根据id查询详情
     * @param id
     * @return
     */
    @GetMapping("/info")
    @ApiOperation(value = "根据id查询详情")
    public Resp<MessageHistoryEntity> getById(@RequestBody Long id) {
        return messageHistoryService.getMessageHistoryById(id);
    }

    /**
     * 修改
     * @param messageHistory
     * @return
     */
    @SysLog("修改")
    @PostMapping("/update")
    @ApiOperation(value = "修改")
    public Resp<Integer> update(@RequestBody MessageHistoryEntity messageHistory) {
        return messageHistoryService.updateMessageHistory(messageHistory);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @SysLog("删除")
    @PostMapping("/remove")
    @ApiOperation(value = "删除")
    public Resp batchRemove(@RequestBody Long[] id) {
        return messageHistoryService.batchRemove(id);
    }

}
