package cn.tpkf.bot.core.function;

import cn.tpkf.bot.core.manager.DeviceManager;
import cn.tpkf.bot.enums.FunctionStateEnums;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

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

    protected final ReentrantLock lock = new ReentrantLock();

    protected final Condition condition = lock.newCondition();

    protected final DeviceManager deviceManager;

    protected final Executor asyncExecutor;

    protected AbstractFunction(String name, DeviceManager deviceManager, Executor asyncExecutor) {
        this.name = name;
        this.deviceManager = deviceManager;
        this.asyncExecutor = asyncExecutor;
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
        state = FunctionStateEnums.RUNNING;
        asyncExecutor.execute(() -> {
            while (true) {
                if (state == FunctionStateEnums.STOP) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        log.error("{}功能运行错误: {} ---- {}", name, e.getMessage(), e.getCause());
                        Thread.currentThread().interrupt();
                    }
                }
                doRun();
            }
        });
    }

    @Override
    public void stop() {
        if (state != FunctionStateEnums.RUNNING) {
            log.warn("{} Function is not running!", name);
            return;
        }
        doStop();
        state = FunctionStateEnums.STOP;
    }

    @Override
    public void reRun() {
        if (state == FunctionStateEnums.STOP) {
            log.warn("{} Function is not stop!", name);
            return;
        }
        state = FunctionStateEnums.RUNNING;
        condition.signal();
    }

    @Override
    public boolean isRunning() {
        return state == FunctionStateEnums.RUNNING;
    }
}
