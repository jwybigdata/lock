package com.jwy.reference;

import java.lang.ref.SoftReference;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: jwy
 * @Date: 2019/12/10/22:06
 * @Description:软应用演示
 */
public class SoftReferenceDemo {
    public static void soft_memory_enough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);

        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();
        System.out.println("======================");

        System.out.println(o1);
        System.out.println(softReference.get());
    }
    public static void soft_memory_not_enough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);

        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;

        try {
            byte[] b = new byte[30*1024*1024];
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }

    public static void main(String[] args) {
            //soft_memory_enough();

        soft_memory_not_enough();
    }
}
