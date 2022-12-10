package cn.tpkf.bot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 10 下午 01:20
 */
@Getter
@AllArgsConstructor
public enum AddressEnums {

    /**
     * Device address if pin is connected to GND
     */
    GND(0x48),

    /**
     * Device address if pin is connected to VDD
     */
    VDD(0x49),

    /**
     * Device address if pin is connected to SDA
     */
    SDA(0x4A),

    /**
     * Device address if pin is connected to SCL
     */
    SCL(0x4B);

    /**
     * device address on I2C
     */
    private final int value;
}
