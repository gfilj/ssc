package com.project.model.menu;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by goforit on 2017/11/25.
 */
@Component
@ConfigurationProperties(prefix = "Button")
public class Button {

    private Map<String, List<JSONObject>> button;

    public Map<String, List<JSONObject>> getButton() {
        return button;
    }

    public void setButton(Map<String, List<JSONObject>> button) {
        this.button = button;
    }

}
