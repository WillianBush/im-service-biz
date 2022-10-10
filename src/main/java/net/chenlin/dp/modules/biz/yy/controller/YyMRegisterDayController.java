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
import net.chenlin.dp.modules.biz.yy.entity.YyMRegisterDayEntity;
import net.chenlin.dp.modules.biz.yy.service.YyMRegisterDayService;

/**
 * 运营-每日注册数据
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/yy_m_register_day")
@Api(tags = "运营-每日注册数据")
public class YyMRegisterDayController extends AbstractController {
	

	private YyMRegisterDayService yyMRegisterDayService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "列表")
	public Page<YyMRegisterDayEntity> list(@RequestBody Map<String, Object> params) {
		return yyMRegisterDayService.listYyMRegisterDay(params);
	}
		
	/**
	 * 新增
	 * @param yyMRegisterDay
	 * @return
	 */
	@SysLog("新增运营-每日注册数据")
	@GetMapping("/save")
	@ApiOperation(value = "新增")
	public Resp<YyMRegisterDayEntity> save(@RequestBody YyMRegisterDayEntity yyMRegisterDay) {
		return yyMRegisterDayService.saveYyMRegisterDay(yyMRegisterDay);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public Resp<YyMRegisterDayEntity> getById(@RequestBody Long id) {
		return yyMRegisterDayService.getYyMRegisterDayById(id);
	}
	
	/**
	 * 修改
	 * @param yyMRegisterDay
	 * @return
	 */
	@SysLog("修改运营-每日注册数据")
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public Resp<Integer> update(@RequestBody YyMRegisterDayEntity yyMRegisterDay) {
		return yyMRegisterDayService.updateYyMRegisterDay(yyMRegisterDay);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除运营-每日注册数据")
	@PostMapping("/remove")
	@ApiOperation(value = "删除")
	public Resp batchRemove(@RequestBody Long[] id) {
		return yyMRegisterDayService.batchRemove(id);
	}

}
