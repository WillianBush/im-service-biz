package net.chenlin.dp.modules.biz.ipwhite.controller;

import java.util.Map;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.ipwhite.entity.IpwhiteEntity;
import net.chenlin.dp.modules.biz.ipwhite.service.IpwhiteService;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ipwhite")
public class IpwhiteController extends AbstractController {
	
	private IpwhiteService ipwhiteService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<IpwhiteEntity> list(@RequestBody Map<String, Object> params) {
		return ipwhiteService.listIpwhite(params);
	}
		
	/**
	 * 新增
	 * @param ipwhite
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/save")
	public R save(@RequestBody IpwhiteEntity ipwhite) {
		return ipwhiteService.saveIpwhite(ipwhite);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getById(@RequestBody Long id) {
		return ipwhiteService.getIpwhiteById(id);
	}
	
	/**
	 * 修改
	 * @param ipwhite
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	public R update(@RequestBody IpwhiteEntity ipwhite) {
		return ipwhiteService.updateIpwhite(ipwhite);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return ipwhiteService.batchRemove(id);
	}
	
}
