package com.project.service.systemuser;

import com.project.model.sql.SystemUser;

/**
 * $myDescription
 * Create by Fenix_Bao on 2018/4/1.
 */
public interface SystemUserService {
    boolean insert(SystemUser systemUser) throws Exception;
    SystemUser select(String username) throws Exception;
}
