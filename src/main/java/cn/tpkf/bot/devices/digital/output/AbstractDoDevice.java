package cn.tpkf.bot.devices.digital.output;

import cn.tpkf.bot.devices.AbstractDelayDevice;
import cn.tpkf.bot.enums.PinEnums;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfig;
import com.pi4j.io.gpio.digital.DigitalState;
import lombok.Getter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 10 下午 05:32
 */
@Getter
public abstract class AbstractDoDevice extends AbstractDelayDevice implements DigitalOutputDevice {

    protected final DigitalOutput digitalOutput;

    protected final DigitalState onState;

    protected final DigitalState offState;

    protected final PinEnums pin;

    protected final ReentrantLock lock;

    protected AbstractDoDevice(Context pi4jContext, PinEnums pin, String name, DigitalState onState, DigitalState offState, DigitalState initial, DigitalState shutdown) {
        super(pi4jContext, name);
        this.digitalOutput = pi4jContext.create(buildDigitalOutputConfig(pi4jContext, pin, name, initial, shutdown));
        this.pin = pin;
        this.onState = onState;
        this.offState = offState;
        this.lock = new ReentrantLock();
    }

    protected AbstractDoDevice(Context pi4jContext, PinEnums pin, String name, DigitalState initial, DigitalState shutdown) {
        super(pi4jContext, name);
        this.digitalOutput = pi4jContext.create(buildDigitalOutputConfig(pi4jContext, pin, name, initial, shutdown));
        this.pin = pin;
        this.lock = new ReentrantLock();
        this.onState = DigitalState.HIGH;
        this.offState = DigitalState.LOW;
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

    @Override
    public DigitalState getState() {
        return digitalOutput.state();
    }

    public void on() {
        digitalOutput.setState(onState.getValue().intValue());
    }

    public void on(long duration, TimeUnit timeUnit) {
        lock.lock();
        try {
            on();
            delay(duration, timeUnit);
            off();
        } finally {
            lock.unlock();
        }
    }

    public void off() {
        digitalOutput.setState(offState.getValue().intValue());
    }

    public boolean toggle() {
        getDigitalOutput().toggle();
        return getDigitalOutput().isOff();
    }

    public void setState(DigitalState state) {
        digitalOutput.setState(state.getValue().intValue());
    }

    public void loop(long duration, int loop, TimeUnit timeUnit) {
        if (loop <= 0 || duration <= 0) {
            return;
        }
        lock.lock();
        try {
            while (loop >= 1) {
                on(duration, timeUnit);
                loop--;
            }
        } finally {
            lock.unlock();
        }
    }

    public void loop() {
        loop(200, 1, TimeUnit.MILLISECONDS);
    }

    public void cycle(long duration, int loop, long interval, int cycle, TimeUnit timeUnit) {
        if (duration == 0 || loop == 0 || cycle == 0 || interval < 0) {
            return;
        }
        lock.lock();
        try {
            while (cycle >= 1) {
                loop(duration, loop, timeUnit);
                delay(interval, timeUnit);
                cycle--;
            }
        } finally {
            lock.unlock();
        }
    }

    public void cycle() {
        cycle(200, 3, 500, 1, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean isLocked() {
        return getLock().isLocked();
    }
}
