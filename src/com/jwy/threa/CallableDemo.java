package com.jwy.threa;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: jwy
 * @Date: 2019/12/06/11:07
 * @Description:使用callable接口创建线程
 */
class MyThread implements Callable{

    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"\t come in ~~");
        return 1;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws Exception {
        MyThread t = new MyThread();
        FutureTask futureTask = new FutureTask(t);
        new Thread(futureTask,"AA").start();
        while (!futureTask.isDone()){
            System.out.println("工作ing。。。");
        }
        System.out.println("result="+futureTask.get());
    }
}
