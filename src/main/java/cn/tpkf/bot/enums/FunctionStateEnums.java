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
     * 初始设置
     */
    SETUP(1),

    /**
     * 运行
     */
    RUNNING(2),

    /**
     * 停止
     */
    STOP(3),

    /**
     * 销毁
     */
    DESTROY(4);

    private final Integer value;

}
