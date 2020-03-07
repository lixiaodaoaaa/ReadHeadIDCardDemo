package com.daolion.net.response;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-13
       Time     :  20:06
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class PictureInfo {

    private String url;


    public PictureInfo(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PictureInfo{" +
                "url='" + url + '\'' +
                '}';
    }
}

