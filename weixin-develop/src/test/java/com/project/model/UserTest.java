package com.project.model;

import com.project.BaseTest;
import com.project.model.sql.User;
import org.junit.Test;

/**
 * Created by goforit on 2018/6/5.
 */
public class UserTest extends BaseTest{

    @Test
    public void test() throws Exception {
        System.out.println(User.class.getDeclaredConstructor(new Class[0]));
    }
}
