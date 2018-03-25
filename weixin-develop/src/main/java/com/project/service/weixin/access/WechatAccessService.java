package com.project.service.weixin.access;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.project.common.exception.BusinessException;
import com.project.common.exception.ExceptionEnum;
import com.project.common.model.Row;
import com.project.common.network.NetUtil;
import com.project.common.util.LogUtil;
import com.project.model.AccessTokenEntity;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by goforit on 2017/10/23.
 */
@Service
public class WechatAccessService {

    protected Log logger = LogUtil.getLogger(getClass());

    @Autowired
    protected WechatAccessProperty wechatAccessProperty;

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
                .toStringMap();
        try {
            String httpContent = NetUtil.getHttpContent(
                    wechatAccessProperty.getTokenUrl(),
                    tokenMap,
                    false,
                    wechatAccessProperty.getCharset(),
                    wechatAccessProperty.getCharset());
            logger.info("获取access_token返回的内容：" + httpContent);
            AccessTokenEntity accessTokenEntity = JSON.parseObject(httpContent, AccessTokenEntity.class);
            return accessTokenEntity.getAccess_token();
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.WECHAT_REQUEST_ERROR, e.getMessage(), e);
        }
    }

    /**
     * 生成长效二维码Ticket
     *
     * @return
     */
    public String createTicket(String qrMessage) throws BusinessException {
        String access_token = getToken();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action_name", wechatAccessProperty.getAction_name());
        JSONObject action_info = new JSONObject();
        JSONObject scene = new JSONObject();
        scene.put("scene_str", qrMessage);
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

    /**
     * 获取展示二维码的请求连接
     *
     * @throws Exception
     */
    public String generateShowRRUrl(String qrMessage) throws BusinessException {
        try {
            String ticketJson = createTicket(qrMessage);
            String ticket = JSON.parseObject(ticketJson).getString("ticket");

            logger.info("获取ticket:" + ticket);
            return String.format(wechatAccessProperty.getTicketUrl(), URLEncoder.encode(ticket, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new BusinessException(ExceptionEnum.ENCODE_UNSURRPOT_CAUSE, e.getMessage(), e);
        }
    }

    /**
     * 获取用户信息
     *
     * @throws Exception
     */
    public String getUserInfo(String openId) throws BusinessException {
        String access_token = getToken();
        Map<String, String> stringStringMap = Row.getInstance().put("access_token", access_token).put("openid", openId).put("lang", "zh_CN").toStringMap();
        try {
            return NetUtil.getHttpContent(
                    wechatAccessProperty.getUserInfoUrl(),
                    stringStringMap,
                    false,
                    wechatAccessProperty.getCharset(),
                    wechatAccessProperty.getCharset()
            );
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.WECHAT_REQUEST_ERROR, e.getMessage(), e);
        }
    }


}
