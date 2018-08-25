package com.tangbaobao.lambda;

import org.junit.Test;

import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * 斐波那契额数列测试
 *
 * @author 唐学俊
 * @create 2018/08/25
 **/

public class FibonacciTest {
    @Test
    public void normalTest() {
        long l = System.currentTimeMillis();
        int fibonacci = this.fibonacci(1000000);
        System.out.println(fibonacci);
        System.out.println(System.currentTimeMillis() - l);
    }

    public int fibonacci(int n) {
        int temp;
        int before = 1;
        int after = 1;
        if (n == 1 || n == 2) {
            return 1;
        } else {
            for (int i = 2; i < n; i++) {
                temp = after + before;
                before = after;
                after = temp;
            }
        }
        return after;
    }

    @Test
    public void lambdaTest() {
        long l = System.currentTimeMillis();
        int n = 1000000;
        //1.构建一个流
        Stream<Integer> integerStream = Stream.iterate(new int[]{1, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(n)
                .map(t -> t[0]);
        List<Integer> collect = integerStream.collect(Collectors.toList());
        System.out.println(collect.get(n - 1));
        System.out.println(System.currentTimeMillis() - l);
    }


    @Test
    public void lambdaTest2() {
        long l = System.currentTimeMillis();
        IntSupplier intSupplier = new IntSupplier() {
            private int previous = 1;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };

        int[] ints = IntStream.generate(intSupplier).limit(10).toArray();
        System.out.println(ints[1000000]);
        System.out.println(System.currentTimeMillis() - l);
    }
}
