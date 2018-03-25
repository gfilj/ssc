package com.project.service.user;

import com.github.pagehelper.PageInfo;
import com.project.BaseTest;
import com.project.common.exception.BusinessException;
import com.project.common.util.LogUtil;
import com.project.model.sql.User;
import com.project.model.vo.Page;
import com.project.service.weixin.user.WechatUserService;
import org.apache.commons.logging.Log;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.project.common.util.LogUtil.getLogger;
import static com.project.common.util.LogUtil.logstr;

/**
 * Created by goforit on 2018/1/16.
 */
public class WechatUserServiceTest extends BaseTest {
    @Autowired
    private WechatUserService wechatUserService;

    Log logger = getLogger(getClass());


    Page instance(){
        Page page = new Page();
        page.setOffset(0);
        page.setSize(3);
        return page;
    }

    /**
     * 不能准确查出结果，但能反映出一些数据
     * @throws BusinessException
     */
    @Test
    public void list() throws BusinessException {
        String funcname = "分页选择";
        PageInfo<User> list = wechatUserService.list(instance());
        logger.info(logstr(funcname,"分页数据",list));
    }
}
