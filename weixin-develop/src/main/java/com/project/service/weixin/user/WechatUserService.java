package com.project.service.weixin.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.common.exception.BusinessException;
import com.project.model.sql.User;
import com.project.model.vo.Page;
import com.project.service.user.impl.UserService;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.List;

import static com.project.common.util.LogUtil.getLogger;
import static com.project.common.util.LogUtil.logstr;

/**
 * 微信用户服务
 * Created by goforit on 2017/12/3.
 */
@Service
public class WechatUserService {

    @Autowired
    private UserService userService;
    private Log logger = getLogger(getClass());

    public PageInfo<User> list(Page page) throws BusinessException {
        String funcname = "微信用户列表";
        PageHelper.offsetPage(page.getOffset(),page.getSize());
        logger.info(logstr(funcname,"偏移量",page.getOffset(),"页面大小",page.getSize()));
        List<User> users = userService.selectList();
        int i=1;
        for(User user:users){
            user.setId(page.getOffset()+i);
            i++;
        }
        return new PageInfo<User>(users);
    }
}

