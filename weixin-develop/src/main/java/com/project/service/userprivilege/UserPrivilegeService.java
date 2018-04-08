package com.project.service.userprivilege;

import com.project.model.sql.UserPrivilege;

/**
 * $myDescription
 * Create by Fenix_Bao on 2018/4/1.
 */
public interface UserPrivilegeService {
    boolean set(String userPrivilege) throws Exception;
    boolean get(String openid) throws Exception;
}
