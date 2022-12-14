package cn.tpkf.bot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/12/14
 */
@Getter
@AllArgsConstructor
public enum ResultCodeEnums {

    /**
     * 未知异常
     */
    UNKNOWN_ERROR("-1", "unknown error"),

    /**
     * 成功
     */
    SUCCESS("0001", "success"),

    /**
     * 失败
     */
    FAIL("0000", "fail"),

    /**
     * 参数校验错误
     */
    ARGUMENT_NOT_VALID("0002", "参数校验错误");

    private final String code;

    private final String message;
}
