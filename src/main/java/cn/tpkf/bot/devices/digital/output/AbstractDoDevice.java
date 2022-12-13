package cn.tpkf.bot.devices.digital.output;

import cn.tpkf.bot.devices.AbstractDelayDevice;
import cn.tpkf.bot.enums.PinEnums;
import cn.tpkf.bot.utils.GpioConfigUtils;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
import lombok.Getter;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 10 下午 05:32
 */
@Getter
public abstract class AbstractDoDevice extends AbstractDelayDevice implements DigitalOutputDevice {

    protected final Context context;

    protected final DigitalOutput digitalOutput;


    protected final String name;

    protected final PinEnums pin;

    protected final ReentrantLock lock;

    protected AbstractDoDevice(Context pi4jContext, PinEnums pin, String name, DigitalState initial, DigitalState shutdown) {
        this.context = pi4jContext;
        this.digitalOutput = pi4jContext.create(GpioConfigUtils.buildDigitalOutputConfig(pi4jContext, pin, name, initial, shutdown));
        this.name = name;
        this.pin = pin;
        this.lock = new ReentrantLock();
    }

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

    public void off() {
        getDigitalOutput().off();
    }

     public boolean toggle() {
         getDigitalOutput().toggle();
         return getDigitalOutput().isOff();
     }

     public void setState(boolean state) {
         getDigitalOutput().setState(state);
     }

    @Override
    public boolean isLocked() {
        return getLock().isLocked();
    }
}
