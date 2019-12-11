package com.jwy.reference;

import java.lang.ref.WeakReference;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: jwy
 * @Date: 2019/12/11/9:34
 * @Description:弱引用
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> w = new WeakReference<>(o1);

        System.out.println(o1);
        System.out.println(w.get());

        o1 = null;
        System.gc();
        System.out.println("========================");
        System.out.println(o1);
        System.out.println(w.get());
    }
}
