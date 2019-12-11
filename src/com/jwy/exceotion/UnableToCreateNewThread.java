package com.jwy.exceotion;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: jwy
 * @Date: 2019/12/11/16:49
 * @Description:
 * 高并发请求服务器时，经常出现如下异常：java.lang.OutOfMemoryError:unable to create new native thread
 * 准确的讲该native thread 异常对应的平台有关
 * 导致原因：
 * 1    你的用用创建了太多线程了，一个应用线程创建多个线程，超过系统承载极限
 * 2    你的服务器并不允许你的应用程序创建这么多线程，linux系统默认允许单线程创建线程数为1024个，
 *      你的应用创建超过这个数量，就会报java.OutOfMemoryError:unable to create new native Thread
 * 解决方案
 * 1 想办法降低你应用程序创建线程的数量，分析应用是否真的需要这么多的线程，如果不是，改代码将线程数降到最低
 * 2  对于有的应用，确实需要创建多线程，远超过linux系统的默认1024个线程的线程，可以通过修改linux服务器配置，
 * 扩大linux默认限制
 * 补充linux命令
 * ulimit -u 查询非root 用户单个应用最大默认线程数
 * vim /etc/security/limits.d/90-nproc.conf 该文件配置了单个应用最大默认线程数
 */
public class UnableToCreateNewThread {
    public static void main(String[] args) {
        for (int i=0;;i++){
            System.out.println("i="+i);
            new Thread(() ->{
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
