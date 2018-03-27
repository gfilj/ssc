package com.project.service.weixin.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.common.exception.BusinessException;
import com.project.common.util.LogUtil;
import com.project.config.PageHelperConfiguration;
import com.project.model.sql.User;
import com.project.model.sql.UserRelation;
import com.project.model.vo.Page;
import com.project.service.user.impl.UserRelationService;
import com.project.service.user.impl.UserService;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.project.common.util.LogUtil.logstr;

/**
 * Created by goforit on 2017/12/10.
 */
@Service
public class WechatUserReleationService {

    @Autowired
    private UserRelationService userRelationService;
    @Autowired
    private UserService userService;
    private Log logger = LogUtil.getLogger(getClass());

//    public List<UserRelation> list(Page page) throws BusinessException {
//        PageHelper.offsetPage(page.getOffset(),page.getSize());
//        int count = userRelationService.selectPageListCount();
////        page.setRowcountAndCompute(count);
//        logger.info(page.toString());
//        List<UserRelation> userRelations = userRelationService.selectPageList(page);
//        int i=1;
//        for(UserRelation userRelation:userRelations){
//            userRelation.setId(page.getOffset()+i);
//            i++;
//        }
//
//        return userRelations;
//    }

    public PageInfo<UserRelation> list(Page page) throws BusinessException {
        String funcname = "微信用户关系列表";
        PageHelper.offsetPage(page.getOffset(),page.getSize());
        logger.info(logstr(funcname,"偏移量",page.getOffset(),"页面大小",page.getSize()));
        List<UserRelation> userRelations = userRelationService.selectList();
        int i=1;
        for(UserRelation userRelation:userRelations){
            userRelation.setId(page.getOffset()+i);
            i++;
        }
        return new PageInfo<UserRelation>(userRelations);
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
