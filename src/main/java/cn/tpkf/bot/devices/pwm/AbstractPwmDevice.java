package cn.tpkf.bot.devices.pwm;

import cn.tpkf.bot.devices.AbstractDelayDevice;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 10 下午 05:58
 */
public abstract class AbstractPwmDevice extends AbstractDelayDevice implements PwmDevice {

    public void on(int frequency) {
        on(frequency, 1000);
    }

    public void on(int frequency, long duration) {
        ReentrantLock lock = getLock();
        if (frequency <= 0 || duration <= 0) {
            off();
            return;
        }
        lock.lock();
        try {
            getPwm().on(50, frequency);
            delay(duration);
            off();
        } finally {
            lock.unlock();
        }
    }

    public void off() {
        getPwm().off();
    }

    @Override
    public boolean isLocked() {
        return getLock().isLocked();
    }
}
