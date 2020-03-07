package com.brc.idauth.ui.searchsquare;

import com.daolion.net.response.SquareBean;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-15
       Time     :  11:50
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛

    选中的SquareDataBean 包含案场ID和案场项目名称
 */
public class SelectSquareEvent {

    private SquareBean.DataBean dataBean;

    public SelectSquareEvent(SquareBean.DataBean dataBean) {
        this.dataBean = dataBean;
    }

    public SquareBean.DataBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(SquareBean.DataBean dataBean) {
        this.dataBean = dataBean;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
