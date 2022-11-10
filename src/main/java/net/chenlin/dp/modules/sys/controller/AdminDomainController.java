package net.chenlin.dp.modules.sys.controller;


import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.annotation.SysLog;
import net.chenlin.dp.common.entity.R;
import net.chenlin.dp.modules.sys.entity.DomainsEntity;
import net.chenlin.dp.modules.sys.service.DomainsService;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/domain")
@Slf4j
@AllArgsConstructor
@Api(tags = "域名配置")
@DependsOn("springContextUtils")
public class AdminDomainController extends AbstractController {

    private DomainsService domainsService;

    /**
     * 新增机构域名
     * @param domainsEntity
     * @return
     */
    @SysLog("新增机构域名")
    @PostMapping("/save")
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

}
