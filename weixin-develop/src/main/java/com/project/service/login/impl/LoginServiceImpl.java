package com.project.service.login.impl;

import com.project.common.util.LogUtil;
import com.project.model.sql.UserPrivilege;
import com.project.service.login.LoginService;
import com.project.service.userprivilege.UserPrivilegeService;
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
    UserPrivilegeService userPrivilegeService;

    @Override
    public UserPrivilege login(String openid) throws Exception {
        return userPrivilegeService.get(openid);
    }

    @Override
    public boolean loginOut(UserPrivilege userPrivilege) {
        return false;
    }
}
