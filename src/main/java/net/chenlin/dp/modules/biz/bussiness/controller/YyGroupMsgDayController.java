package net.chenlin.dp.modules.biz.bussiness.controller;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.chenlin.dp.modules.biz.bussiness.entity.YySendGroupMsgEntity;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.bussiness.entity.YyGroupMsgDayEntity;
import net.chenlin.dp.modules.biz.bussiness.service.YyGroupMsgDayService;

/**
 * 运营-每日群消息数量统计
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/yy_group_msg_day")
@Api(tags = "运营-每日群消息数量统计")
@DependsOn("springContextUtils")
public class YyGroupMsgDayController extends AbstractController {
	

	private YyGroupMsgDayService yyGroupMsgDayService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "列表")
	public Resp<List<YyGroupMsgDayEntity>> list(@RequestBody Map<String, Object> params) {
		params.put("domain",getServerName());
		return yyGroupMsgDayService.listYyGroupMsgDay(params);
	}
		
	/**
	 * 新增
	 * @param yyGroupMsgDay
	 * @return
	 */
	@SysLog("新增运营-每日群消息数量统计")
	@PostMapping("/save")
	@ApiOperation(value = "新增")
	public Resp<YyGroupMsgDayEntity> save(@RequestBody YyGroupMsgDayEntity yyGroupMsgDay) {
		return yyGroupMsgDayService.saveYyGroupMsgDay(yyGroupMsgDay);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public Resp<YyGroupMsgDayEntity> getById(@RequestBody Long id) {
		return yyGroupMsgDayService.getYyGroupMsgDayById(id);
	}
	
	/**
	 * 修改
	 * @param yyGroupMsgDay
	 * @return
	 */
	@SysLog("修改运营-每日群消息数量统计")
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public Resp<Integer> update(@RequestBody YyGroupMsgDayEntity yyGroupMsgDay) {
		return yyGroupMsgDayService.updateYyGroupMsgDay(yyGroupMsgDay);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除运营-每日群消息数量统计")
	@PostMapping("/remove")
	@ApiOperation(value = "删除")
	public Resp batchRemove(@RequestBody Long[] id) {
		return yyGroupMsgDayService.batchRemove(id);
	}
	
}
