package com.jwy.threa;

import org.omg.SendingContext.RunTime;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: jwy
 * @Date: 2019/12/06/12:08
 * @Description:线程池的创建,工作中不用Executors创建线程
 */
public class ThreaPoolDemo {
    public static void main(String[] args) {
        //固定线程数池
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //单线程池
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //n线程池
        //ExecutorService threadPool = Executors.newCachedThreadPool();

        ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,
                1l,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());



        try {
            for (int i = 1; i <=10 ; i++) {
                threadPool.execute(()->{

                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");

                });
                //暂时一会儿线程
                //Thread.sleep(200);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }

    }
}
