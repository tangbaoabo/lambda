package com.tangbaobao.lambda;

import com.tangbaobao.pojo.Dish;
import com.tangbaobao.pojo.PojoList;
import com.tangbaobao.pojo.Trader;
import com.tangbaobao.pojo.Transaction;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

/**
 * @author 唐学俊
 * @create 2018/08/24
 **/

public class StreamTest {

    private Trader raoul = new Trader("Raoul", "Cambridge");
    private Trader mario = new Trader("Mario", "Milan");
    private Trader alan = new Trader("Alan", "Cambridge");
    private Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    /**
     * 找出2011年的所有交易,并按照交易额排序(从低到高)
     */
    @Test
    public void fun1() {
        List<Transaction> collect = transactions.stream()
                .filter(x -> x.getYear() == 2011)
                .sorted(comparing(Transaction::getValue).reversed())
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 交易员工都在那些城市工作过
     */
    @Test
    public void fun2() {
        List<String> collect = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 查找所有来自剑桥的交易员,并按姓名排序
     */
    @Test
    public void fun3() {
        List<Trader> collect = transactions.stream()
                .map(Transaction::getTrader)
                .filter(x -> "Cambridge".equals(x.getCity()))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 返回所有交易员的姓名字符串,按字母顺序排序
     */
    @Test
    public void fun4() {
        String reduce = transactions.stream()
                .map(x -> x.getTrader().getName())
                .sorted(String::compareTo)
                .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(reduce);
    }
    @Test
    public void fun4_1() {
        String reduce = transactions.stream()
                .map(x -> x.getTrader().getName())
                .sorted(String::compareTo)
                .collect(Collectors.joining());
        System.out.println(reduce);
    }
    /**
     * 有没有交易员是在美国工作的?
     */
    @Test
    public void fun5() {
        boolean b = transactions.stream()
                .anyMatch(x -> "Milan".equals(x.getTrader().getCity()));
        System.out.println(b);
    }

    /**
     * 打印生活在剑桥的交易员的所有交易额
     */
    @Test
    public void fun6() {
        transactions.stream()
                .filter(x -> "Cambridge".equals(x.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    /**
     * 在所有交易中,最高的交易额是多少
     */
    @Test
    public void fun7() {
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo);
        System.out.println(max.get());

        Optional<Integer> reduce = transactions.stream()
                .map(Transaction::getValue)
                .reduce((t1,t2)->t1>t2?t1:t2);

        Optional<Transaction> min = transactions.stream()
                .min(comparing(Transaction::getValue));
    }

    @Test
    public void fun8() {
        List<Dish> menu = PojoList.menu;
        Integer reduce = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println(reduce);
    }

    @Test
    public void fun9() {
        List<Dish> menu = PojoList.menu;
        int sum = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(sum);
    }
    @Test
    public void fun10() {
        List<Dish> menu = PojoList.menu;
        IntStream intStream = menu.stream()
                .mapToInt(Dish::getCalories);
        Stream<Integer> boxed = intStream.boxed();
        Integer reduce = boxed.reduce(0, Integer::sum);
        System.out.println(reduce);
    }

    @Test
    public void fun11() {
        List<Dish> menu = PojoList.menu;
        OptionalInt max = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();

        System.out.println(max.getAsInt());
    }


    @Test
    public void fun12() {
        IntStream intStream = IntStream.rangeClosed(1, 100);
        intStream.boxed().collect(Collectors.toList());
    }

    @Test
    public void fun13() {
        Stream<String> hello = Stream.of("hello", "world", "java8", "in", "action");
        hello.map(String::toUpperCase).forEach(System.out::println);

        Stream<Object> empty = Stream.empty();
        IntStream stream = Arrays.stream(new int[]{1, 23, 4});
        int sum = stream.sum();
        System.out.println(sum);
    }

    @Test
    public void fun14() {
        int sum = IntStream.rangeClosed(1, 100).sum();
        System.out.println(sum);
        Stream<Integer> boxed = IntStream.rangeClosed(1, 100).boxed();
        Integer reduce = boxed.reduce(0, Integer::min);
        System.out.println(reduce);
    }

    @Test
    public void fun15() {
        Stream<Integer> limit = Stream.iterate(1, x -> x + 1).limit(100);
        limit.forEach(System.out::println);
        //System.out.println(limit.reduce(0,Integer::sum));
    }

    /**
     * 斐波那契梳数列
     */
    @Test
    public void fun16() {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .map(t->t[0])
                .forEach(System.out::println);
    }

    @Test
    public void fun17() {

    }
}
