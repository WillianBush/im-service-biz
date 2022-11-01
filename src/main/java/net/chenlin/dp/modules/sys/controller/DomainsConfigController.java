package net.chenlin.dp.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.entity.Page;
import net.chenlin.dp.common.entity.Resp;
import net.chenlin.dp.modules.sys.service.DomainsConfigService;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 系统日志
 * @author wang<fangyuan.co@outlook.com>
 */
@RestController
@RequestMapping("/sys/domain")
@Slf4j
@AllArgsConstructor
@Api(tags = "域名配置")
@DependsOn("springContextUtils")
public class DomainsConfigController extends AbstractController{

    private DomainsConfigService domainsConfigService;

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

}
