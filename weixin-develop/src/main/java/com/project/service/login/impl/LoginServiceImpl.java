package com.project.service.login.impl;

import com.project.common.util.LogUtil;
import com.project.model.sql.SystemUser;
import com.project.service.login.LoginService;
import com.project.service.systemuser.SystemUserService;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Create by Fenix_Bao on 2018/4/1.
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    private Log logger = LogUtil.getLogger(getClass());

    @Resource
    SystemUserService systemUserService;

    @Override
    public SystemUser login(String username) throws Exception {
        return systemUserService.select(username);
    }

    @Override
    public boolean loginOut(SystemUser systemUser) {
        return false;
    }
}
