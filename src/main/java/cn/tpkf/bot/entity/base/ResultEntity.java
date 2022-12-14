package cn.tpkf.bot.entity.base;

import cn.tpkf.bot.enums.ResultCodeEnums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 10 下午 10:00
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ResultEntity<T> {

    private String code;

    private T data;

    private String message;

    public static <T> ResultEntity<T> getInstance(String code, T data, String message) {
        return new ResultEntity<>(code, data, message);
    }

    public static <T> ResultEntity<T> getInstance(ResultCodeEnums resultCode, T data) {
        return new ResultEntity<>(resultCode.getCode(), data, resultCode.getMessage());
    }

    public static <T> ResultEntity<T> getInstance(ResultCodeEnums resultCode) {
        return getInstance(resultCode, null);
    }

    public static <T> ResultEntity<T> success(T data) {
        return getInstance(ResultCodeEnums.SUCCESS, data);
    }

    public static <T> ResultEntity<T> success() {
        return getInstance(ResultCodeEnums.SUCCESS);
    }

    public static <T> ResultEntity<T> fail(ResultCodeEnums resultCode, String message) {
        return getInstance(resultCode.getCode(), null, message);
    }

    public static <T> ResultEntity<T> fail(T data) {
        return getInstance(ResultCodeEnums.FAIL, data);
    }

    public static <T> ResultEntity<T> fail() {
        return getInstance(ResultCodeEnums.FAIL);
    }
}
