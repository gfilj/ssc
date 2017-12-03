package com.project.model.menu;

/**
 * Created by goforit on 2017/11/25.
 */
public class ViewButon extends SupperButton {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ViewButon{" +
                "type='" + getType() + '\'' +
                ", name='" + getName() + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
