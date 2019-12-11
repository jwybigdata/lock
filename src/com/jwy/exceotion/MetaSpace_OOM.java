package com.jwy.exceotion;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: jwy
 * @Date: 2019/12/11/18:57
 * @Description:
 * JVM参数
 * -XX：MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 * java 8及以后的版本是哟个Metaspace来替代使用
 * Metaspace是方法区在HotSpot中的实现，它与持久代最大的区别在于：Metaspace并不在虚拟机内存而是在本地内存
 也即是java8中，class metadata(the virtual machines internal presentation of java class),被存储在叫做
 Metaspace的native memory
 永久代（java8 后被原空间metaspace取代）存放了一下信息：
 虚拟机加载类的的信息
 常量池
 静态变量
 即时编译后的代码
 模拟Metaspace空间溢出，不断生成类往元空间灌，类占据的空间总是会超过metaspace指定的空间大小
 */
public class MetaSpace_OOM {
    static class OOMTest {}

    public static void main(String[] args) {
        int i = 0;
        try {
            while (true){
                i++;
                /**
                 * Enhancer en = new Enhancer();
                 * en.setSuperclass(OOMTest.class);
                 * en.setUseCache(false);
                 * en.setCallback(new MethodInterCeptor(){
                 *     @Override
                 *     public Object intercept(Object o,Method.Object[] objects,MethodProxy methodProxy) throws Throwable
                 *     {
                 *         return methodProxy.invokeSuper(o,args);
                 *     }
                 * })
                 * en.create();
                 */

            }
        }catch (Throwable e){
            System.out.println("多少次发生异常:"+i);
            e.printStackTrace();
        }
    }
}
