package cn.tpkf.bot.core.function;

import cn.tpkf.bot.core.commend.Commend;
import cn.tpkf.bot.enums.FunctionStateEnums;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
public class AsyncFunction extends AbstractFunction {

    private final Executor asyncExecutor;

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    private final Long commendExecuteTime;

    private final TimeUnit timeUnit;

    public AsyncFunction(String name, Executor asyncExecutor, Commend setUpCommend, Commend stopCommend, List<Commend> commends, Long commendExecuteTime, TimeUnit timeUnit) {
        super(name, setUpCommend, stopCommend, commends);
        this.asyncExecutor = asyncExecutor;
        this.commendExecuteTime = commendExecuteTime;
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
    public void reRun() {
        if (state == FunctionStateEnums.STOP) {
            log.warn("{} Function is not stop!", name);
            return;
        }
        condition.signal();
    }
}
