package com.daolion.greendao.factory;

import com.daolion.greendao.TableConstant;
import com.daolion.greendao.bean.TableBean;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/9/3
       Time     :  00:17
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class TableBeanBuilder {
    
    public static TableBean getUserTableBean(){
        return new TableBean(TableConstant.TABLE_USER, TableConstant.MAIN_KEY_USER);
    }
    
    public static TableBean getSiteTableBean(){
        return new TableBean(TableConstant.TABLE_SITE, TableConstant.MAIN_KEY_SITE);
    }
    
    public static TableBean getCheckPointTableBean(){
        return new TableBean(TableConstant.TABLE_CHECKPOINT, TableConstant.MAIN_KEY_CHECKPOINT);
    }
    
    
    public static TableBean getWorkOrderTableBean(){
        return new TableBean(TableConstant.TABLE_WORKORDER, TableConstant.MAIN_KEY_WORKORDER);
    }
    
    public static TableBean getHandoverTableBean(){
        return new TableBean(TableConstant.TABLE_HANDOVER, TableConstant.MAIN_KEY_HANDOVER);
    }
    
    public static TableBean getLineTableBean(){
        return new TableBean(TableConstant.TABLE_LINE, TableConstant.MAIN_KEY_LINE);
    }
    
    public static TableBean getPhotoInfoBean(){
        return new TableBean(TableConstant.TABLE_PHOTOINFO, TableConstant.MAIN_KEY_PHOTOINFO);
    }
    
    
}
