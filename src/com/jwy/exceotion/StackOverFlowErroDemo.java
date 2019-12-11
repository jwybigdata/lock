package com.jwy.exceotion;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: jwy
 * @Date: 2019/12/11/11:18
 * @Description:
 */
public class StackOverFlowErroDemo {
    public static void sofe(){
        sofe();
    }

    public static void main(String[] args) {
        sofe();//异常描述：Exception in thread "main" java.lang.StackOverflowError
    }
}
