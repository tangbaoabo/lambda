package com.tangbaobao.pojo;

import lombok.*;

/**
 * @author 唐学俊
 * @create 2018/08/20
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Apple {
    @NonNull
    private int weight;
    private String color;

}
