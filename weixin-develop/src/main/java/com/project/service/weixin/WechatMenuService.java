package com.project.service.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.project.common.exception.BusinessException;
import com.project.common.exception.ExceptionEnum;
import com.project.common.network.NetUtil;
import com.project.common.util.LogUtil;
import com.project.model.AccessTokenEntity;
import com.project.model.message.menu.ClickButton;
import com.project.model.message.menu.ViewButon;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
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

        ViewButon vbt=new ViewButon();
        vbt.setUrl("http://www.cuiyongzhi.com");
        vbt.setName("博客");
        vbt.setType("view");

        JSONArray sub_button=new JSONArray();
        sub_button.add(cbt);
        sub_button.add(vbt);

        JSONObject buttonOne=new JSONObject();
        buttonOne.put("name", "菜单");
        buttonOne.put("sub_button", sub_button);

        JSONArray button=new JSONArray();
        button.add(vbt);
        button.add(buttonOne);
        button.add(cbt);

        JSONObject menujson=new JSONObject();
        menujson.put("button", button);
        return menujson.toJSONString();
    }

    public String createMenu() throws BusinessException{
        try {
            String httpContent = NetUtil.getHttpContent(
                   String.format(wechatAccessProperty.getMenuUrl(),getToken()),
                    init(),
                    wechatAccessProperty.getCharset(),
                    wechatAccessProperty.getCharset());
            AccessTokenEntity accessTokenEntity = JSON.parseObject(httpContent, AccessTokenEntity.class);
            return accessTokenEntity.getAccess_token();
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.WECHAT_REQUEST_ERROR, e.getMessage(), e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            createMenu();
        } catch (BusinessException e) {
            logger.error("创建菜单按钮失败！", e);
        }
    }
}
