package com.project.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.project.BaseTest;
import com.project.common.exception.BusinessException;
import com.project.model.menu.ClickButton;
import com.project.model.menu.ViewButon;
import com.project.service.weixin.menu.WechatMenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by goforit on 2017/11/25.
 */
public class WechatMenuServiceTest extends BaseTest{
    @Autowired
    private WechatMenuService wechatMenuService;
    @Test
    public void testCreateMenu() throws BusinessException {
        System.out.println(wechatMenuService.createMenu());
    }
    @Test
    public void testInit(){
        System.out.println(init());
    }
    @Test
    public void testCeateMenuJson(){
        System.out.println(wechatMenuService.createMenuJson());
    }

    public String init(){
        ClickButton cbt=new ClickButton();
        cbt.setKey("image");
        cbt.setName("回复图片");
        cbt.setType("click");

        ViewButon swl=new ViewButon();
        swl.setUrl("http://55951118.m.weimob.com/vshop/Index?PageId=541725&channel=menu");
        swl.setName("赛乐维官网");
        swl.setType("view");

        ViewButon jd=new ViewButon();
        jd.setUrl("https://shop.jd.hk/?shopId=672044");
        jd.setName("京东商城");
        jd.setType("view");

        ViewButon wq=new ViewButon();
        wq.setUrl("https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzUzODAwNDA0Ng==&scene=123#wechat_redirect");
        wq.setName("往期精彩");
        wq.setType("view");


        JSONArray sub_button=new JSONArray();
        sub_button.add(swl);
        sub_button.add(jd);
        sub_button.add(wq);

        JSONObject swlButton =new JSONObject();
        swlButton.put("name", "赛乐维");
        swlButton.put("sub_button", sub_button);

        ViewButon fxrule=new ViewButon();
        fxrule.setUrl("http://mp.weixin.qq.com/s/UAG06wtMcqk9VpFhKF0cqg");
        fxrule.setName("分销规则");
        fxrule.setType("view");

        ViewButon cjwt=new ViewButon();
        cjwt.setUrl("http://55951118.m.weimob.com/weisite/list?pid=55951118&bid=57129306&wechatid=fromUsername&ltid=2232545&wxref=mp.weixin.qq.com&channel=menu");
        cjwt.setName("常见问题");
        cjwt.setType("view");

        ViewButon kfzx=new ViewButon();
        kfzx.setUrl("http://55951118.im.m.weimob.com/?aid=55951118&channel=menu");
        kfzx.setName("客服咨询");
        kfzx.setType("view");


        JSONArray sub_button_kfzx=new JSONArray();
        sub_button_kfzx.add(fxrule);
        sub_button_kfzx.add(cjwt);
        sub_button_kfzx.add(kfzx);

        JSONObject kfzxButton =new JSONObject();
        kfzxButton.put("name", "客服中心");
        kfzxButton.put("sub_button", sub_button_kfzx);


        JSONArray button=new JSONArray();
        button.add(swlButton);
        button.add(kfzxButton);

        JSONObject menujson=new JSONObject();
        menujson.put("button", button);
        return menujson.toJSONString();
    }
}
