package com.project.service.weixin.user;

import com.project.common.exception.BusinessException;
import com.project.common.util.LogUtil;
import com.project.model.sql.User;
import com.project.model.sql.UserRelation;
import com.project.model.vo.Page;
import com.project.service.user.impl.UserRelationService;
import com.project.service.user.impl.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by goforit on 2017/12/10.
 */
@Service
public class WechatUserReleationService {

    @Autowired
    private UserRelationService userRelationService;
    @Autowired
    private UserService userService;
    private Logger logger = LogUtil.getLogger(getClass());

    public List<UserRelation> list(Page page) throws BusinessException {
        int count = userRelationService.selectPageListCount();
        page.setRowcountAndCompute(count);
        logger.info(page.toString());
        List<UserRelation> userRelations = userRelationService.selectPageList(page);
        int i=1;
        for(UserRelation userRelation:userRelations){
            userRelation.setId(page.getStart()+i);
            i++;
        }
        return userRelations;
    }

    /**
     * 外部暴露接口
     * @param introduce
     * @param member
     * @param releation
     * @return
     * @throws BusinessException
     */
    public int insert(String introduce, String member, int releation) throws BusinessException {
        return userRelationService.insert(generateUserRelation(introduce, member, releation));
    }

    /**
     * 生成reletion
     * @param introduce
     * @param member
     * @param releation
     * @return
     */
    protected UserRelation generateUserRelation(String introduce, String member, int releation,String parameter) {
        UserRelation userRelation = new UserRelation();
        if(introduce !=null){
            User user = userService.parse(introduce, parameter);
            userRelation.setIntroducer(user.getOpenid());
            userRelation.setIntroducername(user.getNickname());
        }
        User newUser = userService.parse(member, parameter);
        userRelation.setNewmember(newUser.getOpenid());
        userRelation.setNewmembername(newUser.getNickname());
        userRelation.setLmodify(new Date());
        userRelation.setReleation(releation);
        return userRelation;
    }


    /**
     * 生成reletion
     * @param introduce
     * @param member
     * @param releation
     * @return
     */
    protected UserRelation generateUserRelation(String introduce, String member, int releation) {
        return generateUserRelation(introduce,member,releation,"subscribe_time");
    }

    /**
     * 生成reletion
     * @param introduce
     * @param member
     * @param releation
     * @return
     */
    protected UserRelation generateUserRelationCancle(String introduce, String member, int releation) {
        return generateUserRelation(introduce,member,releation,"");
    }
}
