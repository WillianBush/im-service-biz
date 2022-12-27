package net.chenlin.dp.modules.biz.bussiness.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.common.exception.RRException;
import net.chenlin.dp.modules.biz.bussiness.entity.CommodityCategoryEntity;
import net.chenlin.dp.modules.biz.bussiness.entity.CommodityEntity;
import net.chenlin.dp.modules.biz.bussiness.service.CommodityService;
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
@RequestMapping("/commodity_category")
@Api(tags = "商品信息")
@DependsOn("springContextUtils")
public class CommodityCategoryController extends AbstractController {
	

	private CommodityService commodityService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	@ApiOperation(value = "列表")
	public Page<CommodityCategoryEntity> list(@RequestBody Map<String, Object> params) {
//		params.put("domain",getServerName());
		return commodityService.listPageForCommodityCategory(params);
	}
		
	/**
	 * 新增
	 * @param commodityCategoryEntity
	 * @return
	 */
	@SysLog("新增")
	@PostMapping("/save")
	@ApiOperation(value = "新增")
	public Resp<CommodityCategoryEntity> save(@RequestBody CommodityCategoryEntity commodityCategoryEntity) {
		if (StringUtils.isEmpty(commodityCategoryEntity.getCategory_name() )) {
			return Resp.error("商品名称不能为空");

		}
		return commodityService.saveCommodityCategory(commodityCategoryEntity);
	}

	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "根据id查询详情")
	public Resp<CommodityCategoryEntity> getById(@RequestBody Long id) {
		if (null == id) {
			throw new RRException("id不能为空");
		}
		return commodityService.getCommodityCategoryById(id);
	}

	/**
	 * 修改
	 * @param commodityCategoryEntity
	 * @return
	 */
	@SysLog("修改")
	@PostMapping("/update")
	@ApiOperation(value = "修改")
	public Resp<Integer> update(@RequestBody CommodityCategoryEntity commodityCategoryEntity) {
		if (null == commodityCategoryEntity.getId()) {
			throw new RRException("id参数不能为空");
		}
		return commodityService.updateCommodityCategory(commodityCategoryEntity);
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

		return  commodityService.batchRemoveCC(id);
	}
	
}
