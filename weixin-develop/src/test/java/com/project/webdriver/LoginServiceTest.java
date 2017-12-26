package com.project.webdriver;

import com.project.BaseTest;
import com.project.common.exception.BusinessException;
import com.project.webdriver.login.LoginService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by goforit on 2017/12/17.
 */
public class LoginServiceTest extends BaseTest{
    @Autowired
    private LoginService loginService;
    @Test
    public void testLogin() throws BusinessException {
        loginService.login();
    }
}
