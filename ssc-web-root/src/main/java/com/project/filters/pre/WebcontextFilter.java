package com.project.filters.pre;

import com.netflix.zuul.ZuulFilter;

/**
 * Created by goforit on 2017/8/31.
 */
//@Component
class WebContextFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {
        return null;
    }
}
