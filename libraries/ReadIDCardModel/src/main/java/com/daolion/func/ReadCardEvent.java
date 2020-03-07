package com.daolion.func;

import android.graphics.Bitmap;

import com.huashi.otg.sdk.HSIDCardInfo;


/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-11
       Time     :  08:57
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class ReadCardEvent {




    private HSIDCardInfo cardInfo;




    public ReadCardEvent() {
    }

    public ReadCardEvent(HSIDCardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

    public ReadCardEvent(HSIDCardInfo cardInfo, Bitmap bitmap) {
        this.cardInfo = cardInfo;
    }

    public HSIDCardInfo getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(HSIDCardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }
}
