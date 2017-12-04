package com.project.provider;

import com.project.model.vo.Page;
import org.junit.Test;

/**
 * Created by goforit on 2017/12/3.
 */
public class UserSqlProviderTest {

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
}
