package com.jwy.producueConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: jwy
 * @Date: 2019/12/06/9:35
 * @Description:
 */
class Cake{//资源类
    //true时，进行生产消费蛋糕，false停止生产消费
    private volatile Boolean flag = true;
    private AtomicInteger atomic = new AtomicInteger();
    private BlockingQueue<String> queue = null;

    //构造方法
    public Cake(BlockingQueue queue){
        this.queue=queue;
        System.out.println(queue.getClass().getName());
    }

    //生产者
    public void myPro() throws Exception {
        String data = null;
        Boolean value =null;
        while (flag){
            data = atomic.incrementAndGet()+"";
            value = queue.offer(data, 2L, TimeUnit.SECONDS);
            if (value){
                System.out.println(Thread.currentThread().getName()+"\t 生产蛋糕"+data+"成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"\t"+"等待超时,生产失败");
            }
            TimeUnit.SECONDS.sleep(1L);
        }

        System.out.println(Thread.currentThread().getName()+"\t 老板命令停止生产");
    }

    //消费者
    public  void myCon() throws Exception {
        String result=null;
        while (flag){
             result = queue.poll(2L, TimeUnit.SECONDS);
            if (result==null || result.equals("")){
                flag = false;
                System.out.println(Thread.currentThread().getName()+"\t 超时退出");
                return;
            }
             System.out.println(Thread.currentThread().getName()+"\t 消费蛋糕"+result+"成功");
        }
    }

    public  void  stop() throws Exception{
        this.flag = false;
    }
}
public class ProConsumer_BlockingQueueDemo {
    public static void main(String[] args) throws Exception {
        Cake cake = new Cake(new ArrayBlockingQueue(10));
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+"\t 消费者线程启动");
            try {
                cake.myPro();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Pro").start();
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+"\t 生产者线程启动");
            try {
                cake.myCon();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Con").start();
        TimeUnit.SECONDS.sleep(5);
        cake.stop();
    }


}
