package com.project.model.material;

import java.util.List;

/**
 * Created by goforit on 2017/11/27.
 */
public class Content {
    private List<New> news_item;
    private Long create_time;
    private Long update_time;

    public List<New> getNews_item() {
        return news_item;
    }

    public void setNews_item(List<New> news_item) {
        this.news_item = news_item;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public Long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Long update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "Content{" +
                "news_item=" + news_item +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                '}';
    }
}
