package cn.tpkf.bot.utils;

import cn.tpkf.bot.enums.PinEnums;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfig;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.i2c.I2C;
import com.pi4j.io.i2c.I2CConfig;
import com.pi4j.io.pwm.Pwm;
import com.pi4j.io.pwm.PwmConfig;
import com.pi4j.io.pwm.PwmType;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 09 下午 10:25
 */
public class GpioConfigUtils {

    private GpioConfigUtils() {
        //DO NOTHING
    }

    public static PwmConfig buildPwmConfig(Context pi4j, PinEnums pin, String name, PwmType pwmType, int initial, int shutdown) {
        return Pwm.newConfigBuilder(pi4j)
                .id("BCM-" + pin)
                .name(name)
                .address(pin.getVale())
                .pwmType(pwmType)
                .initial(initial)
                .shutdown(shutdown)
                .provider("pigpio-pwm")
                .build();
    }

    public static DigitalOutputConfig buildDigitalOutputConfig(Context pi4j, PinEnums pin, String name, DigitalState initial, DigitalState shutdown) {
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
     * Build a I2C Configuration to use the AD convertor
     * @param pi4j   PI4J Context
     * @param i2cBus    I2C Bus address
     * @param address I2C Device address
     * @return I2C configuration
     */
    public static I2CConfig buildI2cConfig(Context pi4j, int i2cBus, int address, String name) {
        return I2C.newConfigBuilder(pi4j)
                .id("I2C-" + address + "@" + i2cBus)
                .name(name)
                .bus(i2cBus)
                .device(address)
                .provider("linuxfs-i2c")
                .build();
    }
}
