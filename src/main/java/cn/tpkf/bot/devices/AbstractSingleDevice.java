package cn.tpkf.bot.devices;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/12/13
 */
public abstract class AbstractSingleDevice implements SingleDevice {

    protected final ReentrantLock lock;

    protected AbstractSingleDevice() {
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
