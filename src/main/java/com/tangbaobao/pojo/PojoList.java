package com.tangbaobao.pojo;

import java.util.Arrays;
import java.util.List;

/**
 * @author 唐学俊
 * @create 2018/08/20
 **/

public class PojoList {
    public static List<Apple> appleList = Arrays.asList(
            new Apple(80, "green"),
            new Apple(155, "green"),
            new Apple(120, "red"),
            new Apple(120, "aaa")
    );
    public static final List<Dish> menu =
            Arrays.asList(new Dish("pork", false, 800, Type.MEAT),
                    new Dish("beef", false, 700, Type.MEAT),
                    new Dish("chicken", false, 400, Type.MEAT),
                    new Dish("french fries", true, 530, Type.OTHER),
                    new Dish("rice", true, 350, Type.OTHER),
                    new Dish("season fruit", true, 120, Type.OTHER),
                    new Dish("pizza", true, 550, Type.OTHER),
                    new Dish("prawns", false, 400, Type.FISH),
                    new Dish("salmon", false, 450, Type.FISH));
}
