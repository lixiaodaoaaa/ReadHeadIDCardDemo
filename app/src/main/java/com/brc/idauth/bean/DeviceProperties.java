package com.brc.idauth.bean;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-14
       Time     :  09:57
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class DeviceProperties {

    private String iotId;

    public DeviceProperties(String iotId) {
        this.iotId = iotId;
    }

    public String getIotId() {
        return iotId;
    }

    public void setIotId(String iotId) {
        this.iotId = iotId;
    }

    @Override
    public String toString() {
        return "DeviceProperties{" +
                "iotId='" + iotId + '\'' +
                '}';
    }
}
