package com.brc.idauth.ui.verify;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-11
       Time     :  15:53
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class ItemBean {
    private String title;
    private String value;

    public ItemBean() {
    }

    public ItemBean(String title) {
        this.title = title;
    }

    public ItemBean(String title, String value) {
        this.title = title;
        this.value = value;
    }


    public String getItem() {
        return title;
    }

    public void setItem(String item) {
        this.title = item;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}

