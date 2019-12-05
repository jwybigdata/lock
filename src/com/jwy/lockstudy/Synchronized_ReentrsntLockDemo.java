package com.jwy.lockstudy;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: jwy
 * @Date: 2019/12/05/16:03
 * @Description:多线程之间的顺序调用，实现A>B>C三个三个线程启动，要求如下：
 * AA打印5次，BB打印10次，CC打印15次
 * 紧接着
 * AA打印5次，BB打印20次，Cc打印15次
 * ------来10轮
 */
class ShareResource{
    private int number = 1;//1为A，2为B，三为C
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    public void print5(){
        lock.lock();
        try{
            while (number!=1){
                c1.await();
            }
            for (int i = 1; i <=5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number=2;
            c2.signal();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try{
            while (number!=2){
                c2.await();
            }
            for (int i = 1; i <=10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number=3;
            c3.signal();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try{
            while (number!=3){
                c3.await();
            }
            for (int i = 1; i <=15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number=1;
            c1.signal();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class Synchronized_ReentrsntLockDemo {
    public static void main(String[] args) {
        ShareResource sh = new ShareResource();
        for (int i = 1; i <=10 ; i++) {
            new Thread(() ->{sh.print5();},"A").start();
        }

        for (int i = 1; i <=10 ; i++) {
            new Thread(() ->{sh.print10();},"B").start();
        }

        for (int i = 1; i <=10 ; i++) {
            new Thread(() ->{sh.print15();},"C").start();
        }
    }
}
