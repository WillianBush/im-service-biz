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
import net.chenlin.dp.modules.biz.yy.entity.YyMOnlineDayEntity;
import net.chenlin.dp.modules.biz.yy.service.YyMOnlineDayService;

/**
 * 运营-每日用户在线统计
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/yy_m_online_day")
@Api(tags = "运营-每日用户在线统计")
@DependsOn("springContextUtils")
public class YyMOnlineDayController extends AbstractController {
	

	private YyMOnlineDayService yyMOnlineDayService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "列表")
	public Page<YyMOnlineDayEntity> list(@RequestBody Map<String, Object> params) {
		return yyMOnlineDayService.listYyMOnlineDay(params);
	}
		
	/**
	 * 新增
	 * @param yyMOnlineDay
	 * @return
	 */
	@SysLog("新增运营-每日用户在线统计")
	@PostMapping("/save")
	@ApiOperation(value = "新增")
	public Resp<YyMOnlineDayEntity> save(@RequestBody YyMOnlineDayEntity yyMOnlineDay) {
		return yyMOnlineDayService.saveYyMOnlineDay(yyMOnlineDay);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public Resp<YyMOnlineDayEntity> getById(@RequestBody Long id) {
		return yyMOnlineDayService.getYyMOnlineDayById(id);
	}
	
	/**
	 * 修改
	 * @param yyMOnlineDay
	 * @return
	 */
	@SysLog("修改运营-每日用户在线统计")
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public Resp<Integer> update(@RequestBody YyMOnlineDayEntity yyMOnlineDay) {
		return yyMOnlineDayService.updateYyMOnlineDay(yyMOnlineDay);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除运营-每日用户在线统计")
	@PostMapping("/remove")
	@ApiOperation(value = "删除")
	public Resp batchRemove(@RequestBody Long[] id) {
		return yyMOnlineDayService.batchRemove(id);
	}
	
}
