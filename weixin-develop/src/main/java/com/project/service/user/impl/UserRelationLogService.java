package com.project.service.user.impl;

import com.project.common.exception.BusinessException;
import com.project.common.exception.ExceptionEnum;
import com.project.common.util.LogUtil;
import com.project.mapper.UserRelationLogMapper;
import com.project.model.sql.UserRelation;
import com.project.service.user.AbstracUserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by goforit on 2017/12/3.
 */
@Service
public class UserRelationLogService extends AbstracUserService<UserRelation> {


    @Autowired
    private UserRelationLogMapper userRelationLogMapper;


    private Logger logger = LogUtil.getLogger(UserRelationLogService.class);

    /**
     *
     * @param userRelation
     * @return
     * @throws BusinessException
     */
    public int insert(UserRelation userRelation) throws BusinessException {
        try {
            return userRelationLogMapper.insert(userRelation);
        } catch (Throwable e) {
            logger.error("用户关系插入报错", e);
            throw new BusinessException(ExceptionEnum.DATA_CAUSE, userRelation);
        }
    }

}
