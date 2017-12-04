package com.project.processor.event.impl;

import com.project.common.exception.BusinessException;
import com.project.processor.event.EventProcessor;
import com.project.service.user.impl.UserRelationService;
import com.project.service.user.impl.UserService;
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
    private UserRelationService userRelationService;

    @Override
    public String doProcess(Map<String, String> map) throws BusinessException {
        logger.info("==============这是取消关注事件！");
        String  cancelUser= map.get("FromUserName");
            //获取用户信息
            String cancleUserInfo = wechatAccessService.getUserInfo(cancelUser);
            logger.info("取消关注的粉丝信息：" + cancleUserInfo);
            userRelationService.insert(null, cancleUserInfo, Relation.Unsubscrib.getValue());
        return "success";
    }
}
