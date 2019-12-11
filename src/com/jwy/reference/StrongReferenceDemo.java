package com.jwy.reference;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: jwy
 * @Date: 2019/12/10/21:56
 * @Description:
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object object1 = new Object();
        Object object2 = object1;
        object1 = null;
        System.gc();
        System.out.println(object2);
    }
}
