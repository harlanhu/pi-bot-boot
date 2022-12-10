package cn.tpkf.bot.devices.i2c;

import cn.tpkf.bot.devices.Device;
import cn.tpkf.bot.enums.AddressEnums;
import com.pi4j.io.i2c.I2C;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 10 下午 06:41
 */
public interface I2cDevice extends Device {

    /**
     * 获取i2c
     * @return i2c
     */
    I2C getI2c();

    /**
     * 获取设备地址
     * @return 设备地址
     */
    AddressEnums getAddress();

    /**
     * 获取总线
     * @return i2c总线
     */
    int getI2cBus();

    /**
     * 关闭设备
     */
    void close();

    /**
     * 是否开启
     * @return boolean
     */
    boolean isOpen();
}
