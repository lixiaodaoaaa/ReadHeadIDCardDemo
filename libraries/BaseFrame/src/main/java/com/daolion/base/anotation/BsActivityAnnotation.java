package com.daolion.base.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
       Author   :  lixiaodaoaaa
       Date     :  2018/7/24
       Time     :  18:05
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
    
    用与注解 Activity的 Annotation
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BsActivityAnnotation {


    /**
     * int layoutId  用于设置 Acitivity的资源文件 setcontentView( layoutId);
     * 默认不设置LayoutId;
     */
    int  layoutId() default  -1;


    /**
     * 得到toolbarTitle的String文件
     *
     * @return
     */
    int toolbarTitleStringID() default -1;

    /**
     * 得到Toolbar menu的LayouIDResource
     *
     * @return
     */
    int toolbarMenuLayouId() default -1;

    /**
     * 是否显示 返回键（Toolbar Navigation back);
     *
     * @return
     */
    boolean showBackNavigation() default false;

    boolean isFullScreen() default true;


    /**
     * 是否显示 返回键（Toolbar Navigation back);
     *
     * @return
     */
    boolean isNeedEventBus() default false;

}
