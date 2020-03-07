package com.brc.idauth.utils;

import com.brc.idauth.bean.IdCardBean;
import com.huashi.otg.sdk.HSIDCardInfo;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-11
       Time     :  17:13
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class IdCardConvertUtils {


    public static IdCardBean converToIdCardBean(HSIDCardInfo cardInfo) {
        IdCardBean idCardBean = new IdCardBean();
        if (null != cardInfo) {
            String name = cardInfo.getPeopleName();
            String gender = cardInfo.getSex();
            String people = cardInfo.getPeople();
            String birthDay = DateFormatterUtils.toBirthDay(cardInfo.getBirthDay());
            String address = cardInfo.getAddr();
            String cardNumber = cardInfo.getIDCard();
            String department = cardInfo.getDepartment();
            String endDate = cardInfo.getEndDate();
            idCardBean.setName(name);
            idCardBean.setGender(gender);
            idCardBean.setPeople(people);
            idCardBean.setBirthDay(birthDay);
            idCardBean.setAddress(address);
            idCardBean.setIdNumber(cardNumber);
            idCardBean.setDepartment(department);
            idCardBean.setEndDate(endDate);
            return idCardBean;
        }
        return null;
    }
}
