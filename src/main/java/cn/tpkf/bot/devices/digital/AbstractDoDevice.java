package cn.tpkf.bot.devices.digital;

import cn.tpkf.bot.devices.AbstractDelayDevice;
import com.pi4j.io.gpio.digital.DigitalOutput;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 10 下午 05:32
 */
public abstract class AbstractDoDevice extends AbstractDelayDevice implements DigitalOutputDevice {

    /**
     * 获取 DigitalOutput
     * @return DigitalOutput
     */
    public abstract DigitalOutput getDigitalOutput();

    /**
     * 设置高电平
     */
    public void on() {
        getDigitalOutput().on();
    }

    public void on(long duration) {
        ReentrantLock lock = getLock();
        lock.lock();
        try {
            on();
            delay(duration);
            off();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 设置低电平
     */
    public void off() {
        getDigitalOutput().off();
    }

    /**
     * 反转电平
     * @return true 高电平, false 低电平
     */
     public boolean toggle() {
         getDigitalOutput().toggle();
         return getDigitalOutput().isOff();
     }

    /**
     * 设置电平
     * @param state true 高电平, false 低电平
     */
     public void setState(boolean state) {
         getDigitalOutput().setState(state);
     }

    @Override
    public boolean isLocked() {
        return getLock().isLocked();
    }
}
