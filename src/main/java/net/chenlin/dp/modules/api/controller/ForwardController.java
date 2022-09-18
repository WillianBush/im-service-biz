package net.chenlin.dp.modules.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chenlin.dp.common.annotation.RestAnon;
import net.chenlin.dp.common.exception.RRException;
import net.chenlin.dp.modules.api.entity.Constants;
import net.chenlin.dp.modules.api.service.ForwardService;
import net.chenlin.dp.modules.sys.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/forward")
@AllArgsConstructor
@Slf4j
public class ForwardController extends AbstractController {

    ForwardService forwardService;

    @GetMapping()
    @RestAnon
    public String get(HttpServletRequest request) {
        String requestDomain = request.getHeader("Host");
//        log.info("nginx转发过来的请求头，requestDomain：{}", requestDomain);
        try {
            String responseDomain = forwardService.getResponseDomain(requestDomain);
            return redirect("https://" + responseDomain);
        } catch (RRException exception) {
            log.error("nginx转发过来的请求头，requestDomain：{}", requestDomain, exception);
        }
        return redirect("https://" + Constants.DEFAULT_DOMAIN);
    }

    @PostMapping()
    @RestAnon
    public String post(HttpServletRequest request) {
        String requestDomain = request.getHeader("Host");
//        log.info("nginx转发过来的请求头，requestDomain：{}", requestDomain);
        return redirect("https://" + Constants.DEFAULT_DOMAIN);
    }

    @PostMapping("/{path}")
    @RestAnon
    public String postPath(@PathVariable(value = "path") String path, HttpServletRequest request) {
        String requestDomain = request.getHeader("Host");
//        log.info("nginx转发过来的请求头，requestDomain：{},url:{}", requestDomain, path);
        return redirect("https://" + Constants.DEFAULT_DOMAIN);
    }


    @GetMapping("/{path}")
    @RestAnon
    public String getPath(@PathVariable(value = "path") String path, HttpServletRequest request) {
        String requestDomain = request.getHeader("Host");
//        log.info("nginx转发过来的请求头，requestDomain：{},url:{}", requestDomain, path);
//        try {
//            String responseDomain = forwardService.getResponseDomain(requestDomain,path);
//            return redirect("https://"+responseDomain);
//        }catch (RRException exception) {
//            log.error("nginx转发过来的请求头，requestDomain：{}",requestDomain,exception);
//            return redirect("https://"+ Constants.DEFAULT_DOMAIN);
//        }
        return redirect("https://" + Constants.DEFAULT_DOMAIN);
    }

    @GetMapping("/{path}/{path2}")
    @RestAnon
    public String getPath2(@PathVariable(value = "path") String path, @PathVariable(value = "path2") String path2) {
//        String requestDomain = request.getHeader("Host");
//        log.info("nginx转发过来的请求头，requestDomain：{},url:{},url2:{}", requestDomain, path, path2);
        return redirect("https://" + Constants.DEFAULT_DOMAIN);
    }


    @PostMapping("/{path}/{path2}")
    @RestAnon
    public String postPath2(@PathVariable(value = "path") String path, @PathVariable(value = "path2") String path2) {
//        String requestDomain = request.getHeader("Host");
//        log.info("nginx转发过来的请求头，requestDomain：{},url1:{},url2:{}", requestDomain, path, path2);
        return redirect("https://" + Constants.DEFAULT_DOMAIN);
    }


    @GetMapping("/{path}/{path2}/{path3}")
    @RestAnon
    public String getPath3(@PathVariable(value = "path") String path, @PathVariable(value = "path2") String path2, @PathVariable(value = "path3") String path3) {
        return redirect("https://" + Constants.DEFAULT_DOMAIN);
    }


    @PostMapping("/{path}/{path2}/{path3}")
    @RestAnon
    public String postPath3(@PathVariable(value = "path") String path, @PathVariable(value = "path2") String path2, @PathVariable(value = "path3") String path3) {
        return redirect("https://" + Constants.DEFAULT_DOMAIN);
    }

    @GetMapping("/{path}/{path2}/{path3}/{path4}")
    @RestAnon
    public String getPath4(@PathVariable(value = "path") String path, @PathVariable(value = "path2") String path2, @PathVariable(value = "path3") String path3, @PathVariable(value = "path4") String path4) {
        return redirect("https://" + Constants.DEFAULT_DOMAIN);
    }
    @GetMapping("/{path}/{path2}/{path3}/{path4}/{path5}")
    @RestAnon
    public String getPath5(@PathVariable(value = "path") String path, @PathVariable(value = "path2") String path2, @PathVariable(value = "path3") String path3, @PathVariable(value = "path4") String path4, @PathVariable(value = "path5") String path5) {
        return redirect("https://" + Constants.DEFAULT_DOMAIN);
    }
    @GetMapping("/{path}/{path2}/{path3}/{path4}/{path5}/{path6}")
    @RestAnon
    public String getPath6(@PathVariable(value = "path") String path, @PathVariable(value = "path2") String path2, @PathVariable(value = "path3") String path3, @PathVariable(value = "path4") String path4, @PathVariable(value = "path5") String path5, @PathVariable(value = "path6") String path6) {
        return redirect("https://" + Constants.DEFAULT_DOMAIN);
    }

    @PostMapping("/{path}/{path2}/{path3}/{path4}")
    @RestAnon
    public String postPath4(@PathVariable(value = "path") String path, @PathVariable(value = "path2") String path2, @PathVariable(value = "path3") String path3, @PathVariable(value = "path4") String path4) {
        return redirect("https://" + Constants.DEFAULT_DOMAIN);
    }
    @PostMapping("/{path}/{path2}/{path3}/{path4}/{path5}")
    @RestAnon
    public String postPath5(@PathVariable(value = "path") String path, @PathVariable(value = "path2") String path2, @PathVariable(value = "path3") String path3, @PathVariable(value = "path4") String path4, @PathVariable(value = "path5") String path5) {
        return redirect("https://" + Constants.DEFAULT_DOMAIN);
    }
    @PostMapping("/{path}/{path2}/{path3}/{path4}/{path5}/{path6}")
    @RestAnon
    public String postPath6(@PathVariable(value = "path") String path, @PathVariable(value = "path2") String path2, @PathVariable(value = "path3") String path3, @PathVariable(value = "path4") String path4, @PathVariable(value = "path5") String path5, @PathVariable(value = "path6") String path6) {
        return redirect("https://" + Constants.DEFAULT_DOMAIN);
    }
}
