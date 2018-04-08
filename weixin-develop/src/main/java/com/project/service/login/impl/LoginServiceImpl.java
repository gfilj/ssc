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
    public UserPrivilege login(UserPrivilege userPrivilege) throws Exception {

        String openid = userPrivilege.getOpenid();

        UserPrivilege userPrivilege = userPrivilegeService.get(openid);
        if (userInfo == null) {
            if(userInfoVO.getRealname() == null){
                userInfoVO.setRealname(userid);
            }
            userInfoService.addUserInfo(userInfoVO);
        }else{
            if (userInfo.getDel() != null && userInfo.getDel() == 1) {
                throw new BusinessException(StatusCode.USER_DEL,"用户已经被删除，请联系相关管理人员！");
            }
            userInfoVO.setRealname(userInfo.getRealname());
            userInfoVO.setBuloid(userInfo.getBuloid());
            userInfoVO.setBuloname(userInfo.getBuloname());
        }
        try{
            userInfoService.updateLoginInfo(userInfoVO);
        }catch(Exception e){
            logger.error("用户{}更新登录信息失败",userInfoVO.getUserid());
        }
        return userInfoVO;
    }

    @Override
    public boolean loginOut(UserInfoVO userInfoVO) {
        return false;
    }
}
