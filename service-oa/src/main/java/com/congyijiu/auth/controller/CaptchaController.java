package com.congyijiu.auth.controller;

/**
 * @author congyijiu
 * @create 2023-06-03-21:35
 */
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
@Controller
@RestController
@RequestMapping("/getimg")
public class CaptchaController {
    @Autowired
    private Producer captchaProducer;

    @Autowired
    private static Logger logger = LoggerFactory.getLogger(CaptchaController.class);
    @RequestMapping
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        logger.debug("******************验证码是: " + code + "******************");

        response.setDateHeader("Expires", 0);

        // 设置标准的HTTP/1.1无缓存头信息
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

        // 设置IE扩展的HTTP/1.1无缓存头(使用addHeader)
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");

        // 设置标准HTTP/1.0无缓存报头
        response.setHeader("Pragma", "no-cache");

        // 返回一个jpeg
        response.setContentType("image/jpeg");

        // 为图像创建文本
        String capText = captchaProducer.createText();

        // 将文本存储在会话中
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        // 用文本创建图像
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();

        // 导出数据
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }
}
