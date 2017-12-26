package com.project.webdriver.login;

import com.project.common.exception.BusinessException;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 * Created by goforit on 2017/12/17.
 */
public interface LoginService {

    /**
     * 登录
     * @throws BusinessException
     */
    void login() throws BusinessException;

    /**
     * 是否成功
     * @return
     */
    boolean isSuccess();

    /**
     * 输入验证码
     */
    void enterCode(String code) throws BusinessException;

    /**
     * 输出当前的页面
     */
    String currentSource();
}
