package net.chenlin.dp.modules.biz.yy.controller;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.yy.entity.YyPersonalMsgDayEntity;
import net.chenlin.dp.modules.biz.yy.service.YyPersonalMsgDayService;

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
	 * 列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "列表")
	public Page<YyPersonalMsgDayEntity> list(@RequestBody Map<String, Object> params) {
		return yyPersonalMsgDayService.listYyPersonalMsgDay(params);
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
