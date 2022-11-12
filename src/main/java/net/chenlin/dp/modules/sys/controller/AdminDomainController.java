package net.chenlin.dp.modules.sys.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.sys.entity.DomainsEntity;
import net.chenlin.dp.modules.sys.service.DomainsConfigService;
import net.chenlin.dp.modules.sys.service.DomainsService;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sys/domain")
@Slf4j
@AllArgsConstructor
@Api(tags = "域名配置")
@DependsOn("springContextUtils")
public class AdminDomainController extends AbstractController {

    private DomainsService domainsService;

    private DomainsConfigService domainsConfigService;

    /**
     * 站点列表
     *
     * @param params
     * @return
     */
    @PostMapping("/list")
    @ApiOperation(value = "站点列表")
    public Page<DomainsEntity> list(@RequestBody(required = false) Map<String, Object> params) {
//        if (!isSuperAdmin()) {
//            params.put("userIdCreate", getUserId());
//        }
        return domainsService.listDomain(params);
    }


    /**
     * 新增机构域名
     * @param domainsEntity
     * @return
     */
    @SysLog("新增机构域名")
    @PostMapping("/saveDb")
    public R save(@RequestBody DomainsEntity domainsEntity) {
        return domainsService.save(domainsEntity);
    }

    /**
     * 查询机构域名
     * @param org_id
     * @return
     */
    @SysLog("查询机构域名")
    @PostMapping("/getDomainByOrgId")
    public R getDomainByOrgId(@RequestBody Integer org_id) {
        return domainsService.getDomainByOrgId(org_id);
    }


    /**
     * 保存域名配置
     * @param params
     * @return
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存域名配置")
    public Resp listLog(@RequestBody Map<String, Object> params) {
        return domainsConfigService.save(params);
    }

    /**
     * 查看配置
     * @return
     */
    @GetMapping("/info")
    public Resp info() {
        return domainsConfigService.get();
    }

    /**
     * 修改站点域名信息
     * @param domainsEntity
     * @return
     */
    @SysLog("修改站点域名信息")
    @PostMapping("/update")
    @ApiOperation(value = "修改站点域名信息")
    public R update(@RequestBody DomainsEntity domainsEntity) {
        return domainsService.update(domainsEntity);
    }

    /**
     * 删除站点
     *
     * @return
     */
    @SysLog("删除机构")
    @PostMapping("/remove")
    public R remove(@RequestBody Long[] domain_id) {
        return domainsService.remove(domain_id);
    }

}
