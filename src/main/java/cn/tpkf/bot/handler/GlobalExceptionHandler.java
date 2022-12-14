package cn.tpkf.bot.handler;

import cn.tpkf.bot.entity.base.ResultEntity;
import cn.tpkf.bot.enums.ResultCodeEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import java.util.List;

/**
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/12/14
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        ObjectError objectError = allErrors.get(0);
        return ResultEntity.fail(ResultCodeEnums.ARGUMENT_NOT_VALID, objectError.getDefaultMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResultEntity<Object> handleSqIntegrityConstraintViolationException(DuplicateKeyException e) {
        return ResultEntity.fail(ResultCodeEnums.FAIL, "该数据已被绑定，请勿重复绑定!");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResultEntity<Object> handleRuntimeException(RuntimeException e) {
        return ResultEntity.fail(ResultCodeEnums.FAIL, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResultEntity<Object> handleException(Exception e) {
        log.error("全局异常处理: {} ---- {}", e.getLocalizedMessage(), e.getStackTrace());
        return ResultEntity.fail(ResultCodeEnums.UNKNOWN_ERROR);
    }
}
