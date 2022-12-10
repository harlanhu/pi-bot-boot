package cn.tpkf.bot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Data rate
 * These bits control the data rate setting
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 10 下午 01:29
 */
@Getter
@AllArgsConstructor
public enum DrEnums {

    /**
     * 8 sampling per second
     */
    SPS_8(0b0000_0000_0000_0000, 8),

    /**
     * 16 sampling per second
     */
    SPS_16(0b0000_0000_0010_0000, 16),

    /**
     * 32 sampling per second
     */
    SPS_32(0b0000_0000_0100_0000, 32),

    /**
     * 64 sampling per second
     */
    SPS_64(0b0000_0000_0110_0000, 64),

    /**
     * 128 sampling per second
     */
    SPS_128(0b0000_0000_1000_0000, 128),

    /**
     * 250 sampling per second
     */
    SPS_250(0b0000_0000_1010_0000, 250),

    /**
     * 8475sampling per second
     */
    SPS_475(0b0000_0000_1100_0000, 475),

    /**
     * 860 sampling per second
     */
    SPS_860(0b0000_0000_1110_0000, 860),

    /**
     * With an AND operation all other parameters will be set to 0
     */
    CLR_OTHER_CONF_PARAM(0b0000_0000_1110_0000, 0),

    /**
     * With an AND operation, the current parameters will be set to 0
     * all other parameters remain unchanged
     */
    CLR_CURRENT_CONF_PARAM(0b1111_1111_0001_1111, 0);

    /**
     * configuration
     */
    private final int conf;

    /**
     * sampling per second
     */
    private final int sps;

}
