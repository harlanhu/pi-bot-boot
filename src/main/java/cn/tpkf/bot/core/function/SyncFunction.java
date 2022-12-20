package cn.tpkf.bot.core.function;

import cn.tpkf.bot.core.commend.Commend;

import java.util.List;

/**
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/12/20
 */
public class SyncFunction extends AbstractFunction {

    protected SyncFunction(String name, Commend setUpCommend, Commend stopCommend, List<Commend> commends) {
        super(name, setUpCommend, stopCommend, commends);
    }

    protected SyncFunction(String name, Commend setUpCommend, Commend stopCommend, Commend... commends) {
        super(name, setUpCommend, stopCommend, commends);
    }

    protected SyncFunction(String name, Commend setUpCommend, Commend stopCommend, Commend commend) {
        super(name, setUpCommend, stopCommend, commend);
    }

    @Override
    public void run() {
        for (Commend commend : commends) {
            commend.execute();
            currentCommendIndex.incrementAndGet();
        }
        currentCommendIndex.set(0);
    }

    @Override
    public void reRun() {
        currentCommendIndex.set(0);
        run();
    }
}
