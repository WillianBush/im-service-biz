//package net.chenlin.dp.modules.biz.bussiness.controller;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.AllArgsConstructor;
//import net.chenlin.dp.common.annotation.SysLog;
//import net.chenlin.dp.common.entity.Page;
//import net.chenlin.dp.common.entity.Resp;
//import net.chenlin.dp.common.exception.RRException;
////import net.chenlin.dp.modules.biz.bussiness.entity.CommodityEntity;
//import net.chenlin.dp.modules.biz.bussiness.entity.YyIpListEntity;
//import net.chenlin.dp.modules.biz.bussiness.service.CommodityService;
//import net.chenlin.dp.modules.biz.bussiness.service.YyIpListService;
//import net.chenlin.dp.modules.sys.controller.AbstractController;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
///**
// *
// * @author wang<fangyuan.co@outlook.com>
// */
//@RestController
//@AllArgsConstructor
//@RequestMapping("/commodity")
//@Api(tags = "商品信息")
//@DependsOn("springContextUtils")
//public class CommodityController extends AbstractController {
//
//
//	private CommodityService commodityService;
//
//	/**
//	 * 列表
//	 * @param params
//	 * @return
//	 */
//	@PostMapping("/list")
//	@ApiOperation(value = "列表")
//	public Page<CommodityEntity> list(@RequestBody Map<String, Object> params) {
//		params.put("domain",getServerName());
//		return commodityService.listPageForCommodity(params);
//	}
//
//	/**
//	 * 新增
//	 * @param commodityEntity
//	 * @return
//	 */
//	@SysLog("新增")
//	@PostMapping("/save")
//	@ApiOperation(value = "新增")
//	public Resp<CommodityEntity> save(@RequestBody CommodityEntity commodityEntity) {
//		if (StringUtils.isEmpty(commodityEntity.getName() )) {
//			return Resp.error("商品名称不能为空");
//		}
//
//		if (StringUtils.isEmpty(String.valueOf(commodityEntity.getIn_stock())) ) {
//			return Resp.error("商品库存数量错误");
//		}
////		Resp<CommodityEntity> commodityEntityCheck = commodityService.getCommodityByName(commodityEntity.getName());
////		if (null != commodityEntityCheck.getData()){
////			return Resp.error("商品已存在");
////		}
//		return commodityService.saveCommodity(commodityEntity,getServerName());
//	}
//
//	/**
//	 * 根据id查询详情
//	 * @param id
//	 * @return
//	 */
//	@GetMapping("/info")
//	@ApiOperation(value = "根据id查询详情")
//	public Resp<CommodityEntity> getById(@RequestBody Long id) {
//		if (null == id) {
//			throw new RRException("id不能为空");
//		}
//		return commodityService.getCommodityById(id);
//	}
//
//	/**
//	 * 修改
//	 * @param commodityEntity
//	 * @return
//	 */
//	@SysLog("修改")
//	@PostMapping("/update")
//	@ApiOperation(value = "修改")
//	public Resp<Integer> update(@RequestBody CommodityEntity commodityEntity) {
//		if (null == commodityEntity.getId()) {
//			throw new RRException("id参数不能为空");
//		}
//		return commodityService.updateCommodity(commodityEntity, getServerName());
//	}
//
//	/**
//	 * 删除
//	 * @param id
//	 * @return
//	 */
//	@SysLog("删除")
//	@PostMapping("/remove")
//	@ApiOperation(value = "删除")
//	public Resp batchRemove(@RequestBody Long[] id) {
//		if (0 == id.length) {
//			throw new RRException("id不能为空");
//		}
//
//		return  commodityService.batchRemove(id);
//	}
//
//}
