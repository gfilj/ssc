package com.project.service.user.impl;

import com.project.common.exception.BusinessException;
import com.project.common.exception.ExceptionEnum;
import com.project.common.util.LogUtil;
import com.project.mapper.UserMapper;
import com.project.model.sql.User;
import com.project.model.vo.Page;
import com.project.service.user.AbstracUserService;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by goforit on 2017/12/2.
 */
@Service
public class UserService extends AbstracUserService<User> {

    @Autowired
    private UserMapper userMapper;

    private Log logger = LogUtil.getLogger(UserService.class);

    public int insert(String userJson) throws BusinessException {
        return insert(parse(userJson, "subscribe_time"));
    }

    /**
     * 插入数据
     *
     * @param user
     * @return
     * @throws BusinessException
     */
    public int insert(User user) throws BusinessException {
        String funcname = "插入用户数据";
        try {
            return userMapper.insert(user);
        } catch (Throwable e) {
            logger.error(LogUtil.logstr(funcname,"报错",user), e);
            throw new BusinessException(ExceptionEnum.DATA_CAUSE, user);
        }
    }

    /**
     * 选择数据的总条数
     *
     * @return
     * @throws BusinessException
     */
    public int selectPageListCount() throws BusinessException {
        String funcname = "选择列表总数";
        try {
            return userMapper.selectPageListCount();
        } catch (Throwable e) {
            logger.error(LogUtil.logstr(funcname,"报错",""), e);
            throw new BusinessException(ExceptionEnum.DATA_CAUSE, "selectPageListCount");
        }
    }

    /**
     * 选择返回列表
     *
     * @param page
     * @return
     * @throws BusinessException
     */
    public List<User> selectPageList(Page page) throws BusinessException {
        String funcname = "选择分页数据";
        try {
            return userMapper.selectPageList(page);
        } catch (Throwable e) {
            logger.error(LogUtil.logstr(funcname,"报错",page), e);
            throw new BusinessException(ExceptionEnum.DATA_CAUSE, "selectPageList");
        }
    }

    /**
     * 选择列表数据
     * @return
     * @throws BusinessException
     */
    public List<User> selectList() throws BusinessException{
        String funcname = "选择列表数据";
        try {
            return userMapper.selectList();
        } catch (Throwable e) {
            logger.error(LogUtil.logstr(funcname,"报错",""), e);
            throw new BusinessException(ExceptionEnum.DATA_CAUSE, "selectList");
        }
    }

}
