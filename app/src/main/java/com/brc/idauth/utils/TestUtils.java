package com.brc.idauth.utils;

import com.brc.idauth.bean.Project;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-08-12
       Time     :  19:44
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class TestUtils {


    public static boolean isForTest = true;

    public static Project getTestProject() {
        Project project = new Project("成都长岛1", "018787e1fd460c04a18520aaf2a0ca68");
        return project;
    }
}
