package com.brc.idauth.ui.searchsquare;

import com.daolion.net.response.SquareBean;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-18
       Time     :  20:55
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class SquareBeanChangeEvent {


    private SquareBean.DataBean dataBean;
    private int position;

    public SquareBeanChangeEvent() {
    }

    public SquareBeanChangeEvent(int position, SquareBean.DataBean dataBean) {
        this.dataBean = dataBean;
        this.position = position;
    }

    public SquareBean.DataBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(SquareBean.DataBean dataBean) {
        this.dataBean = dataBean;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "SquareBeanChangeEvent{" +
                "dataBean=" + dataBean +
                ", position=" + position +
                '}';
    }
}
