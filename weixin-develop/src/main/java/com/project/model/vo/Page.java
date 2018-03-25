package com.project.model.vo;

import org.omg.CORBA.INTERNAL;

import java.io.Serializable;
import java.util.List;

/**
 * 分页传入对象
 * Created by goforit on 2018/1/16.
 */
public class Page implements Serializable {

    private static final long serialVersionUID = -2898739937885595126L;

    private List<?> datas;//数据集
    private Integer offset;   //请求偏移量
    private Integer size;     //页条数
    private Integer total;   //总条数
    private Integer totalPage;//总页数

    public Page() {

    }

    public Page(List<?> datas, Integer total, Integer size, Integer offset) {
        this.datas = datas;
        this.offset = offset;
        this.size = size;
        this.total = total;
        if (size > 0) {
            totalPage = (int) (total / size + ((total % size == 0) ? 0 : 1));
        } else {
            totalPage = 0;
        }
    }

    public Page(List<?> datas, Integer total, int size, Integer offset, int totalPage) {
        this.datas = datas;
        this.offset = offset;
        this.size = size;
        this.total = total;
        this.totalPage = totalPage;
    }


    //set get
    public List<?> getDatas() {
        return datas;
    }

    public void setDatas(List<?> datas) {
        this.datas = datas;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public int getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

}
