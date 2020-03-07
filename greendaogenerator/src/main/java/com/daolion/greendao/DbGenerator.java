package com.daolion.greendao;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Schema;

public class DbGenerator {

    public static void main(String[] args) {

        //设置导入到数据库的路径地址 默认为app目录下的 db  package 下。
        //TODO  完成项目后要设置这个路径
        Schema schema = new Schema(1, "com.brc.idauth.db");
        schema.enableKeepSectionsByDefault();
        addTables(schema);
        try {
            new DaoGenerator().generateAll(schema, "./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void addTables(Schema schema) {

    }

}
