package com.jwy.jucstudy;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <=6; i++) {
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName()+"\t 下班了");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("所有人已离开公司 \t"+Thread.currentThread().getName()+"开始消毒");
    }
}
