package com.project.service;

import com.project.BaseTest;
import com.project.common.exception.BusinessException;
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
    public void createMenuTest() throws BusinessException {
        System.out.println(wechatMenuService.createMenu());
    }
    @Test
    public void testInit(){
        System.out.println(wechatMenuService.init());
    }
    @Test
    public void testCeateMenuJson(){
        System.out.println(wechatMenuService.createMenuJson());
    }
}
