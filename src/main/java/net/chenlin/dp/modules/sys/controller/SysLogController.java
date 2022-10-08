package net.chenlin.dp.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.sys.entity.SysLogEntity;
import net.chenlin.dp.modules.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 系统日志
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/sys/log")
@Slf4j
@AllArgsConstructor
@Api(tags = "系统日志")
public class SysLogController extends AbstractController {

	private SysLogService sysLogService;
	
	/**
	 * 日志列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "日志列表")
	public Page<SysLogEntity> listLog(@RequestBody Map<String, Object> params) {
		return sysLogService.listLog(params);
	}
	
	/**
	 * 删除日志
	 * @param id
	 * @return
	 */
	@SysLog("删除日志")
	@PostMapping("/remove")
	@ApiOperation(value = "删除日志")
	public Resp batchRemove(@RequestBody Long[] id) {
		return sysLogService.batchRemove(id);
	}
	
	/**
	 * 清空日志
	 * @return
	 */
	@SysLog("清空日志")
	@PostMapping("/clear")
	@ApiOperation(value = "清空日志")
	public Resp<Integer> batchRemoveAll() {
		return sysLogService.batchRemoveAll();
	}
	
}
