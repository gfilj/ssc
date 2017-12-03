package com.project.model.menu;

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

    private Map<String,List<ViewButon>> button;

    public Map<String, List<ViewButon>> getButton() {
        return button;
    }

    public void setButton(Map<String, List<ViewButon>> button) {
        this.button = button;
    }

}
