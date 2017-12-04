package com.project.service.user;

import com.project.common.exception.BusinessException;
import com.project.common.util.LogUtil;
import com.project.model.sql.User;
import com.project.model.vo.Page;
import com.project.service.user.impl.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by goforit on 2017/12/3.
 */
@Service
public class WechatUserService {

    @Autowired
    private UserService userService;
    private Logger logger = LogUtil.getLogger(getClass());

    public List<User> list(Page page) throws BusinessException {
        int count = userService.selectPageListCount();
        page.setRowcountAndCompute(count);
        logger.info(page.toString());
        return userService.selectPageList(page);
    }
}
