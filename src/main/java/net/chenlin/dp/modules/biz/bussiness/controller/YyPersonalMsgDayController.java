package net.chenlin.dp.modules.biz.bussiness.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.chenlin.dp.modules.biz.bussiness.entity.YyGroupMsgDayEntity;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.bussiness.entity.YyPersonalMsgDayEntity;
import net.chenlin.dp.modules.biz.bussiness.service.YyPersonalMsgDayService;

/**
 * 运营-每次私发消息统计
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/yy_personal_msg_day")
@Api(tags = "运营-每次私发消息统计")
@DependsOn("springContextUtils")
public class YyPersonalMsgDayController extends AbstractController {
	

	private YyPersonalMsgDayService yyPersonalMsgDayService;
	
	/**
	 * 私聊消息统计
	 * @param params
	 * @return
	 */
	@PostMapping("/personalList")
	@ApiOperation(value = "二人私发消息汇总")
	public Resp<List<YyPersonalMsgDayEntity>> personalList(@RequestBody Map<String, Object> params) {
		String type = (String) params.get("type");
		if (type.isEmpty()) {
			return Resp.error(500, "参数错误");
		}
		params.put("domain",getServerName());
		return yyPersonalMsgDayService.listYyPersonalMsgDay(params);
	}

	/**
	 * 私聊消息统计
	 * @param params
	 * @return
	 */
	@PostMapping("/groupList")
	@ApiOperation(value = "群发消息汇总")
	public Resp<YyGroupMsgDayEntity> groupList(@RequestBody Map<String, Object> params) {
		String type = (String) params.get("type");
		if (type.isEmpty()) {
			return Resp.error("参数错误");
		}
		params.put("domain",getServerName());
		List<YyGroupMsgDayEntity> list = yyPersonalMsgDayService.listYyGroupMsgDay(params);
		return Resp.ok(list);
	}
		
	/**
	 * 新增
	 * @param yyPersonalMsgDay
	 * @return
	 */
	@SysLog("新增运营-每次私发消息统计")
	@PostMapping("/save")
	@ApiOperation(value = "新增")
	public Resp<YyPersonalMsgDayEntity> save(@RequestBody YyPersonalMsgDayEntity yyPersonalMsgDay) {
		return yyPersonalMsgDayService.saveYyPersonalMsgDay(yyPersonalMsgDay);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public Resp<YyPersonalMsgDayEntity> getById(@RequestBody Long id) {
		return yyPersonalMsgDayService.getYyPersonalMsgDayById(id);
	}
	
	/**
	 * 修改
	 * @param yyPersonalMsgDay
	 * @return
	 */
	@SysLog("修改运营-每次私发消息统计")
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public Resp<Integer> update(@RequestBody YyPersonalMsgDayEntity yyPersonalMsgDay) {
		return yyPersonalMsgDayService.updateYyPersonalMsgDay(yyPersonalMsgDay);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除运营-每次私发消息统计")
	@PostMapping("/remove")
	@ApiOperation(value = "删除")
	public Resp batchRemove(@RequestBody Long[] id) {
		return yyPersonalMsgDayService.batchRemove(id);
	}
	
}
