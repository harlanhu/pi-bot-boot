package cn.tpkf.bot.devices.i2c;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 10 下午 06:47
 */
public abstract class AbstractI2cDevice implements I2cDevice {

    @Override
    public int getI2cBus() {
        return getI2c().getBus();
    }

    @Override
    public void close() {
        getI2c().close();
    }

    @Override
    public boolean isOpen() {
        return getI2c().isOpen();
    }
}
