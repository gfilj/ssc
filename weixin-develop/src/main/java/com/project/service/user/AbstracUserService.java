package com.project.service.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.util.Date;

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
        if(!StringUtils.isBlank(parameter)){
            Long subscribe_time = jsonObject.getLong(parameter) * 1000;
            jsonObject.put(parameter, subscribe_time);
        }else{
            jsonObject.put("subscribe_time", new Date());
        }
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
