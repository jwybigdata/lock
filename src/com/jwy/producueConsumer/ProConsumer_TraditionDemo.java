package com.jwy.producueConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: jwy
 * @Date: 2019/12/05/15:13
 * @Description:一个初始值为0,俩个线程对其交替操作，一个加1，一个减1
 * 1 线程       操作（方法） 资源类
 * 2 判断       干活         通知
 * 3 防止虚假唤醒（避免用if判断，使用while）
 */
//资源类
    class Source{
        private int num = 0;
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        //加1方法
    public void add1() throws Exception {
        lock.lock();
                try{
                    //判断
                    while (num!=0){
                        condition.await();
                    }
                    num++;
                    System.out.println(Thread.currentThread().getName()+"\t"+num);
                    condition.signalAll();
                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }

    }


    public void del1() throws Exception {
        lock.lock();
        try{
            //判断
            while (num==0){
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName()+"\t"+num);
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}

public class ProConsumer_TraditionDemo {
    public static void main(String[] args) {
        Source s = new Source();
        for (int i = 1; i <=5 ; i++) {
            new Thread(() ->{
                try {
                    s.add1();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },"AAA").start();
        }

        for (int i = 1; i <=5 ; i++) {
            new Thread(() ->{
                try {
                    s.del1();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },"BBB").start();
        }
    }
}
