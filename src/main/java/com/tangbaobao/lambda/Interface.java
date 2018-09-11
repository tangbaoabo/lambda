package com.tangbaobao.lambda;

/**
 * 接口测试
 *
 * @author 唐学俊
 * @create 2018/09/09
 **/
public interface Interface {
    default void fun1(){
        System.out.println("你好");
    }
    static void fun2() {
        System.out.println("我是静态方法");
    }
}
