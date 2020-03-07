package com.daolion.base.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
       Author   :  lixiaodaoaaa
       Date     :  2018/7/24
       Time     :  18:05
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
    
   用与注解 Fragment的 Annotation
 */
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface BsFragmentAnnotation {
    
    
    int fragmentLayoutId() default -1;
    
    int recyclerHeaderTextId() default -1;
    
    boolean isRecyclerItemIsLinerlayout() default true;
    
    //是不是需要EventBus
    boolean isNeedEventBus() default false;
    
    boolean recyclerHeaderVisible() default true;
    
    
    //是否显示RecyclerDiver分割线 默认显示
    boolean isShowRecyclerDiver() default true;
    
}
