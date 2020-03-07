package com.daolion.greendao.bean;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/9/3
       Time     :  00:20
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class TableBean {
    
    private String tableName;
    private String mainKey;
    
    public TableBean(String tableName, String mainKey){
        this.tableName = tableName;
        this.mainKey = mainKey;
    }
    
    public String getTableName(){
        return tableName;
    }
    
    public void setTableName(String tableName){
        this.tableName = tableName;
    }
    
    public String getMainKey(){
        return mainKey;
    }
    
    public void setMainKey(String mainKey){
        this.mainKey = mainKey;
    }
    
    @Override
    public String toString(){
        return "TableBean{" + "tableName='" + tableName + '\'' + ", mainKey='" + mainKey + '\'' + '}';
    }
}
