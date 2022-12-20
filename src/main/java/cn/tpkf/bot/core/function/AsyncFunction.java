package cn.tpkf.bot.core.function;

import cn.tpkf.bot.core.commend.Commend;
import cn.tpkf.bot.enums.FunctionStateEnums;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 18 下午 05:32
 */
@Slf4j
@Data
public class AsyncFunction implements Function {

    private final String name;

    private final Commend setUpCommend;

    private final Commend stopCommend;

    private final List<Commend> commends;

    private FunctionStateEnums state;

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    private Integer currentCommendIndex = 0;

    private final Long commendExecuteTime;

    private final TimeUnit timeUnit;

    private final Executor asyncExecutor;

    protected AsyncFunction(String name, Executor asyncExecutor, Commend setUpCommend, Commend stopCommend, List<Commend> commends, Long commendExecuteTime, TimeUnit timeUnit) {
        this.name = name;
        this.asyncExecutor = asyncExecutor;
        this.commends = commends;
        this.commendExecuteTime = commendExecuteTime;
        this.setUpCommend = setUpCommend;
        this.stopCommend = stopCommend;
        this.timeUnit = timeUnit;
        setUp();
    }

    public AsyncFunction(String name, Executor asyncExecutor, Commend setUpCommend, Commend stopCommend,List<Commend> commends) {
        this(name, asyncExecutor, setUpCommend, stopCommend, commends, 15L, TimeUnit.SECONDS);
    }

    public AsyncFunction(String name, Executor asyncExecutor, Commend setUpCommend, Commend stopCommend, Commend... commends) {
        this(name, asyncExecutor, setUpCommend, stopCommend, Arrays.asList(commends));
    }

    public AsyncFunction(String name, Executor asyncExecutor, Commend setUpCommend, Commend stopCommend, Commend commend) {
        this(name, asyncExecutor, setUpCommend, stopCommend, Collections.singletonList(commend));
    }

    @Override
    public void setUp() {
        if (state == FunctionStateEnums.RUNNING) {
            log.warn("{} Function is running!", name);
            return;
        }
        if (Objects.nonNull(setUpCommend)) {
            setUpCommend.execute();
        }
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
                LocalDateTime endTime = LocalDateTime.now().plus(commendExecuteTime, timeUnit.toChronoUnit());
                while (endTime.isAfter(LocalDateTime.now())) {
                    getCuurentCommend().execute();
                }
                if (currentCommendIndex == commends.size()) {
                    currentCommendIndex = 0;
                } else {
                    currentCommendIndex ++;
                }
            }
        });
    }

    @Override
    public void stop() {
        if (state != FunctionStateEnums.RUNNING) {
            log.warn("{} Function is not running!", name);
            return;
        }
        if (Objects.nonNull(stopCommend)) {
            stopCommend.execute();
        }
        state = FunctionStateEnums.STOP;
    }

    @Override
    public void reRun() {
        if (state == FunctionStateEnums.STOP) {
            log.warn("{} Function is not stop!", name);
            return;
        }
        condition.signal();
    }

    @Override
    public boolean isRunning() {
        return state == FunctionStateEnums.RUNNING;
    }

    @Override
    public Commend getCommend(int index) {
        return commends.get(index);
    }

    @Override
    public Commend getCuurentCommend() {
        return commends.get(currentCommendIndex);
    }
}
