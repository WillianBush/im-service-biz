package net.chenlin.dp.modules.biz.bussiness.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.exception.RRException;
import net.chenlin.dp.modules.biz.bussiness.entity.CommodityEntity;
import net.chenlin.dp.modules.biz.bussiness.entity.NoticeEntity;
import net.chenlin.dp.modules.biz.bussiness.service.NoticeService;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/notice")
@Api(tags = "公告信息")
@DependsOn("springContextUtils")
public class NoticeController extends AbstractController {
	

	private NoticeService noticeService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "列表")
	public Page<NoticeEntity> list(@RequestBody Map<String, Object> params) {
		params.put("domain",getServerName());
		return noticeService.listPage(params);
	}
		
	/**
	 * 新增
	 * @param noticeEntity
	 * @return
	 */
	@SysLog("新增")
	@PostMapping("/save")
	@ApiOperation(value = "新增")
	public Resp<NoticeEntity> save(@RequestBody NoticeEntity noticeEntity) {
		if (StringUtils.isEmpty(noticeEntity.getTitle() )) {
			return Resp.error("公告标题不能为空");
		}
		if (StringUtils.isEmpty(String.valueOf(noticeEntity.getContent())) ) {
			return Resp.error("公告内容不能为空");
		}
		return noticeService.save(noticeEntity,getServerName());
	}

	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public Resp<NoticeEntity> getById(@RequestBody String id) {
		if (null == id) {
			throw new RRException("id不能为空");
		}
		return noticeService.getById(id);
	}

	/**
	 * 修改
	 * @param noticeEntity
	 * @return
	 */
	@SysLog("修改")
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public Resp<Integer> update(@RequestBody NoticeEntity noticeEntity) {
		if (null == noticeEntity.getId()) {
			throw new RRException("id参数不能为空");
		}
		return noticeService.update(noticeEntity, getServerName());
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
		if (0 == id.length) {
			throw new RRException("id不能为空");
		}
		return  noticeService.batchRemove(id);
	}
	
}
