package com.daolion.net;

import java.util.ArrayList;

public class RspUpdate {
    private int total;
    private int pageSize;
    private int totalPage;
    private int page;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public ArrayList<UpdateDetail> getData() {
        return data;
    }

    public void setData(ArrayList<UpdateDetail> data) {
        this.data = data;
    }

    private int offset;
    private ArrayList<UpdateDetail> data;
}
