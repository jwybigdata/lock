package com.jwy.lockstudy;

import sun.misc.Unsafe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示ReentrantReadWriteLock写时独占，读时共享
 */
class MyCache{//资料类
    private volatile Map<String,Object> map = new HashMap<>();

    private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    //写操作
    public void put(String key,String value) throws InterruptedException {

        rwlock.writeLock().lock();
                try{
                    System.out.println(Thread.currentThread().getName()+"\t 正在写入的key为"+key);
                    //Thread.sleep(300);
                    map.put(key,value);
                    System.out.println(Thread.currentThread().getName()+"\t 写入完成");
                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    rwlock.writeLock().unlock();
                }
    }

    //读操作
    public void  get(String key){
        rwlock.readLock().lock();
                try{
                    System.out.println(Thread.currentThread().getName()+"\t 正在读取");
                    Thread.sleep(300);
                    Object result = map.get(key);
                    System.out.println(Thread.currentThread().getName() + "\t 读取结果：" + result);
                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    rwlock.readLock().unlock();
                }

    }

}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache cache = new MyCache();

        //多线程并发写
        for (int i = 1; i <=5; i++) {
           int temp = i;
            new Thread(() ->{
                try {
                    cache.put(temp+"",temp+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
        //多线程并发读
        for (int i = 1; i <=5; i++) {
            int temp = i;
            new Thread(() ->{
                try {
                    cache.get(temp+"");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }


    }
}
