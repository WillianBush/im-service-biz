package net.chenlin.dp.modules.biz.yy_group_notice.controller;

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
import net.chenlin.dp.modules.biz.yy_group_notice.entity.YyGroupNoticeEntity;
import net.chenlin.dp.modules.biz.yy_group_notice.service.YyGroupNoticeService;

/**
 * 运营-消息群发
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/yy_group_notice")
@Api(tags = "运营-消息群发")
public class YyGroupNoticeController extends AbstractController {
	

	private YyGroupNoticeService yyGroupNoticeService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@GetMapping("/list")
	@ApiOperation(value = "列表")
	public Page<YyGroupNoticeEntity> list(@RequestBody Map<String, Object> params) {
		return yyGroupNoticeService.listYyGroupNotice(params);
	}
		
	/**
	 * 新增
	 * @param yyGroupNotice
	 * @return
	 */
	@SysLog("新增运营-消息群发")
	@GetMapping("/save")
	@ApiOperation(value = "新增")
	public Resp<YyGroupNoticeEntity> save(@RequestBody YyGroupNoticeEntity yyGroupNotice) {
		return yyGroupNoticeService.saveYyGroupNotice(yyGroupNotice);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public Resp<YyGroupNoticeEntity> getById(@RequestBody Long id) {
		return yyGroupNoticeService.getYyGroupNoticeById(id);
	}
	
	/**
	 * 修改
	 * @param yyGroupNotice
	 * @return
	 */
	@SysLog("修改运营-消息群发")
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public Resp<Integer> update(@RequestBody YyGroupNoticeEntity yyGroupNotice) {
		return yyGroupNoticeService.updateYyGroupNotice(yyGroupNotice);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除运营-消息群发")
	@PostMapping("/remove")
	@ApiOperation(value = "删除")
	public Resp batchRemove(@RequestBody Long[] id) {
		return yyGroupNoticeService.batchRemove(id);
	}
	
}
