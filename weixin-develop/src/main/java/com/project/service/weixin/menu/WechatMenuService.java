package com.project.service.weixin.menu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.project.common.exception.BusinessException;
import com.project.common.exception.ExceptionEnum;
import com.project.common.network.NetUtil;
import com.project.common.util.LogUtil;
import com.project.model.menu.Button;
import com.project.service.weixin.access.WechatAccessService;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by goforit on 2017/11/25.
 */
@Service
public class WechatMenuService extends WechatAccessService implements InitializingBean {

    private Log logger = LogUtil.getLogger(getClass());


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
