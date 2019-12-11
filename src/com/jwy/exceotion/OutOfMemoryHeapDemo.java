package com.jwy.exceotion;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: jwy
 * @Date: 2019/12/11/11:20
 * @Description:
 */
public class OutOfMemoryHeapDemo {
    public static void main(String[] args) {
        String a = "hhe";
        while(true){
            a += a+new Random().nextInt(112222)+new Random().nextInt(2555555);
            a.intern();
        }
        // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    }
}
