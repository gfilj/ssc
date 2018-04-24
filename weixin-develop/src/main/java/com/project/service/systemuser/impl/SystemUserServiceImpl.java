package com.project.service.systemuser.impl;

import com.project.mapper.SystemUserMapper;
import com.project.model.sql.SystemUser;
import com.project.service.systemuser.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by Fenix_Bao on 2018/4/1.
 */
@Service("SystemUserService")
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserMapper systemUserMapper;

    @Override
    public int insert(SystemUser systemUser) throws Exception {
        return systemUserMapper.insert(systemUser);
    }


    @Override
    public SystemUser select(String username) throws Exception {
        return systemUserMapper.selectOne(username);
    }
}
