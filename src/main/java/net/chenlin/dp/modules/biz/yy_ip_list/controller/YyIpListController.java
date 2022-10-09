package net.chenlin.dp.modules.biz.yy_ip_list.controller;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.yy_ip_list.entity.YyIpListEntity;
import net.chenlin.dp.modules.biz.yy_ip_list.service.YyIpListService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/yy_ip_list")
@Api(tags = "运营-ip黑名单/ipweb白名单")
public class YyIpListController extends AbstractController {
	

	private YyIpListService yyIpListService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@GetMapping("/list")
	@ApiOperation(value = "列表")
	public Page<YyIpListEntity> list(@RequestBody Map<String, Object> params) {
		return yyIpListService.listYyIpList(params);
	}
		
	/**
	 * 新增
	 * @param yyIpList
	 * @return
	 */
	@SysLog("新增")
	@GetMapping("/save")
	@ApiOperation(value = "新增")
	public Resp<YyIpListEntity> save(@RequestBody YyIpListEntity yyIpList) {
		return yyIpListService.saveYyIpList(yyIpList);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public Resp<YyIpListEntity> getById(@RequestBody Long id) {
		return yyIpListService.getYyIpListById(id);
	}
	
	/**
	 * 修改
	 * @param yyIpList
	 * @return
	 */
	@SysLog("修改")
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public Resp<Integer> update(@RequestBody YyIpListEntity yyIpList) {
		return yyIpListService.updateYyIpList(yyIpList);
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
		return yyIpListService.batchRemove(id);
	}
	
}
