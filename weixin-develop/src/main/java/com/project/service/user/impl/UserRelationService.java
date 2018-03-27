package com.project.service.user.impl;

import com.project.common.exception.BusinessException;
import com.project.common.exception.ExceptionEnum;
import com.project.common.util.LogUtil;
import com.project.mapper.UserRelationMapper;
import com.project.model.sql.UserRelation;
import com.project.model.vo.Page;
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
public class UserRelationService extends AbstracUserService<UserRelation> {


    @Autowired
    private UserRelationMapper userRelationMapper;


    private Log logger = LogUtil.getLogger(UserRelationService.class);

    /**
     *
     * @param userRelation
     * @return
     * @throws BusinessException
     */
    public int insert(UserRelation userRelation) throws BusinessException {
        try {
            return userRelationMapper.insert(userRelation);
        } catch (Throwable e) {
            logger.error("用户关系插入报错", e);
            throw new BusinessException(ExceptionEnum.DATA_CAUSE, userRelation);
        }
    }


    /**
     * 选择数据的总条数
     * @return
     * @throws BusinessException
     */
    public int selectPageListCount() throws BusinessException{
        try {
            return userRelationMapper.selectPageListCount();
        } catch (Throwable e) {
            logger.error("用户插入报错", e);
            throw new BusinessException(ExceptionEnum.DATA_CAUSE, "selectPageListCount");
        }
    }

    /**
     * 选择返回列表
     * @param page
     * @return
     * @throws BusinessException
     */
    public List<UserRelation> selectPageList(Page page) throws BusinessException{
        try {
            return userRelationMapper.selectPageList(page);
        } catch (Throwable e) {
            logger.error("用户插入报错", e);
            throw new BusinessException(ExceptionEnum.DATA_CAUSE, "selectPageList");
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
            return userRelationMapper.selectList();
        } catch (Throwable e) {
            logger.error(LogUtil.logstr(funcname,"报错",""), e);
            throw new BusinessException(ExceptionEnum.DATA_CAUSE, "selectList");
        }
    }

}
