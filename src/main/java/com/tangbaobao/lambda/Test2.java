package com.tangbaobao.lambda;

/**
 * @author 唐学俊
 * @create 2018/09/09
 **/

public class Test2 {
    public static void main(String[] args) {
        Interface face = new InterfaceImpl();
        face.fun1();
        Interface.fun2();
    }
}
