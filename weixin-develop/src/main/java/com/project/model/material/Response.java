package com.project.model.material;

import java.util.List;

/**
 * Created by goforit on 2017/11/27.
 */
public class Response {
    private List<Item> item;
    private int total_count;
    private int item_count;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Response{" +
                "item=" + item +
                ", total_count=" + total_count +
                ", item_count=" + item_count +
                '}';
    }
}
