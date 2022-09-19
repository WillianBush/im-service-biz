package net.chenlin.dp.modules.biz.appDomain.controller;

import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.DateUtils;
import net.chenlin.dp.common.utils.JSONUtils;
import net.chenlin.dp.modules.biz.appDomain.entity.AppDomainEntity;
import net.chenlin.dp.modules.biz.appDomain.entity.AppDomainEntityCSV;
import net.chenlin.dp.modules.biz.appDomain.service.AppDomainService;
import net.chenlin.dp.modules.biz.domain.entity.DomainEnum;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import net.chenlin.dp.modules.sys.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/appDomain")
public class AppDomainController extends AbstractController {
	
	@Autowired
	private AppDomainService appDomainService;
	
	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<AppDomainEntity> list(@RequestBody Map<String, Object> params) {
		return appDomainService.listAppDomain(params);
	}

	@RequestMapping(value = "/csv", method = RequestMethod.GET)
	public void bootPercentage(@RequestParam Map<String, Object> params, HttpServletResponse response) throws IOException {
		params = JSONUtils.mapNoEmpty(params);
		String current = DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN);
		String filename = URLEncoder.encode("APP域名-" + current + ".csv", "UTF-8");
		params.put("pageSize",10000);

		List<AppDomainEntity> bootPercentageList = appDomainService.listAppDomain(params).getRows(); // 这是一个业务代码 返回我要导出去的数据

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		// 防止乱码出现
		Writer writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
		// 写入字节流，让文档以UTF-8编码
		writer.write('\uFEFF');
		ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
//        String[] header = {"服务器类型", "域名", "app名字","是否启用","是否被封","创建时间","更新时间","创建人","更新人"};
		String[] header = {"domainType", "domainName", "appBaseName", "createTime","shortLink"};
		csvWriter.writeHeader(header);
//		List<AppDomainEntityCSV> appDomainEntityCSVS = new LinkedList<>();
		for (AppDomainEntity it : bootPercentageList) {
			AppDomainEntityCSV appDomainEntityCSV = new AppDomainEntityCSV();
			appDomainEntityCSV.setDomainName(it.getDomainName());
			appDomainEntityCSV.setDomainType(it.getDomainType().equals(DomainEnum.ServerDomain.getCode())?"落地页域名":"短域名");
			appDomainEntityCSV.setCreateTime(DateUtils.format(it.getCreateTime(),DateUtils.DATE_PATTERN));
			appDomainEntityCSV.setAppBaseName(it.getAppBaseName());
			if (it.getShortLink() != null) {
				appDomainEntityCSV.setShortLink(it.getShortLink().equals(1) ? "长连接" : "短连接");
			}else {
				appDomainEntityCSV.setShortLink(it.getDomainName().length()>7 ? "长连接" : "短连接");
			}
			csvWriter.write(appDomainEntityCSV, header);
		}
		csvWriter.close();
	}
	/**
	 * 新增
	 * @param appDomain
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/save")
	public R save(@RequestBody AppDomainEntity appDomain) {
		SysUserEntity sysUserEntity = super.getUser();
		appDomain.setCreateBy(sysUserEntity.getUsername());
		appDomain.setUpdateBy(sysUserEntity.getUsername());
		return appDomainService.saveAppDomain(appDomain);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getById(@RequestBody Long id) {
		return appDomainService.getAppDomainById(id);
	}
	
	/**
	 * 修改
	 * @param appDomain
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	public R update(@RequestBody AppDomainEntity appDomain) {
		SysUserEntity sysUserEntity = super.getUser();
		appDomain.setUpdateBy(sysUserEntity.getUsername());
		return appDomainService.updateAppDomain(appDomain);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return appDomainService.batchRemove(id);
	}
	
}
