package cn.tpkf.bot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 10 下午 01:25
 */
@Getter
@AllArgsConstructor
public enum PgaEnums {

    /**
     * 000 : FSR = ±6.144 V
     */
    FSR_6_144(0b0000_0000_0000_0000),

    /**
     * 001 : FSR = ±4.096 V
     */
    FSR_4_096(0b0000_0010_0000_0000),

    /**
     * 010 : FSR = ±2.048 V
     */
    FSR_2_048(0b0000_0100_0000_0000),

    /**
     * 011 : FSR = ±1.024 V
     */
    FSR_1_024(0b0000_0110_0000_0000),

    /**
     * 100 : FSR = ±0.512 V
     */
    FSR_0_512(0b0000_1000_0000_0000),

    /**
     * 101 : FSR = ±0.256 V
     */
    FSR_0_256(0b0000_1010_0000_0000),

    /**
     * With an AND operation, all other parameters will be set to 0
     */
    CLR_OTHER_CONF_PARAM(0b0000_1110_0000_0000),

    /**
     * With an AND operation, the current parameters will be set to 0
     * all other parameters remain unchanged
     */
    CLR_CURRENT_CONF_PARAM(0b1111_0001_1111_1111);

    /**
     * Programmable gain amplifier
     */
    private final int value;
}
