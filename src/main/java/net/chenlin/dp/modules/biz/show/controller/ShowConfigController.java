package net.chenlin.dp.modules.biz.show.controller;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.show.entity.ShowConfigEntity;
import net.chenlin.dp.modules.biz.show.service.ShowConfigService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/show/config")
@Api(tags = "界面配置")
@DependsOn("springContextUtils")
public class ShowConfigController extends AbstractController {
	

	private ShowConfigService showConfigService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "列表")
	public Page<ShowConfigEntity> list(@RequestBody Map<String, Object> params) {
		return showConfigService.listShowConfig(params);
	}
		
	/**
	 * 新增
	 * @param showConfig
	 * @return
	 */
	@SysLog("新增")
	@PostMapping("/save")
	@ApiOperation(value = "新增")
	public Resp<ShowConfigEntity> save(@RequestBody ShowConfigEntity showConfig) {
		return showConfigService.saveShowConfig(showConfig);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@PostMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public Resp<ShowConfigEntity> getById(@RequestBody String id) {
		return showConfigService.getShowConfigById(id);
	}
	
	/**
	 * 修改
	 * @param showConfig
	 * @return
	 */
	@SysLog("修改")
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public Resp<Integer> update(@RequestBody ShowConfigEntity showConfig) {
		return showConfigService.updateShowConfig(showConfig);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@PostMapping("/remove")
	@ApiOperation(value = "删除")
	public Resp batchRemove(@RequestBody String[] id) {
		return showConfigService.batchRemove(id);
	}
	
}
