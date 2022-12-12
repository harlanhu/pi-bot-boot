package cn.tpkf.bot.devices.digital.output;

import cn.tpkf.bot.enums.PinEnums;
import cn.tpkf.bot.utils.GpioConfigUtils;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
import lombok.Getter;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 蜂鸣器
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 09 下午 11:11
 */
@Getter
public class Buzzer extends AbstractDoDevice {

    private final Context context;

    private final DigitalOutput digitalOutput;

    private final String name;

    private final PinEnums pin;

    private final ReentrantLock lock;

    public Buzzer(Context pi4jContext, PinEnums pin, String name) {
        this.name = name;
        this.context = pi4jContext;
        this.pin = pin;
        this.lock = new ReentrantLock();
        digitalOutput = pi4jContext.create(GpioConfigUtils.buildDigitalOutputConfig(pi4jContext, pin, name, DigitalState.LOW, DigitalState.LOW));
    }

    public void setState(boolean state) {
        digitalOutput.setState(state);
    }


    public void on() {
        digitalOutput.setState(false);
    }

    public void off() {
        digitalOutput.setState(true);
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
                cycle --;
            }
        } finally {
            lock.unlock();
        }
    }

    public void cycle() {
        cycle(200, 3, 500, 1);
    }

    @Override
    public void setUp() {
        loop();
    }
}
