package cn.tpkf.bot.core.function;

import cn.tpkf.bot.core.commend.Commend;
import cn.tpkf.bot.enums.FunctionStateEnums;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/12/20
 */
@Slf4j
@Data
public abstract class AbstractFunction implements Function {

    protected final String name;

    protected final Commend setUpCommend;

    protected final Commend stopCommend;

    protected final List<Commend> commends;

    protected FunctionStateEnums state;

    protected AtomicInteger currentCommendIndex = new AtomicInteger(0);

    protected AbstractFunction(String name, Commend setUpCommend, Commend stopCommend, List<Commend> commends) {
        this.name = name;
        this.commends = commends;
        this.setUpCommend = setUpCommend;
        this.stopCommend = stopCommend;
        setUp();
    }

    protected AbstractFunction(String name, Commend setUpCommend, Commend stopCommend, Commend... commends) {
        this(name, setUpCommend, stopCommend, Arrays.asList(commends));
    }

    protected AbstractFunction(String name, Commend setUpCommend, Commend stopCommend, Commend commend) {
        this(name, setUpCommend, stopCommend, Collections.singletonList(commend));
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
    public boolean isRunning() {
        return state == FunctionStateEnums.RUNNING;
    }

    @Override
    public Integer getCurrentCommendIndex() {
        return currentCommendIndex.get();
    }

    @Override
    public Commend getCommend(int index) {
        return commends.get(index);
    }

    @Override
    public Commend getCuurentCommend() {
        return commends.get(currentCommendIndex.get());
    }

    @Override
    public Commend getNextCommend() {
        return commends.get(currentCommendIndex.get());
    }

    @Override
    public Commend executeNextCommend() {
        Commend commend = commends.get(currentCommendIndex.get());
        commend.execute();
        currentCommendIndex.incrementAndGet();
        return commend;
    }
}
