package net.chenlin.dp.modules.biz.server.controller;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.biz.server.entity.ServerEntity;
import net.chenlin.dp.modules.biz.server.service.ServerService;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 服务器基本信息
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/server")
public class ServerController extends AbstractController {
	
	@Autowired
	private ServerService serverService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<ServerEntity> list(@RequestBody Map<String, Object> params) {
		return serverService.listServer(params);
	}
		
	/**
	 * 新增
	 * @param server
	 * @return
	 */
	@SysLog("新增服务器基本信息")
	@RequestMapping("/save")
	public R save(@RequestBody ServerEntity server) {
		SysUserEntity sysUserEntity = super.getUser();
		server.setCreateBy(sysUserEntity.getUsername());
		server.setUpdateBy(sysUserEntity.getUsername());
		return serverService.saveServer(server);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getById(@RequestBody Long id) {
		return serverService.getServerById(id);
	}
	
	/**
	 * 修改
	 * @param server
	 * @return
	 */
	@SysLog("修改服务器基本信息")
	@RequestMapping("/update")
	public R update(@RequestBody ServerEntity server) {
		SysUserEntity sysUserEntity = super.getUser();
		server.setUpdateBy(sysUserEntity.getUsername());
		return serverService.updateServer(server);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除服务器基本信息")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return serverService.batchRemove(id);
	}
	
}
