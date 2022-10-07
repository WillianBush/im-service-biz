package net.chenlin.dp.modules.biz.notice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.notice.entity.NoticeEntity;
import net.chenlin.dp.modules.biz.notice.service.NoticeService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/notice")
public class NoticeController extends AbstractController {
	
	@Autowired
	private NoticeService noticeService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
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
	public R save(@RequestBody NoticeEntity notice) {
		return noticeService.saveNotice(notice);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getById(@RequestBody Long id) {
		return noticeService.getNoticeById(id);
	}
	
	/**
	 * 修改
	 * @param notice
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	public R update(@RequestBody NoticeEntity notice) {
		return noticeService.updateNotice(notice);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return noticeService.batchRemove(id);
	}
	
}