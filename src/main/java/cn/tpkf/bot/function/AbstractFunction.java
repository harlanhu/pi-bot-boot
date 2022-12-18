package cn.tpkf.bot.function;

import cn.tpkf.bot.enums.FunctionStateEnums;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 18 下午 05:32
 */
@Slf4j
@Getter
public abstract class AbstractFunction implements Function {

    private final String name;

    private FunctionStateEnums state;

    protected AbstractFunction(String name) {
        this.name = name;
        state = FunctionStateEnums.INITIALIZE;
    }

    protected abstract void doSetUp();

    protected abstract void doStart();

    protected abstract void doStop();

    @Override
    public void setUp() {
        if (state == FunctionStateEnums.RUNNING) {
            log.warn("{} Function is running!", name);
            return;
        }
        doSetUp();
        state = FunctionStateEnums.SETUP;
    }

    @Override
    public void run() {
        if (state == FunctionStateEnums.RUNNING) {
            log.warn("{} Function is running!", name);
            return;
        }
        doStart();
        state = FunctionStateEnums.RUNNING;
    }

    @Override
    public void stop() {
        if (state != FunctionStateEnums.RUNNING) {
            log.warn("{} Function is not running!", name);
        }
        doStop();
        state = FunctionStateEnums.STOP;
    }

    @Override
    public boolean isRunning() {
        return state == FunctionStateEnums.RUNNING;
    }
}
