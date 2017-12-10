package com.project.service.weixin.user;

import com.project.common.exception.BusinessException;
import com.project.service.user.impl.UserRelationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by goforit on 2017/12/10.
 */
@Service
public class WechatUserReleationLogService extends WechatUserReleationService {

    @Autowired
    private UserRelationLogService userRelationLogService;

    /**
     * 外部暴露接口
     * @param introduce
     * @param member
     * @param releation
     * @return
     * @throws BusinessException
     */
    public int insert(String introduce, String member, int releation) throws BusinessException {
        return userRelationLogService.insert(generateUserRelation(introduce, member, releation));
    }


}
