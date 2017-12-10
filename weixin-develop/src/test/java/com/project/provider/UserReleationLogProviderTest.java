package com.project.provider;

import com.project.model.sql.UserRelation;
import com.project.model.vo.Page;
import org.junit.Test;

/**
 * Created by goforit on 2017/12/10.
 */
public class UserReleationLogProviderTest {
    @Test
    public void testSelectList() throws Exception {
        System.out.println(getUserRelationLogSqlProvider().selectList(getPage(),getUserReleation()));
    }

    private UserRelationLogSqlProvider getUserRelationLogSqlProvider() {
        return new UserRelationLogSqlProvider();
    }

    private UserRelation getUserReleation(){
        UserRelation userRelation = new UserRelation();
        userRelation.setReleation(1);
        userRelation.setNewmember("dfsf");
        userRelation.setNewmembername("dfad");
        userRelation.setIntroducername("fdsaf");
        return userRelation;
    }
    private Page getPage(){
        return new Page();
    }
}
