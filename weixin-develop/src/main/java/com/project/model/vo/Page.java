package com.project.model.vo;

import java.io.Serializable;

/**
 * Created by goforit on 2017/12/3.
 */
public class Page implements Serializable {

    private static final long serialVersionUID = -1692691572707557191L;

    public int getPageon() {
        return pageon;
    }

    public void setPageon(int pageon) {
        this.pageon = pageon;
    }

    public int getRowcount() {
        return rowcount;
    }

    public void setRowcount(int rowcount) {
        this.rowcount = rowcount;
    }

    public int getPagecount() {
        return pagecount;
    }

    public void setPagecount(int pagecount) {
        this.pagecount = pagecount;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public Page(int pageon, int row, int rowcount) {
        pageNumber = 11;
        this.pageon = pageon;
        this.row = row;
        this.rowcount = rowcount;
        compute();
    }

    public Page(int pageon, int row) {
        pageNumber = 11;
        this.pageon = pageon;
        this.row = row;
    }

    public Page(int pageon) {
        pageNumber = 11;
        this.pageon = pageon;
    }

    public Page() {
        pageNumber = 11;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void compute() {
        if (rowcount <= 0)
            return;
        if (row <= 0)
            row = 5;
        pagecount = rowcount % row != 0 ? rowcount / row + 1 : rowcount / row;
        if (pageon > pagecount-1)
            pageon = pagecount-1;
        if (pageon < 0)
            pageon = 0;
        start = (pageon) * row;
        end = (pageon+1) * row ;
        if (end > rowcount)
            end = rowcount;
        first = pageon==0;
        last= pageon==(pagecount-1);
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setRowcountAndCompute(int rowcount) {
        this.rowcount = rowcount;
        compute();
    }

    protected int pageon;//开始页
    protected int rowcount;
    protected int pagecount;//总页数
    protected int row;
    protected int start;
    protected int end;
    protected int pageNumber;
    protected String url;
    protected boolean first;
    protected boolean last;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageon=" + pageon +
                ", rowcount=" + rowcount +
                ", pagecount=" + pagecount +
                ", row=" + row +
                ", start=" + start +
                ", end=" + end +
                ", pageNumber=" + pageNumber +
                ", url='" + url + '\'' +
                ", first=" + first +
                ", last=" + last +
                '}';
    }
}
