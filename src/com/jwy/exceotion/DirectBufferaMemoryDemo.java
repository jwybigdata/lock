package com.jwy.exceotion;

import java.nio.ByteBuffer;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: jwy
 * @Date: 2019/12/11/15:39
 * @Description:
 * JVM配置参数
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * 故障现象
 *Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
 * 导致原因：
 * 写NIO程序经常使用ByteBuffer来读取或者写数据，这是一种基于通道（Channel）与缓冲区（Buffer）的I/O方式,
 * 它可以使用Nativ函数库直接分配推外内存，然后通过一个存储再java堆和native堆中来回复制数据。
 * ByteBUffer.allocate(capability) 第一种方式是非陪JVM内存，属于GC管辖范围，由于需要拷贝所以速度相对比较慢
 * ByteBuffer.allooateDirect(capablity) 第一种方式是属于OS本地内存，不属于GC管辖范围，由于不需要内存拷贝所以速度相对很快。
 * 但是如果不断分配内存，推内存很少使用，那么JVM就不需要执行GC，DirectByteBuffer对象们就不会被回收，
 * 这时候堆内存充足，但本地内存可能已经使用光了，再次尝试分配本地内存就会出现OutOfMeMory,那程序就直接崩溃了。
 */
public class DirectBufferaMemoryDemo {
    public static void main(String[] args) {
        System.out.println("MaxDirectMemory="+(sun.misc.VM.maxDirectMemory()/(double)1024/1024)+"M");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(60 * 1024 * 1024);

    }
}
