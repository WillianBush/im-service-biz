package net.chenlin.dp.modules.biz.domain.controller;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.utils.DateUtils;
import net.chenlin.dp.common.utils.JSONUtils;
import net.chenlin.dp.modules.biz.appDomain.entity.AppDomainEntity;
import net.chenlin.dp.modules.biz.appDomain.service.AppDomainService;
import net.chenlin.dp.modules.biz.appResigned.entity.AppResignedEntity;
import net.chenlin.dp.modules.biz.domain.entity.DomainEntity;
import net.chenlin.dp.modules.biz.domain.entity.DomainOutCsv;
import net.chenlin.dp.modules.biz.domain.service.DomainService;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/domain")
public class DomainController extends AbstractController {

	private DomainService domainService;

	private AppDomainService appDomainService;


	/**
	 * 列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public Page<DomainEntity> list(@RequestBody Map<String, Object> params) {
		return domainService.listDomain(params);
	}
		
	/**
	 * 新增
	 * @param domain
	 * @return
	 */
	@SysLog("新增")
	@RequestMapping("/save")
	public R save(@RequestBody DomainEntity domain) {
		SysUserEntity user = super.getUser();
		domain.setCreateBy(user.getUsername());
		domain.setUpdateBy(user.getUsername());
		if (!(domain.getDomainType() == 1 || domain.getDomainType() == 2)) {
			return R.error("域名类型输入错误");
		}

		if (1 == domain.getDomainType()) {
			domain.setServerName("47.242.14.229");
			domain.setServerId(1);
//			switch (domain.getAppName()) {
//				case "miaoyu":
//				case "yueai":
//				case "youyue":
//				case "chulian":
//					domain.setServerName("47.242.14.229");
//					break;
//			}
		}else {
			domain.setServerName("8.210.136.237");
			domain.setServerId(2);
		}
		AppDomainEntity appDomain = new AppDomainEntity();
		AppResignedEntity appResigned = appDomainService.getAppResignedByAppName(domain.getAppName());
		if (null == appResigned) {
			return R.error("AppName不存在");
		}
		DomainEntity domainEach = new DomainEntity();
		domain.setDomainName(domain.getDomainName().replace(" ", ""));

		if (!domain.getDomainName().contains(",")) {
			appDomain.setDomainType(domain.getDomainType());
			appDomain.setDomainName(domain.getDomainName());
			appDomain.setAppBaseName(domain.getAppName());
			appDomain.setAndroidResignedPackageName(appResigned.getAndroidResignedPackageName());
			appDomain.setAppResignedId(appResigned.getId());
			appDomain.setCreateTime(domain.getCreateTime());
			appDomain.setUpdateTime(domain.getUpdateTime());
			appDomain.setCreateBy(user.getUsername());
			appDomain.setUpdateBy(user.getUsername());
			appDomainService.saveAppDomain(appDomain);
			return domainService.saveDomain(domain);
		} else {
			String[] nameList = domain.getDomainName().split(",");
			if (nameList.length > 50) {
				return R.error("输入域名数量超过50个！");
			}
			List<String> domainNameList = Arrays.asList(nameList);
			AtomicReference<Long> countSuccess = new AtomicReference<>(0L);
			AtomicReference<Long> countFailed = new AtomicReference<>(0L);
			domainNameList.forEach(it -> {
				domainEach.setDomainName(it);
				domainEach.setCreateBy(user.getUsername());
				domainEach.setUpdateBy(user.getUsername());
				domainEach.setDomainEnable(domain.getDomainEnable());
				domainEach.setDomainType(domain.getDomainType());
				domainEach.setCreateTime(domain.getCreateTime());
				domainEach.setIsBlocked(domain.getIsBlocked());
				domainEach.setUpdateTime(domain.getUpdateTime());
				domainEach.setServerName(domain.getServerName());
				domainEach.setServerId(domain.getServerId());

				appDomain.setDomainType(domain.getDomainType());
				appDomain.setDomainName(it);
				appDomain.setAppBaseName(domain.getAppName());
				appDomain.setAndroidResignedPackageName(appResigned.getAndroidResignedPackageName());
				appDomain.setAppResignedId(appResigned.getId());
				appDomain.setCreateTime(domain.getCreateTime());
				appDomain.setUpdateTime(domain.getUpdateTime());
				appDomain.setCreateBy(user.getUsername());
				appDomain.setUpdateBy(user.getUsername());

				appDomainService.saveAppDomain(appDomain);
				R r = domainService.saveDomain(domainEach);
				if ("500" == r.get("code")) {
					countFailed.updateAndGet(v -> v + 1);
				} else {
					countSuccess.updateAndGet(v -> v + 1);
				}
			});
			R r = new R();
			r.put("msg", "成功：" + countSuccess + ", 失败：" + countFailed);
			return r;
		}
	}

	@RequestMapping(value = "/csv", method = RequestMethod.GET)
	public void bootPercentage(@RequestParam Map<String, Object> params, HttpServletResponse response) throws IOException {
		params = JSONUtils.mapNoEmpty(params);
//        if (!userEntity.getRoleId().equals(SystemConstant.RoleEnum.Organization.getCode()))  throw new RRException("机构管理员才能下载报表");
		String current = DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN);
		String filename = URLEncoder.encode("域名清单-" + current + ".csv", "UTF-8");
		params.put("pageSize",1000);
		List<DomainEntity> bootPercentageList = this.list(params).getRows(); // 这是一个业务代码 返回我要导出去的数据
		List<DomainOutCsv> list = Lists.newArrayList();
		bootPercentageList.stream().forEach(it->{
			DomainOutCsv csv = new DomainOutCsv();
			csv.setDomainType(it.getDomainType());
			csv.setDomainName(it.getDomainName());
			csv.setAppName(it.getAppName());
			csv.setDomainEnable(it.getDomainEnable());
			csv.setIsBlocked(it.getIsBlocked());
			csv.setCreateTime(it.getCreateTime());
			csv.setUpdateTime(it.getUpdateTime());
			csv.setCreateBy(it.getCreateBy());
			csv.setUpdateBy(it.getUpdateBy());
			list.add(csv);
		});
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		// 防止乱码出现
		Writer writer = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
		// 写入字节流，让文档以UTF-8编码
		writer.write('\uFEFF');
		ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
//        String[] header = {"服务器类型", "域名", "app名字","是否启用","是否被封","创建时间","更新时间","创建人","更新人"};
		String[] header = {"domainType","domainName", "appName","domainEnable","isBlocked","createTime","updateTime","createBy","updateBy"};
		csvWriter.writeHeader(header);

		for (DomainOutCsv it : list) {
			csvWriter.write(it, header);
		}
		csvWriter.close();
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R getById(@RequestBody Long id) {
		return domainService.getDomainById(id);
	}
	
	/**
	 * 修改
	 * @param domain
	 * @return
	 */
	@SysLog("修改")
	@RequestMapping("/update")
	public R update(@RequestBody DomainEntity domain) {
		SysUserEntity user = super.getUser();
		domain.setUpdateBy(user.getUsername());
		return domainService.updateDomain(domain);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@SysLog("删除")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return domainService.batchRemove(id);
	}
	
}
