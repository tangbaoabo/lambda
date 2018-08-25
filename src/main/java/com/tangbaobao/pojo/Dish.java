package com.tangbaobao.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author 唐学俊
 * @create 2018/08/22
 **/
@Getter
@ToString
@AllArgsConstructor
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private  final Type type;
}
