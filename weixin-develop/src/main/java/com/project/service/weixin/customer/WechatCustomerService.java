package com.project.service.weixin.customer;

import com.alibaba.fastjson.JSON;
import com.project.common.exception.BusinessException;
import com.project.common.exception.ExceptionEnum;
import com.project.common.network.NetUtil;
import com.project.model.customservice.CustomAccount;
import com.project.service.weixin.access.WechatAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by goforit on 2017/11/26.
 */
@Service
public class WechatCustomerService extends WechatAccessService {

    @Autowired
    private WechatCustomerProperty wechatCustomerProperty;
    /**
     * 添加客服账号
     * @param customAccount
     * @return
     * @throws BusinessException
     */
    public String add(CustomAccount customAccount) throws BusinessException {
        String access_token = getToken();
        try {
            String httpContent = NetUtil.getHttpContent(
                    String.format(wechatCustomerProperty.getAdd(),access_token),
                    JSON.toJSONString(customAccount),
                    wechatAccessProperty.getCharset(),
                    wechatAccessProperty.getCharset());
            return httpContent;
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.WECHAT_REQUEST_ERROR, e.getMessage(), e);
        }
    }

    /**
     * 修改客服账号
     * @param customAccount
     * @return
     * @throws BusinessException
     */
    public String update(CustomAccount customAccount) throws BusinessException{
        String access_token = getToken();
        try {
            String httpContent = NetUtil.getHttpContent(
                    String.format(wechatCustomerProperty.getAdd(),access_token),
                    JSON.toJSONString(customAccount),
                    wechatAccessProperty.getCharset(),
                    wechatAccessProperty.getCharset());
            return httpContent;
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.WECHAT_REQUEST_ERROR, e.getMessage(), e);
        }
    }

    /**
     * 获取账号列表
     * @param customAccount
     * @return
     * @throws BusinessException
     */
    public String getkflist() throws BusinessException{
        String access_token = getToken();
        try {
            String httpContent = NetUtil.getHttpContent(
                    String.format(wechatCustomerProperty.getGetkflist(),access_token),
                    null,
                    false,
                    wechatAccessProperty.getCharset(),
                    wechatAccessProperty.getCharset());
            return httpContent;
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.WECHAT_REQUEST_ERROR, e.getMessage(), e);
        }
    }
}
