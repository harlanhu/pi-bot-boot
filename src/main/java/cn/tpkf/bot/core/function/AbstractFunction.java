package cn.tpkf.bot.core.function;

import cn.tpkf.bot.core.manager.DeviceManager;
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

    protected final String name;

    protected FunctionStateEnums state;

    protected final DeviceManager deviceManager;

    protected AbstractFunction(String name, DeviceManager deviceManager) {
        this.name = name;
        this.deviceManager = deviceManager;
    }

    /**
     * 初始化
     */
    protected abstract void doSetUp();

    /**
     * 运行
     */
    protected abstract void doRun();

    /**
     * 停止
     */
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
        doRun();
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
