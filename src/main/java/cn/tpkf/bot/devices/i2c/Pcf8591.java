package cn.tpkf.bot.devices.i2c;

import cn.tpkf.bot.enums.AddressEnums;
import cn.tpkf.bot.utils.GpioConfigUtils;
import com.pi4j.context.Context;
import com.pi4j.io.i2c.I2C;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * PCF8591
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 10 上午 12:24
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class Pcf8591 extends AbstractI2cDevice {

    private final Context context;

    private final String name;

    private final int i2cBus;

    private final I2C i2c;

    private AddressEnums address = AddressEnums.GND;

    private double maxVoltage = 5;

    private byte rA0 = 0x45;

    private byte rA1 = 0x46;

    private byte rA2 = 0x47;

    private byte rA3 = 0x44;

    public Pcf8591(Context pi4jContext, String name, int i2cBus) {
        this.context = pi4jContext;
        this.name = name;
        this.i2cBus = i2cBus;
        this.i2c = pi4jContext.create(GpioConfigUtils.buildI2cConfig(pi4jContext, i2cBus, address.getValue(), name));
    }

    public Pcf8591(Context pi4jContext, String name, AddressEnums address, int i2cBus) {
        this.context = pi4jContext;
        this.name = name;
        this.address = address;
        this.i2cBus = i2cBus;
        this.i2c = pi4jContext.create(GpioConfigUtils.buildI2cConfig(pi4jContext, i2cBus, address.getValue(), name));
    }

    public Pcf8591(Context pi4jContext, String name, AddressEnums address, int i2cBus, double maxVoltage) {
        this.context = pi4jContext;
        this.name = name;
        this.maxVoltage = maxVoltage;
        this.address = address;
        this.i2cBus = i2cBus;
        this.i2c = pi4jContext.create(GpioConfigUtils.buildI2cConfig(pi4jContext, i2cBus, address.getValue(), name));
    }

    public Pcf8591(Context pi4jContext, String name, AddressEnums address, double maxVoltage, int i2cBus, byte rA0, byte rA1, byte rA2, byte rA3) {
        this.context = pi4jContext;
        this.name = name;
        this.address = address;
        this.i2cBus = i2cBus;
        this.maxVoltage = maxVoltage;
        this.rA0 = rA0;
        this.rA1 = rA1;
        this.rA2 = rA2;
        this.rA3 = rA3;
        this.i2c = pi4jContext.create(GpioConfigUtils.buildI2cConfig(pi4jContext, i2cBus, address.getValue(), name));
    }

    public double readAin0() {
        return readAin(rA0);
    }

    public double readAin1() {
        return readAin(rA1);
    }

    public double readAin2() {
        return readAin(rA2);
    }

    public double readAin3() {
        return readAin(rA3);
    }

    double readAin(byte raN) {
        int value = i2c.readRegister(raN);
        return  (maxVoltage / 255) * value;
    }

    @Override
    public void setUp() {
        //Do Nothing
    }
}
