package com.brc.idauth.bean;

import android.arch.persistence.room.Ignore;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-11
       Time     :  17:13
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class IdCardBean {

    private String name;
    private String gender;
    private String people;//民族;
    private String from;//
    private String address;
    private String idNumber;
    private String department;
    private String endDate;
    private String birthDay;//生日


    public IdCardBean() {
    }


    @Ignore
    public IdCardBean(String name, String gender, String people, String from, String address, String idNumber, String department, String endDate) {
        this.name = name;
        this.gender = gender;
        this.people = people;
        this.from = from;
        this.address = address;
        this.idNumber = idNumber;
        this.department = department;
        this.endDate = endDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }


    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "IdCardBean{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", people='" + people + '\'' +
                ", from='" + from + '\'' +
                ", address='" + address + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", department='" + department + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}


