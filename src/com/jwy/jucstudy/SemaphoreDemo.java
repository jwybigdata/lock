package com.jwy.jucstudy;

import java.util.concurrent.Semaphore;

/**
 * Semaphore:抢车位，主要作用：一个用于多个共享资源的互斥使用，一个用于并发线程数的控制
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//3个车位
        for (int i = 1; i <=6 ; i++) {
            new Thread(() ->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t 抢到车位");
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName()+"\t停车三秒后离开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
