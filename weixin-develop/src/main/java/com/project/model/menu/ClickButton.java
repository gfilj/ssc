package com.project.model.menu;

/**
 * Created by goforit on 2017/11/25.
 */
public class ClickButton extends SupperButton {

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "ClickButton{" +
                "type='" + getType() + '\'' +
                ", name='" + getName() + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
