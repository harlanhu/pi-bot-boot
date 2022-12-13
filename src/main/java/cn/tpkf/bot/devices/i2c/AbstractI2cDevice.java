package cn.tpkf.bot.devices.i2c;

import cn.tpkf.bot.devices.AbstractDevice;
import cn.tpkf.bot.enums.AddressEnums;
import cn.tpkf.bot.utils.GpioConfigUtils;
import com.pi4j.context.Context;
import com.pi4j.io.i2c.I2C;
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
        this.i2c = pi4jContext.create(GpioConfigUtils.buildI2cConfig(pi4jContext, i2cBus, address.getValue(), name));
        this.i2cBus = i2cBus;
        this.address = address;
    }

    @Override
    public boolean isOpen() {
        return getI2c().isOpen();
    }
}
