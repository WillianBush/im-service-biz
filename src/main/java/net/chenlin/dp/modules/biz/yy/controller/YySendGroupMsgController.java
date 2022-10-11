package net.chenlin.dp.modules.biz.yy.controller;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.yy.entity.YySendGroupMsgEntity;
import net.chenlin.dp.modules.biz.yy.service.YySendGroupMsgService;

/**
 * 运营-消息群发
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/yy_send_group_msg")
@Api(tags = "运营-消息群发")
public class YySendGroupMsgController extends AbstractController {
	

	private YySendGroupMsgService yySendGroupMsgService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "列表")
	public Page<YySendGroupMsgEntity> list(@RequestBody Map<String, Object> params) {
		return yySendGroupMsgService.listYySendGroupMsg(params);
	}
		
	/**
	 * 新增
	 * @param yySendGroupMsg
	 * @return
	 */
	@SysLog("新增运营-消息群发")
	@PostMapping("/save")
	@ApiOperation(value = "新增")
	public Resp<YySendGroupMsgEntity> save(@RequestBody YySendGroupMsgEntity yySendGroupMsg) {
		return yySendGroupMsgService.saveYySendGroupMsg(yySendGroupMsg);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public Resp<YySendGroupMsgEntity> getById(@RequestBody Long id) {
		return yySendGroupMsgService.getYySendGroupMsgById(id);
	}
	
	/**
	 * 修改
	 * @param yySendGroupMsg
	 * @return
	 */
	@SysLog("修改运营-消息群发")
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public Resp<Integer> update(@RequestBody YySendGroupMsgEntity yySendGroupMsg) {
		return yySendGroupMsgService.updateYySendGroupMsg(yySendGroupMsg);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除运营-消息群发")
	@PostMapping("/remove")
	@ApiOperation(value = "删除")
	public Resp batchRemove(@RequestBody Long[] id) {
		return yySendGroupMsgService.batchRemove(id);
	}
	
}
