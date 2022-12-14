package cn.tpkf.bot.devices.digital.output;

import cn.tpkf.bot.devices.AbstractDelayDevice;
import cn.tpkf.bot.enums.PinEnums;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfig;
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

    protected final DigitalOutput digitalOutput;

    protected final PinEnums pin;

    protected final ReentrantLock lock;

    protected AbstractDoDevice(Context pi4jContext, PinEnums pin, String name, DigitalState initial, DigitalState shutdown) {
        super(pi4jContext, name);
        this.digitalOutput = pi4jContext.create(buildDigitalOutputConfig(pi4jContext, pin, name, initial, shutdown));
        this.pin = pin;
        this.lock = new ReentrantLock();
    }

    protected DigitalOutputConfig buildDigitalOutputConfig(Context pi4j, PinEnums pin, String name, DigitalState initial, DigitalState shutdown) {
        return DigitalOutput.newConfigBuilder(pi4j)
                .id("BCM-" + pin)
                .name(name)
                .initial(initial)
                .shutdown(shutdown)
                .address(pin.getVale())
                .provider("pigpio-digital-output")
                .build();
    }

    /**
     * 设置高电平
     */
    public void on() {
        getDigitalOutput().on();
    }

    public void on(long duration) {
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

    public void loop(long duration, int loop) {
        if (loop <= 0 || duration <= 0) {
            return;
        }
        lock.lock();
        try {
            while (loop >= 1) {
                on(duration);
                loop--;
            }
        } finally {
            lock.unlock();
        }
    }

    public void loop() {
        loop(200, 1);
    }

    public void cycle(long duration, int loop, long interval, int cycle) {
        if (duration == 0 || loop == 0 || cycle == 0 || interval < 0) {
            return;
        }
        lock.lock();
        try {
            while (cycle >= 1) {
                loop(duration, loop);
                delay(interval);
                cycle--;
            }
        } finally {
            lock.unlock();
        }
    }

    public void cycle() {
        cycle(200, 3, 500, 1);
    }

    @Override
    public boolean isLocked() {
        return getLock().isLocked();
    }
}
