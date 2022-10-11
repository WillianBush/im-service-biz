package net.chenlin.dp.modules.sys.controller;

import lombok.AllArgsConstructor;
import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.sys.entity.SysAreaEntity;
import net.chenlin.dp.modules.sys.service.SysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 行政区域
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/sys/area")
@AllArgsConstructor
@DependsOn("springContextUtils")
public class SysAreaController extends AbstractController {




	private SysAreaService sysAreaService;
	
	/**
	 * 根据父级code查询子节点，子区域列表
	 * @param params
	 * @return
	 */
	@GetMapping("/list")
	public Resp list(@RequestBody Map<String, Object> params) {
		return sysAreaService.listAreaByParentCode(params);
	}
	
	/**
	 * 根据父级code查询子节点，树形目录
	 * @return
	 */
	@GetMapping("/select")
	public List<SysAreaEntity> select(@RequestParam String areaCode) {
		return sysAreaService.listAreaByParentCode(areaCode);
	}
	
	/**
	 * 新增区域
	 * @param area
	 * @return
	 */
	@SysLog("新增区域")
	@PostMapping("/save")
	public Resp save(@RequestBody SysAreaEntity area) {
		return sysAreaService.saveArea(area);
	}
	
	/**
	 * 查询详情
	 * @param areaId
	 * @return
	 */
	@GetMapping("/info")
	public Resp info(@RequestBody Long areaId) {
		return sysAreaService.getAreaById(areaId);
	}
	
	/**
	 * 修改区域
	 * @param area
	 * @return
	 */
	@SysLog("修改区域")
	@PostMapping("/update")
	public Resp update(@RequestBody SysAreaEntity area) {
		return sysAreaService.updateArea(area);
	}
	
	/**
	 * 删除区域
	 * @param id
	 * @return
	 */
	@SysLog("删除区域")
	@PostMapping("/remove")
	public Resp remove(@RequestBody Long[] id) {
		return sysAreaService.batchRemoveArea(id);
	}
	
}
