package com.project.service.weixin.menu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.project.common.exception.BusinessException;
import com.project.common.exception.ExceptionEnum;
import com.project.common.network.NetUtil;
import com.project.common.util.LogUtil;
import com.project.model.menu.Button;
import com.project.model.menu.ClickButton;
import com.project.model.menu.ViewButon;
import com.project.service.weixin.access.WechatAccessService;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by goforit on 2017/11/25.
 */
@Service
public class WechatMenuService extends WechatAccessService implements InitializingBean {

    private Logger logger = LogUtil.getLogger(getClass());


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

    @Autowired
    private Button button;

    public String createMenuJson(){
        JSONObject menuJson = new JSONObject();
        final JSONArray buttonArray = new JSONArray();
        button.getButton().forEach((k,v)->{
            JSONObject button = new JSONObject();
            JSONArray sub_button = new JSONArray();
            v.forEach((sub)->{
                sub_button.add(sub);
            });
            button.put("name",k);
            button.put("sub_button", sub_button);
            buttonArray.add(button);
        });
        menuJson.put("button",buttonArray);
        return menuJson.toJSONString();
    }

    public String createMenu() throws BusinessException{
        try {
            String httpContent = NetUtil.getHttpContent(
                   String.format(wechatAccessProperty.getMenuUrl(),getToken()),
                    createMenuJson(),
                    wechatAccessProperty.getCharset(),
                    wechatAccessProperty.getCharset());
//            AccessTokenEntity accessTokenEntity = JSON.parseObject(httpContent, AccessTokenEntity.class);
            return httpContent;
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.WECHAT_REQUEST_ERROR, e.getMessage(), e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        try {
//            String menu = createMenu();
//            logger.info("创建菜单按钮成功！" + menu);
//        } catch (BusinessException e) {
//            logger.error("创建菜单按钮失败！", e);
//        }
    }
}
