package cn.tpkf.bot.exception;

import cn.tpkf.bot.enums.ResultCodeEnums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统异常
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/12/15
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AppException extends RuntimeException {

    private final String code;

    private final String message;

    public AppException(ResultCodeEnums resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

}
