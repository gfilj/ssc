package com.netease.fileupload.service.auth;

import com.netease.common.privilege.client.base.http.ContentEntity;
import com.netease.common.privilege.client.base.util.AuthUtil;
import com.netease.fileupload.model.Result;
import com.netease.fileupload.model.Row;
import com.netease.fileupload.model.User;
import com.netease.fileupload.model.builder.ResultBuilder;
import com.netease.fileupload.service.project.ProjectInfo;
import com.netease.fileupload.util.Constant;
import com.netease.fileupload.util.LogUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by goforit on 2017/9/18.
 */
@Service
public class AuthService {
    @Autowired
    private ProjectInfo projectInfo;

    private Logger logger = LogUtil.getLogger(getClass());

    public Result auth(User user) {
        Map<String, String> authMap = Row.getInstance()
                .put("userName", user.getUsername())
                .put("password", user.getPassword())
                .put("projectToken", projectInfo.getProjectToken())
                .put("projectKey", projectInfo.getProjectKey())
                .getStringMap();
        try {
            ContentEntity contentEntity = AuthUtil.executeRequest(authMap, projectInfo.getAuthApi(), true);
            logger.info(Constant.INFOStr(user.simpleInfo() + " auth success") + contentEntity.toString());
            return ResultBuilder.getInstance()
                    .setInfo(contentEntity.getMsg())
                    .setStatus(contentEntity.getResultcode())
                    .setData(contentEntity.getData())
                    .build();


        } catch (Exception e) {
            logger.error(Constant.ERRORStr(user.simpleInfo() + " auth error"), e);
            return AuthExceptionEnum.EXECUTEREQUEST_CAUSE.getResult();
        }

    }

}
