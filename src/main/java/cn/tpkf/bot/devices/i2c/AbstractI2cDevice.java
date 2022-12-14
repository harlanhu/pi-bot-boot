package cn.tpkf.bot.devices.i2c;

import cn.tpkf.bot.devices.AbstractDevice;
import cn.tpkf.bot.enums.AddressEnums;
import com.pi4j.context.Context;
import com.pi4j.io.i2c.I2C;
import com.pi4j.io.i2c.I2CConfig;
import lombok.Getter;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 10 下午 06:47
 */
@Getter
public abstract class AbstractI2cDevice extends AbstractDevice implements I2cDevice {

    protected final I2C i2c;

    protected final int i2cBus;

    protected final AddressEnums address;

    protected AbstractI2cDevice(Context pi4jContext, String name, int i2cBus, AddressEnums address) {
        super(pi4jContext, name);
        this.i2c = pi4jContext.create(buildI2cConfig(pi4jContext, i2cBus, address.getValue(), name));
        this.i2cBus = i2cBus;
        this.address = address;
    }

    /**
     * Build a I2C Configuration to use the AD convertor
     *
     * @param pi4j    PI4J Context
     * @param i2cBus  I2C Bus address
     * @param address I2C Device address
     * @return I2C configuration
     */
    protected I2CConfig buildI2cConfig(Context pi4j, int i2cBus, int address, String name) {
        return I2C.newConfigBuilder(pi4j)
                .id("I2C-" + address + "@" + i2cBus)
                .name(name)
                .bus(i2cBus)
                .device(address)
                .provider("linuxfs-i2c")
                .build();
    }

    @Override
    public boolean isOpen() {
        return getI2c().isOpen();
    }
}
