package com.project.service.user.impl;

import com.project.common.exception.BusinessException;
import com.project.common.exception.ExceptionEnum;
import com.project.common.util.LogUtil;
import com.project.mapper.UserRelationLogMapper;
import com.project.model.sql.UserRelation;
import com.project.service.user.AbstracUserService;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by goforit on 2017/12/3.
 */
@Service
public class UserRelationLogService extends AbstracUserService<UserRelation> {


    @Autowired
    private UserRelationLogMapper userRelationLogMapper;


    private Log logger = LogUtil.getLogger(UserRelationLogService.class);

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

    /**
     * 选择列表数据
     * @return
     * @throws BusinessException
     */
    public List<UserRelation> selectList() throws BusinessException{
        String funcname = "选择列表数据";
        try {
            return userRelationLogMapper.selectListNew();
        } catch (Throwable e) {
            logger.error(LogUtil.logstr(funcname,"报错",""), e);
            throw new BusinessException(ExceptionEnum.DATA_CAUSE, "selectList");
        }
    }

    /**
     * 更新
     * @param userRelation
     * @return
     * @throws BusinessException
     */
    public int update(UserRelation userRelation) throws BusinessException {
        try {
            return userRelationLogMapper.update(userRelation);
        } catch (Throwable e) {
            logger.error("更新报错", e);
            throw new BusinessException(ExceptionEnum.DATA_CAUSE, userRelation);
        }
    }

}
