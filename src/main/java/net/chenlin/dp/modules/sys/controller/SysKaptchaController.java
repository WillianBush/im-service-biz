package net.chenlin.dp.modules.sys.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 验证码请求
 * @author wang<fangyuan.co@outlook.com>
 */
@Controller
public class SysKaptchaController {

    @Autowired
    private Producer producer;

    /**
     * 验证码
     * @param response
     * @throws IOException
     */
    @RequestMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        // TODO 保存到 redis 和 用户名 一起

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

}
