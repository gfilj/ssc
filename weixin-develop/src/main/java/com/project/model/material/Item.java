package com.project.model.material;

/**
 * Created by goforit on 2017/11/27.
 */
public class Item {
    private String media_id;
    private Long update_time;
    private Content content;

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public Long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Long update_time) {
        this.update_time = update_time;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Item{" +
                "media_id='" + media_id + '\'' +
                ", update_time=" + update_time +
                ", content=" + content +
                '}';
    }
}
