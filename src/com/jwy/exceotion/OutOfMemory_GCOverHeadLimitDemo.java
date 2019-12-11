package com.jwy.exceotion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: jwy
 * @Date: 2019/12/11/14:51
 * @Description:java.lang.OutOfMemoryError:GC overhead limit exceeded
 * JVM参数配置演示
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * GC回收时间过长会抛出OutOfMemoryError。过长的定义是，超过98%的时间用力啊做GC并且回收了不到2%d的堆内存
 * 连多次GC都只回收了不到2%的极端情况下才会抛出。假如不抛出GC overhead limit 错误会发生什么呢
 * 那就是GC清理这么点内存很快就会再次填满，迫使GC再次执行，这样就形成恶行循环，
 * cpu的使用率一直是100%,而GC却没有任何成果
 */
public class OutOfMemory_GCOverHeadLimitDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        try {
            while (true){
                list.add(String.valueOf(i++).intern());
            }
        }catch (Throwable e){
            System.out.println("i:="+i);
            e.printStackTrace();
            throw e;
        }
    }
}
