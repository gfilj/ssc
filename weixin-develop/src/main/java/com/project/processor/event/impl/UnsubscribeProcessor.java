package com.project.processor.event.impl;

import com.project.common.exception.BusinessException;
import com.project.processor.event.EventProcessor;
import com.project.service.user.impl.UserService;
import com.project.service.weixin.user.WechatUserReleationLogService;
import com.project.type.Relation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 关注处理器
 * Created by goforit on 2017/11/25.
 */
@Service
public class UnsubscribeProcessor extends AbstractEventProcessor implements EventProcessor {

    @Autowired
    private UserService userService;
    @Autowired
    private WechatUserReleationLogService wechatUserReleationLogService;

    @Override
    public String doProcess(Map<String, String> map) throws BusinessException {
        logger.info("==============这是取消关注事件！");
        String cancelUser = map.get("FromUserName");
        //获取用户信息
        String cancleUserInfo = wechatAccessService.getUserInfo(cancelUser);
        logger.info("取消关注的粉丝信息：" + cancleUserInfo);
        wechatUserReleationLogService.insert(null, cancleUserInfo, Relation.Unsubscrib.getValue());
        return "success";
    }
}
