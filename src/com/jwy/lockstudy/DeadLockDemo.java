package com.jwy.lockstudy;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: jwy
 * @Date: 2019/12/09/16:40
 * @Description: 死锁代码演示及诊断
 */

class Mylock implements Runnable{
    private String lockA;
    private String lockB;

    public Mylock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 正持有锁"+lockA+"想抢夺锁"+lockB);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 正持有锁"+lockB+"想抢夺锁"+lockA);
        }
        }
    }
}
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "locaA";
        String lockB = "lockB";
        new Thread(new Mylock(lockA,lockB),"ThreadAAA").start();
        new Thread(new Mylock(lockB,lockA),"ThreadBBB").start();
        /**
         * window诊断：jps -l 查询进程号
         *              jstack 进程号 诊断死锁
         */
    }
}
