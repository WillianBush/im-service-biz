package net.chenlin.dp.modules.biz.promotion.controller;

import lombok.AllArgsConstructor;
import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.DateUtils;
import net.chenlin.dp.common.utils.JSONUtils;
import net.chenlin.dp.modules.biz.promotion.entity.AppPromotionEntity;
import net.chenlin.dp.modules.biz.promotion.service.AppPromotionService;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 域名URL
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/promotion")
@AllArgsConstructor
public class AppPromotionController extends AbstractController {
	

	private AppPromotionService appPromotionService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<AppPromotionEntity> list(@RequestBody Map<String, Object> params) {
		return appPromotionService.listAppPromotion(params);
	}
		
	/**
	 * 新增
	 * @param appPromotion
	 * @return
	 */
	@SysLog("新增域名URL")
	@RequestMapping("/save")
	public R save(@RequestBody AppPromotionEntity appPromotion) {
		return appPromotionService.saveAppPromotion(appPromotion);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getById(@RequestBody Long id) {
		return appPromotionService.getAppPromotionById(id);
	}
	
	/**
	 * 修改
	 * @param appPromotion
	 * @return
	 */
	@SysLog("修改域名URL")
	@RequestMapping("/update")
	public R update(@RequestBody AppPromotionEntity appPromotion) {
		return appPromotionService.updateAppPromotion(appPromotion);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除域名URL")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return appPromotionService.batchRemove(id);
	}


	/**
	 *
	 * @param appName
	 * @return
	 */
	@SysLog("申请推广链接")
	@RequestMapping("/applyUrl")
	public R applyUrl(@RequestParam(value = "appName") String appName) {
		SysUserEntity user = super.getUser();
		return appPromotionService.applyUrl(appName,user);
	}


	/**
	 *
	 * @param appName
	 * @return
	 */
	@SysLog("申请推广链接")
	@RequestMapping("/applyUrlV2")
	public R applyUrl(@RequestParam(value = "appName") String appName,@RequestParam(value = "advertiseDomain") Integer advertiseDomain,@RequestParam(value = "qqChecked") Integer qqChecked) {
		SysUserEntity user = super.getUser();
		return appPromotionService.applyUrl(appName,user,advertiseDomain,qqChecked);
	}

	@RequestMapping(value = "/csv", method = RequestMethod.GET)
	public void bootPercentage(@RequestParam Map<String, Object> params, HttpServletResponse response) throws IOException {
		params = JSONUtils.mapNoEmpty(params);
		String current = DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN);
		String filename = URLEncoder.encode("域名URL-" + current + ".csv", "UTF-8");
		params.put("pageSize",1000);

		List<AppPromotionEntity> bootPercentageList = appPromotionService.listAppPromotion(params).getRows(); // 这是一个业务代码 返回我要导出去的数据

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		// 防止乱码出现
		Writer writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
		// 写入字节流，让文档以UTF-8编码
		writer.write('\uFEFF');
		ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
//        String[] header = {"服务器类型", "域名", "app名字","是否启用","是否被封","创建时间","更新时间","创建人","更新人"};
		String[] header = {"appName", "promotionDomain", "promotionUrl", "createTime", "expireTime"};
		csvWriter.writeHeader(header);

		for (AppPromotionEntity it : bootPercentageList) {
			it.setPromotionDomain("https://" + it.getPromotionDomain());
			csvWriter.write(it, header);
		}
		csvWriter.close();
	}
	
}
