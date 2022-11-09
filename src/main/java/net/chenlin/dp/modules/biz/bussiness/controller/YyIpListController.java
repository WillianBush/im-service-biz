package net.chenlin.dp.modules.biz.bussiness.controller;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.chenlin.dp.common.exception.RRException;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.DependsOn;
import net.chenlin.dp.common.exception.GoLoginException;
import org.springframework.web.bind.annotation.*;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.bussiness.entity.YyIpListEntity;
import net.chenlin.dp.modules.biz.bussiness.service.YyIpListService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/yy_ip_list")
@Api(tags = "运营-ip黑名单/ipweb白名单")
@DependsOn("springContextUtils")
public class YyIpListController extends AbstractController {
	

	private YyIpListService yyIpListService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "列表")
	public Page<YyIpListEntity> list(@RequestBody Map<String, Object> params) {
		if (null == params.get("type") || params.get("type") == "") {
			throw new RRException("type不能为空");
		}
		return yyIpListService.listYyIpList(params);
	}
		
	/**
	 * 新增
	 * @param yyIpList
	 * @return
	 */
	@SysLog("新增")
	@PostMapping("/save")
	@ApiOperation(value = "新增")
	public Resp<YyIpListEntity> save(@RequestBody YyIpListEntity yyIpList) {
		if (StringUtils.isEmpty(yyIpList.getIp_address() )) {
			return Resp.error("IP地址为空");
		}

		if (StringUtils.isEmpty(yyIpList.getType() ) ) {
			return Resp.error("IP类型参数错误");
		}
		YyIpListEntity yyIpListEntity= yyIpListService.getByIP(yyIpList.getIp_address(),0);
		if (null != yyIpListEntity){
			return Resp.error("ip已存在");
		}
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
		if (null == id) {
			throw new RRException("id不能为空");
		}
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
		if (null == yyIpList.getId()) {
			throw new RRException("id参数不能为空");
		}
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
		if (0 == id.length) {
			throw new RRException("id不能为空");
		}

		return  yyIpListService.batchRemove(id);
	}
	
}
