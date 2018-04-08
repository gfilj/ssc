package com.project.service.userprivilege.impl;

import com.project.model.sql.UserPrivilege;
import com.project.service.userprivilege.UserPrivilegeService;
import org.springframework.stereotype.Service;

/**
 * Create by Fenix_Bao on 2018/4/1.
 */
@Service("UserPrivilegeService")
public class UserPrivilegeServiceImpl implements UserPrivilegeService {
    @Override
    public boolean set(UserPrivilege userPrivilege) throws Exception {
        return false;
    }


    @Override
    public UserPrivilege get(String openid) throws Exception {
        return null;
    }
}
