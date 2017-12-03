package com.project.service;

import com.project.BaseTest;
import com.project.common.exception.BusinessException;
import com.project.model.customservice.CustomAccount;
import com.project.service.weixin.customer.WechatCustomerProperty;
import com.project.service.weixin.customer.WechatCustomerService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by goforit on 2017/11/26.
 */
public class WechatCustomServiceTest extends BaseTest {

    @Autowired
    private WechatCustomerProperty wechatCustomerProperty;
    @Autowired
    private WechatCustomerService wechatCustomerService;

    private CustomAccount getCustomAccount() {
        CustomAccount customAccount = new CustomAccount();
        customAccount.setKf_account("001@gh_1249b1cbdda1");
        customAccount.setNickname("客服1");
        customAccount.setPassword("123456");
        return customAccount;
    }

    @Test
    public void testAdd() throws BusinessException {
        CustomAccount customAccount = getCustomAccount();
        System.out.println(wechatCustomerService.add(customAccount));
    }


    @Test
    public void testUpdate() throws BusinessException {
        CustomAccount customAccount = getCustomAccount();
        System.out.println(wechatCustomerService.update(customAccount));
    }

    @Test
    public void testGetKfList() throws BusinessException{
        System.out.println(wechatCustomerService.getkflist());
    }

    @Test
    public void testWechatCustomerProperty(){
        System.out.println(wechatCustomerProperty);
    }


}
