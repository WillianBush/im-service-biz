package net.chenlin.dp.modules.biz.site.controller;

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
import net.chenlin.dp.modules.biz.site.entity.FunctionConfigEntity;
import net.chenlin.dp.modules.biz.site.service.FunctionConfigService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/site/functioncfg")
@Api(tags = "功能配置")
@DependsOn("springContextUtils")
public class FunctionConfigController extends AbstractController {
	

	private FunctionConfigService functionConfigService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "功能点")
	public Page<FunctionConfigEntity> list(@RequestBody Map<String, Object> params) {
		return functionConfigService.listFunctionConfig(params);
	}
		
	/**
	 * 新增
	 * @param functionConfig
	 * @return
	 */
	@SysLog("新增")
	@GetMapping("/save")
	@ApiOperation(value = "新增站点配置")
	public Resp<FunctionConfigEntity> save(@RequestBody FunctionConfigEntity functionConfig) {
		return functionConfigService.saveFunctionConfig(functionConfig);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public Resp<FunctionConfigEntity> getById(@RequestBody String id) {
		return functionConfigService.getFunctionConfigById(id);
	}
	
	/**
	 * 修改
	 * @param functionConfig
	 * @return
	 */
	@SysLog("修改")
	@PostMapping("/update")
	@ApiOperation(value = "修改功能配置")
	public Resp<Integer> update(@RequestBody FunctionConfigEntity functionConfig) {
		return functionConfigService.updateFunctionConfig(functionConfig);
	}

}
