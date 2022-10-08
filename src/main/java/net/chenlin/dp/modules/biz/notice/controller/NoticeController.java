package net.chenlin.dp.modules.biz.notice.controller;

import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.biz.notice.entity.NoticeEntity;
import net.chenlin.dp.modules.biz.notice.service.NoticeService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/notice")
@Api(tags = "用户登录登出")
public class NoticeController extends AbstractController {
	

	private NoticeService noticeService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	@ApiOperation(value = "列表")
	public Page<NoticeEntity> list(@RequestBody Map<String, Object> params) {
		return noticeService.listNotice(params);
	}
		
	/**
	 * 新增
	 * @param notice
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/save")
	@ApiOperation(value = "新增")
	public Resp<NoticeEntity> save(@RequestBody NoticeEntity notice) {
		return noticeService.saveNotice(notice);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public Resp<NoticeEntity> getById(@RequestBody Long id) {
		return noticeService.getNoticeById(id);
	}
	
	/**
	 * 修改
	 * @param notice
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	@ApiOperation(value = "修改")
	public Resp<Integer> update(@RequestBody NoticeEntity notice) {
		return noticeService.updateNotice(notice);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/remove")
	@ApiOperation(value = "删除")
	public Resp batchRemove(@RequestBody Long[] id) {
		return noticeService.batchRemove(id);
	}
	
}
