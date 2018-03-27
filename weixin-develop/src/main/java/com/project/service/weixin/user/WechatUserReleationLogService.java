package com.project.service.weixin.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.common.exception.BusinessException;
import com.project.common.util.LogUtil;
import com.project.model.sql.UserRelation;
import com.project.model.vo.Page;
import com.project.service.user.impl.UserRelationLogService;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.project.common.util.LogUtil.logstr;

/**
 * Created by goforit on 2017/12/10.
 */
@Service
public class WechatUserReleationLogService extends WechatUserReleationService {

    @Autowired
    private UserRelationLogService userRelationLogService;

    private Log logger = LogUtil.getLogger(getClass());

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

    /**
     * 外部暴露接口
     * @param introduce
     * @param member
     * @param releation
     * @return
     * @throws BusinessException
     */
    public int insertCancle(String introduce, String member, int releation) throws BusinessException {
        return userRelationLogService.insert(generateUserRelationCancle(introduce, member, releation));
    }

    public PageInfo<UserRelation> list(Page page) throws BusinessException {
        String funcname = "微信用户关系日志列表";
        PageHelper.offsetPage(page.getOffset(),page.getSize());
        logger.info(logstr(funcname,"偏移量",page.getOffset(),"页面大小",page.getSize()));
        List<UserRelation> userRelations = userRelationLogService.selectList();
        int i=1;
        for(UserRelation userRelation:userRelations){
            userRelation.setId(page.getOffset()+i);
            i++;
        }
        return new PageInfo<UserRelation>(userRelations);
    }

}
