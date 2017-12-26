package com.project.controller.wechat;

import com.project.common.exception.BusinessException;
import com.project.model.material.Request;
import com.project.service.weixin.material.WechatMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by goforit on 2017/11/27.
 */
@Controller
@RequestMapping("/wechat/material")
public class WechatMaterialController {
    @Autowired
    private WechatMaterialService wechatMaterialService;

    private Request getMaterialRequest() {
        return new Request(Request.TypeEnum.NEWS, 0, 20);
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String batchGetMaterialUrl(String qrUrl, Model model) {
        try {
            model.addAttribute("map", wechatMaterialService.batchgetMaterialUrl(qrUrl,
                    getMaterialRequest()));
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return "list";
    }

    @RequestMapping("/share")
    public String materialUrl(String url, String imgUrl, Model model) {
        model.addAttribute("url", url);
        String urlContent = wechatMaterialService.showContent(url);
        model.addAttribute("imgUrl", imgUrl);
        model.addAttribute("urlContent", urlContent);
        return "share";
    }

    @RequestMapping("/imgUrl")
    public void imgUrl(String url, HttpServletResponse httpServletResponse) throws IOException {

        httpServletResponse.setContentType("image/png");
        httpServletResponse.getOutputStream().write(wechatMaterialService.getWechatUrl(url));
    }

}
