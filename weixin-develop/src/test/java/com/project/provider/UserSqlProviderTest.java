package com.project.provider;

import com.project.common.util.LogUtil;
import com.project.model.vo.Page;
import org.apache.commons.logging.Log;
import org.junit.Test;

import static com.project.common.util.LogUtil.getLogger;
import static com.project.common.util.LogUtil.logstr;

/**
 * Created by goforit on 2017/12/3.
 */
public class UserSqlProviderTest {

    Log logger = getLogger(getClass());
    @Test
    public void selectPageListCount(){
        System.out.println(getUserSqlProvider().selectPageListCount());
    }

    private UserSqlProvider getUserSqlProvider() {
        return new UserSqlProvider();
    }

    private Page getPage(){
        return new Page();
    }
    @Test
    public void selectPageList(){
        System.out.println(getUserSqlProvider().selectPageList(getPage()));
    }
    @Test
    public void selectList(){
        String funcname = "选择列表";
        logger.info(logstr(funcname,"查询语句",getUserSqlProvider().selectList()));
    }
}
