package cn.tpkf.bot.devices.i2c.adda;

import cn.tpkf.bot.devices.i2c.AbstractI2cDevice;
import cn.tpkf.bot.enums.AddressEnums;
import cn.tpkf.bot.utils.GpioConfigUtils;
import com.pi4j.context.Context;
import com.pi4j.io.i2c.I2C;
import lombok.Getter;

/**
 * PCF8591
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 10 上午 12:24
 */
@Getter
public class Pcf8591 extends AbstractI2cDevice {

    private final double maxVoltage;

    private final byte rA0;

    private final byte rA1;

    private final byte rA2;

    private final byte rA3;

    public Pcf8591(Context pi4jContext, String name) {
        this(pi4jContext, name, 1, AddressEnums.GND);
    }

    public Pcf8591(Context pi4jContext, String name, int i2cBus) {
        this(pi4jContext, name, i2cBus, AddressEnums.GND);
    }

    public Pcf8591(Context pi4jContext, String name, int i2cBus, AddressEnums address) {
        this(pi4jContext, name, i2cBus, address, 5);
    }

    public Pcf8591(Context pi4jContext, String name, int i2cBus, AddressEnums address, double maxVoltage) {
        this(pi4jContext, name, i2cBus, address, maxVoltage, (byte) 0x45, (byte) 0x46, (byte) 0x47, (byte) 0x44);
    }

    public Pcf8591(Context pi4jContext, String name, int i2cBus, AddressEnums address, double maxVoltage, byte rA0, byte rA1, byte rA2, byte rA3) {
        super(pi4jContext, name, i2cBus, address);
        this.maxVoltage = maxVoltage;
        this.rA0 = rA0;
        this.rA1 = rA1;
        this.rA2 = rA2;
        this.rA3 = rA3;
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
