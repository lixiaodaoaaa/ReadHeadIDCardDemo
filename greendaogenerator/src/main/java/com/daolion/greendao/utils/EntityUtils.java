package com.daolion.greendao.utils;

import com.daolion.greendao.bean.TableBean;

import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/8/31
       Time     :  11:53
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class EntityUtils {
    
    
    public static Entity createTableByMainKey(Schema schema, String tableName, String mainKey){
        Entity entity = schema.addEntity(tableName);
        entity.addLongProperty(mainKey).primaryKey();
        return entity;
    }
    
    public static Entity createTableByTableBean(Schema schema, TableBean tableBean){
        Entity entity = schema.addEntity(tableBean.getTableName());
        entity.addStringProperty(tableBean.getMainKey()).primaryKey().notNull();
        return entity;
    }
    
  
    
}
