package cn.tpkf.bot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 18 下午 05:22
 */
@Getter
@AllArgsConstructor
public enum FunctionStateEnums {

    /**
     * 初始化
     */
    INITIALIZE(0),

    /**
     * 运行
     */
    RUNNING(1),

    /**
     * 停止
     */
    STOP(2);

    private final Integer value;

}
