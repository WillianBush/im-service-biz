package net.chenlin.dp.modules.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.annotation.RestAnon;
import net.chenlin.dp.modules.api.entity.Constants;
import net.chenlin.dp.modules.biz.appResigned.entity.AppResignedEntity;
import net.chenlin.dp.modules.biz.appResigned.service.AppResignedService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.beetl.ext.spring.BeetlSpringViewResolver.redirect;

@Controller
@RequestMapping("/api/download")
@Slf4j
@AllArgsConstructor
public class DownloadController {

    private AppResignedService appResignedService;

    @GetMapping("{terminal}")
    @RestAnon
    public String get(HttpServletRequest request,@PathVariable(value = "terminal") String terminal, @RequestParam(value = "appName") String appName ) {
        if (Constants.DOWNLOAD_IOS_PATH.equals(terminal)) {
            AppResignedEntity appResigned = appResignedService.getLastResigned(appName);
            if (appResigned != null) {
                appResignedService.increaseDownloadTimesIOS(appResigned);
                return redirect(appResigned.getIosDownloadAddrss());
            }
        }

        if (Constants.DOWNLOAD_ANDROID_PATH.equals(terminal)) {
            AppResignedEntity appResigned = appResignedService.getLastResigned(appName);
            if (appResigned != null) {
                appResignedService.increaseDownloadTimesAndroid(appResigned);
                return redirect(appResigned.getAndroidResignedDownloadAddress());
            }
        }
        return redirect("http://"+ Constants.DEFAULT_DOMAIN);
    }

    @PostMapping("{terminal}")
    @RestAnon
    public String post(HttpServletRequest request,@PathVariable(value = "terminal") String terminal, @RequestParam(value = "appName") String appName ) {
//        String requestDomain = request.getHeader("Host");
//        log.info("nginx转发过来的请求头，requestDomain：{}",requestDomain);
        return redirect("http://"+ Constants.DEFAULT_DOMAIN);
    }
}
