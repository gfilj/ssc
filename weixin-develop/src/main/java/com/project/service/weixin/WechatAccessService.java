package com.project.service.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.project.common.exception.BusinessException;
import com.project.common.exception.ExceptionEnum;
import com.project.common.model.Row;
import com.project.common.network.NetUtil;
import com.project.common.util.LogUtil;
import com.project.model.AccessTokenEntity;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by goforit on 2017/10/23.
 */
@Service
public class WechatAccessService implements InitializingBean {

    private Logger logger = LogUtil.getLogger(getClass());

    @Autowired
    private WechatAccessProperty wechatAccessProperty;

    /**
     * 获取accessToken
     *
     * @return
     * @throws BusinessException
     */
    public String getToken() throws BusinessException {
        Map<String, String> tokenMap = Row.getInstance()
                .put("appid", wechatAccessProperty.getAppid())
                .put("secret", wechatAccessProperty.getSecret())
                .put("grant_type", wechatAccessProperty.getGrant_type())
                .getStringMap();
        try {
            String httpContent = NetUtil.getHttpContent(
                    wechatAccessProperty.getTokenUrl(),
                    tokenMap,
                    false,
                    wechatAccessProperty.getCharset(),
                    wechatAccessProperty.getCharset());
            AccessTokenEntity accessTokenEntity = JSON.parseObject(httpContent, AccessTokenEntity.class);
            return accessTokenEntity.getAccess_token();
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.WECHAT_REQUEST_ERROR, e.getMessage(), e);
        }
    }

    /**
     * 生成长效二维码
     *
     * @return
     */
    public String createQR(String access_token) throws BusinessException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action_name", wechatAccessProperty.getAction_name());
        JSONObject action_info = new JSONObject();
        JSONObject scene = new JSONObject();
        scene.put("scene_str", "openId");
        action_info.put("scene", scene);
        jsonObject.put("action_info", action_info);

        try {
            return NetUtil.getHttpContent(
                    String.format(wechatAccessProperty.getQrCodeUrl(), access_token),
                    jsonObject.toJSONString(),
                    wechatAccessProperty.getCharset(),
                    wechatAccessProperty.getCharset()
            );
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.WECHAT_REQUEST_ERROR, e.getMessage(), e);
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info(String.valueOf(wechatAccessProperty));
    }
}
