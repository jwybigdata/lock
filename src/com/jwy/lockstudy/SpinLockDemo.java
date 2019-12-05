package com.jwy.lockstudy;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 */
public class SpinLockDemo {
    //原子引用线程
    AtomicReference<Thread> atomic = new AtomicReference<>();

    public void mylock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in ····");
        while (!atomic.compareAndSet(null, thread)) {

        }
    }

    public void myunlock() {
        Thread thread = Thread.currentThread();
        atomic.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t invoke myunlock");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() ->{
            spinLockDemo.mylock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myunlock();
        },"AAA").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() ->{
            spinLockDemo.mylock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myunlock();
        },"BBB").start();
    }


}
