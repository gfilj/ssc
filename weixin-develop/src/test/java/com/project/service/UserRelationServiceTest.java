package com.project.service;

import com.project.BaseTest;
import com.project.common.exception.BusinessException;
import com.project.service.user.impl.UserRelationService;
import com.project.service.user.impl.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by goforit on 2017/12/3.
 */
public class UserRelationServiceTest extends BaseTest {

    private String oldStr="{\"subscribe\":1,\"openid\":\"o2FwHuPY0t0SreBFgoePtK5hwGPU\",\"nickname\":\"陆长青\"," +
            "\"sex\":1,\"language\":\"zh_CN\",\"city\":\"海淀\",\"province\":\"北京\",\"country\":\"中国\",\"headimgurl\":\"http:\\/\\/wx.qlogo.cn\\/mmopen\\/zmTugyUCmcdptYNKhLmiarw35FOIrO2EvtzsHSeA5aLicjjia9lhj39WIHeiaArzDDIm9xTKg28Xu7CyMYIkyMiaGCdsia774g7YH2\\/0\",\"subscribe_time\":1508598238,\"remark\":\"\",\"groupid\":0,\"tagid_list\":[]}";
    private String newStr="{\"subscribe\":1,\"openid\":\"o2FwHuLjj53RY_RJ6ySo9DAa6Sa4\"," +
            "\"nickname\":\"陆儒江晚年幸福\",\"sex\":0,\"language\":\"zh_CN\",\"city\":\"\",\"province\":\"\",\"country\":\"\",\"headimgurl\":\"http:\\/\\/wx.qlogo.cn\\/mmopen\\/E32QV1mOcl1jibE9wsUMicFDBUgzXiaDwwJE2PC1JOFibltt4FprzkspaZBXa5zUBmSx08bA5rHN9Ccsvs5KAsichVWW6iabPZ9Bmz\\/0\",\"subscribe_time\":1512200313,\"remark\":\"\",\"groupid\":0,\"tagid_list\":[]}";
    @Autowired
    private UserRelationService userRelationService;
    @Autowired
    private UserService userService;
    @Test
    public void testInsert() throws BusinessException {
//        userRelationService.insert(null,newStr, Relation.Unsubscrib.getValue());
    }
}
