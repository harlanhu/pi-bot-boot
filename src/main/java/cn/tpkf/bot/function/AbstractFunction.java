package cn.tpkf.bot.function;

import cn.tpkf.bot.enums.FunctionStateEnums;
import lombok.Getter;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 18 下午 05:32
 */
@Getter
public abstract class AbstractFunction implements Function {

    private final String name;

    private FunctionStateEnums state;

    protected AbstractFunction(String name) {
        this.name = name;
        state = FunctionStateEnums.INITIALIZE;
    }


}
