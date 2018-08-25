package com.tangbaobao.lambda;

import com.tangbaobao.pojo.Apple;
import com.tangbaobao.pojo.Dish;
import com.tangbaobao.pojo.PojoList;
import com.tangbaobao.pojo.Type;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * 测试lambda表达式
 *
 * @author 唐学俊
 * @create 2018/08/20
 **/

public class LambdaTest {
    @Test
    public void fun1() {
        Function<Integer,Apple> function = Apple::new;
        BiFunction<Integer,String,Apple> appleBiFunction = Apple::new;
        String integerStr = "123";
        Function<String,Integer> functionStr = Integer::parseInt;
        Integer apply = functionStr.apply(integerStr);
        System.out.println(apply);
    }

    @Test
    public void fun2() {
        List<Apple> appleList = PojoList.appleList;
        appleList.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));
        System.out.println(appleList);
    }

    @Test
    public void fun3() {
        List<Apple> appleList = PojoList.appleList;
        Predicate<Apple> predicate = x -> x.getWeight() > 120;

        Predicate<Apple> negate = predicate.negate();
        Predicate<Apple> and = negate.and(x -> "aaa".equalsIgnoreCase(x.getColor()));
        List<Apple> filter = this.filter(appleList, and);
        System.out.println(filter);
    }



    @Test
    public void fun4() {
        Function<Integer, Integer> fn1 = x -> x + 1;
        Function<Integer, Integer> fn2 = x -> x * 2;
        Function<Integer, Integer> fn3 = fn1.andThen(fn2);
        Integer apply = fn3.apply(3);
        System.out.println(apply);
        System.out.println(fn1.compose(fn2).apply(3));
    }

    @Test
    public void fun6() {
        List<Dish> menu = PojoList.menu;
        List<String> collect = menu.stream()
                .filter(x -> x.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());
        System.out.println(collect);
    }

    @Test
    public void fun7() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> collect = list.stream()
                .filter(x -> x % 2 == 0)
                .distinct()
                .skip(5)
                .collect(toList());
        System.out.println(collect);
    }

    /**
     * 利用流来筛选前两个荤菜
     */
    @Test
    public  void fun8() {
        List<Dish> menu = PojoList.menu;
        List<Dish> collect = menu.stream()
                .filter(x -> x.getType().equals(Type.MEAT))
                .limit(2)
                .collect(toList());
        System.out.println(collect);
    }

    @Test
    public void fun9() {
        List<String> list = Arrays.asList("java8", "in", "action");
        List<Integer> collect = list.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(collect);


    }
    
    @Test
    
    public void fun10() {
        String[] worlds = {"hello", "world"};
        List<String> collect = Arrays.stream(worlds)
                .map(x -> x.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(collect);
    }
    @Test
    /**
     * f(x) = x+10
     */
    public void fun5() {
        double integrate = integrate((double x) -> x + 10, 3, 10);
        System.out.println(integrate);
    }

    public double integrate(DoubleFunction<Double> f, int x, int y) {
        return (f.apply(x) + f.apply(y)) * (y - x) / 2;
    }

    public  <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> newList = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                newList.add(t);
            }
        }
        return newList;
    }



}
