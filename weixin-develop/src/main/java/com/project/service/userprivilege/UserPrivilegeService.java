package com.project.service.userprivilege;

import com.project.model.sql.UserPrivilege;

/**
 * $myDescription
 * Create by Fenix_Bao on 2018/4/1.
 */
public interface UserPrivilegeService {
    boolean set(UserPrivilege userPrivilege) throws Exception;
    UserPrivilege get(String openid) throws Exception;
}
