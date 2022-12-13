package cn.tpkf.bot.devices;

import com.pi4j.context.Context;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/12/13
 */
public abstract class AbstractSingleDevice extends AbstractDevice implements SingleDevice {

    protected final ReentrantLock lock;

    protected AbstractSingleDevice(Context pi4jContext, String name) {
        super(pi4jContext, name);
        this.lock = new ReentrantLock();
    }

    /**
     * 是否上锁
     * @return boolean
     */
    public boolean isLocked() {
        return this.lock.isLocked();
    }
}
