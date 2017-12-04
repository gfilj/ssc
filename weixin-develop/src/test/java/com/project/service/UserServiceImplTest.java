package com.project.service;

import com.project.BaseTest;
import com.project.common.exception.BusinessException;
import com.project.model.vo.Page;
import com.project.service.user.impl.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by goforit on 2017/12/2.
 */

public class UserServiceImplTest extends BaseTest {

    @Autowired
    private UserService userService;

//    @Test
//    public void testParseUser(){
//        System.out.println(userService.parse(jsonStr(),"subscribe_time"));
//    }

    private String jsonStr() {
        return "{\"subscribe\":1,\"openid\":\"o2FwHuPY0t0SreBFgoePtK5hwGPU\",\"nickname\":\"陆长青\",\"sex\":1,\"language\":\"zh_CN\",\"city\":\"海淀\",\"province\":\"北京\",\"country\":\"中国\",\"headimgurl\":\"http:\\/\\/wx.qlogo.cn\\/mmopen\\/zmTugyUCmcdptYNKhLmiarw35FOIrO2EvtzsHSeA5aLicjjia9lhj39WIHeiaArzDDIm9xTKg28Xu7CyMYIkyMiaGCdsia774g7YH2\\/0\",\"subscribe_time\":1508598238,\"remark\":\"\",\"groupid\":0,\"tagid_list\":[]}";
    }

    @Test
    public void testInserUser() throws BusinessException {
        userService.insert(jsonStr());
    }

    @Test
    public void testSelectPageList() throws BusinessException {
        Page page = new Page(1);
        page.setRowcountAndCompute(1000);
        System.out.println(page);
        System.out.println(userService.selectPageList(page));
    }

    @Test
    public void testSelectPageListCount() throws BusinessException {
        System.out.println(userService.selectPageListCount());
    }

}
