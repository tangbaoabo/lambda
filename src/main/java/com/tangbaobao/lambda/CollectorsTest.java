package com.tangbaobao.lambda;

import com.tangbaobao.pojo.*;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * CollectorsTest
 *
 * @author 唐学俊
 * @create 2018/08/26
 **/

public class CollectorsTest {
    @Test
    public void fun1() {
        List<Transaction> transactions = PojoList.transactions;
        Map<Trader, List<Transaction>> collect = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getTrader));
        System.out.println(collect);
    }

    @Test
    public void fun2() {
        List<Dish> menu = PojoList.menu;
        Long collect = menu.stream().collect(Collectors.counting());
        Long collect1 = menu.stream().count();
        System.out.println(collect);
    }

    /**
     * 查找流中的最大值和最小值
     */
    @Test
    public void fun3() {
        List<Dish> menu = PojoList.menu;
        Optional<Dish> max = menu.stream()
                .max(Comparator.comparing(Dish::getCalories));
        List<Dish> menu2 = PojoList.menu;
        Optional<Dish> collect = menu2.stream()
                .collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories)));
        System.out.println(max);
        System.out.println(collect);
    }

    /**
     * 汇总菜的热量
     */
    @Test
    public void fun4() {
        List<Dish> menu = PojoList.menu;
        int sum = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(sum);

        List<Dish> menu1 = PojoList.menu;


        Integer collect = menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(collect);
    }

    @Test
    public void fun5() {
        List<Dish> menu = PojoList.menu;
        Double collect = menu.stream()
                .collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println(collect);
    }

    @Test
    public void fun6() {
        List<Dish> menu = PojoList.menu;
        IntSummaryStatistics collect = menu.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(collect);
    }

    @Test
    public void fun7() {
        List<Dish> menu = PojoList.menu;
        String collect = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(","));
        System.out.println(collect);
    }

    @Test
    public void fun8() {
        List<Dish> menu = PojoList.menu;
        Integer collect = menu.stream()
                .collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
        System.out.println(collect);
        List<Dish> menu1 = PojoList.menu;
        String collect1 = menu1.stream()
                .collect(Collectors.reducing("", Dish::getName, (a, b) -> a + b+","));
        System.out.println(collect1);
    }

    @Test
    public void fun9() {
        List<Dish> menu = PojoList.menu;
        Optional<Dish> max = menu.stream()
                .max(Comparator.comparing(Dish::getCalories));
        System.out.println(max);
    }
    @Test
    public void fun10() {
        List<Dish> menu = PojoList.menu;
        Map<Type, Long> collect = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        System.out.println(collect);
    }
    @Test
    public void fun11() {
        List<Dish> menu = PojoList.menu;
        Map<Type, Dish> collect = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println(collect);
    }

    @Test
    public void fun12() {
        List<Dish> menu = PojoList.menu;
        Map<Boolean, List<Dish>> collect = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian));
        System.out.println(collect);
    }
    @Test
    public void fun13() {
        List<Dish> menu = PojoList.menu;
        Map<Boolean, Map<Type, List<Dish>>> collect = menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.groupingBy(Dish::getType)));
        System.out.println(collect);
    }


    @Test
    public void fun14() {
        Map<Boolean, List<Integer>> collect = IntStream.rangeClosed(2, 100)
                .boxed()
                .collect(Collectors.partitioningBy(this::isPrim));
        System.out.println(collect);
    }

    public boolean isPrim(int contidate) {
        return IntStream.range(2,contidate)
                .noneMatch(x->contidate%x ==0);
    }
}
