package com.daolion.base.utils;

import java.util.Random;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/8/17
       Time     :  11:33
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class RandomUtils{
    
    public static int getRandomRange(int fromValue, int toValue){
        return new Random().nextInt(toValue) % (toValue - fromValue + 1) + fromValue;
    }
}
