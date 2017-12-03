package com.project.service.user.impl;

import com.project.common.exception.BusinessException;
import com.project.common.exception.ExceptionEnum;
import com.project.common.util.LogUtil;
import com.project.mapper.UserMapper;
import com.project.model.sql.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by goforit on 2017/12/2.
 */
@Service
public class UserService extends AbstracUserService<User> {

    @Autowired
    private UserMapper userMapper;

    private Logger logger = LogUtil.getLogger(UserService.class);

    public int insert(String userJson) throws BusinessException {
        return insert(parse(userJson, "subscribe_time"));
    }

    public int insert(User user) throws BusinessException {
        try {
            return userMapper.insert(user);
        } catch (Throwable e) {
            logger.error("用户插入报错", e);
            throw new BusinessException(ExceptionEnum.DATA_CAUSE, user);
        }
    }

}
