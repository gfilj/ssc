package com.project.service.user.impl;

import com.project.common.exception.BusinessException;
import com.project.common.exception.ExceptionEnum;
import com.project.common.util.LogUtil;
import com.project.mapper.UserRelationMapper;
import com.project.model.sql.User;
import com.project.model.sql.UserRelation;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by goforit on 2017/12/3.
 */
@Service
public class UserRelationService extends AbstracUserService<UserRelation> {


    @Autowired
    private UserRelationMapper userRelationMapper;

    @Autowired
    private UserService userService;

    private Logger logger = LogUtil.getLogger(UserService.class);


    public int insert(UserRelation userRelation) throws BusinessException {
        try {
            return userRelationMapper.insert(userRelation);
        } catch (Throwable e) {
            logger.error("用户关系插入报错", e);
            throw new BusinessException(ExceptionEnum.DATA_CAUSE, userRelation);
        }
    }

    public int insert(String introduce, String member, int releation) throws BusinessException {
        return insert(generateUserRelation(introduce, member, releation));
    }

    public UserRelation generateUserRelation(String introduce, String member, int releation) {
        User user = userService.parse(introduce, "subscribe_time");
        User newUser = userService.parse(member, "subscribe_time");
        UserRelation userRelation = new UserRelation();
        userRelation.setIntroducer(user.getOpenid());
        userRelation.setIntroducername(user.getNickname());
        userRelation.setNewmember(newUser.getOpenid());
        userRelation.setNewmembername(newUser.getNickname());
        userRelation.setLmodify(newUser.getSubscribe_time());
        userRelation.setReleation(releation);
        return userRelation;
    }
}
