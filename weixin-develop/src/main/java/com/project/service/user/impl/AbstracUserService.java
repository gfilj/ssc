package com.project.service.user.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.ParameterizedType;

/**
 * Created by goforit on 2017/12/3.
 */
public abstract class AbstracUserService<T> {

    /**
     * 将str转换为对应的实体
     *
     * @param userJson
     * @param parameter
     * @return
     */
    public T parse(String userJson, String parameter) {
        JSONObject jsonObject = JSON.parseObject(userJson);
        Long subscribe_time = jsonObject.getLong(parameter) * 1000;
        jsonObject.put(parameter, subscribe_time);
        return JSONObject.parseObject(jsonObject.toJSONString(), getTClass());

    }

    /**
     * 获取T.class
     *
     * @return
     */
    public Class<T> getTClass() {
        Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }
}
