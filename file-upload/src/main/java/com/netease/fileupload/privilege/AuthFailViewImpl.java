package com.netease.fileupload.privilege;

import com.netease.common.privilege.client.base.AuthFailView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by goforit on 2017/9/11.
 */
public class AuthFailViewImpl implements AuthFailView {
    @Override
    public void toView(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int i) {
        toJsonView(httpServletRequest,httpServletResponse,i);
    }
    private void toJsonView(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse,
                            int paramInt){
        paramHttpServletResponse.addHeader("content-type", "application/json;charset=utf-8");
        try {
            paramHttpServletResponse.getWriter().println("{\"resultcode\":\"403\",\"msg\":\"无此权限，请联系管理员\"}");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
