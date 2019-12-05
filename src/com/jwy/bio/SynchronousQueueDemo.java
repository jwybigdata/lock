package com.jwy.bio;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列，只存储一个元素
 * 消费一个才能再存一个
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {

        BlockingQueue bq = new SynchronousQueue();
        new Thread(() ->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t put1");
                bq.put(1);

                System.out.println(Thread.currentThread().getName()+"\t put2");
                bq.put(2);

                System.out.println(Thread.currentThread().getName()+"\t put3");
                bq.put(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AAA").start();
        new Thread(() ->{

            try {
                try { TimeUnit.SECONDS.sleep(5); } catch (Exception e) { e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+"\t" +bq.take());

                try { TimeUnit.SECONDS.sleep(5); } catch (Exception e) { e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+"\t" +bq.take());

                try { TimeUnit.SECONDS.sleep(5); } catch (Exception e) { e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+"\t" +bq.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        },"BBB").start();

    }
}
