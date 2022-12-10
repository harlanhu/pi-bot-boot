package cn.tpkf.bot.entity.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 10 下午 10:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultEntity<T> {

    private Integer code;

    private T data;

    private String message;
}
