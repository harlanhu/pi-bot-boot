package cn.tpkf.bot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Programmable gain amplifier configuration
 * These bits set the FSR of the programmable gain amplifier.
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 10 下午 01:24
 */
@Getter
@AllArgsConstructor
public enum GainEnums {

    /**
     * 000 : Full-Scale Range (FSR)  = ±6.144 V
     */
    GAIN_6_144V(PgaEnums.FSR_6_144.getValue(), 187.5 / 1_000_000),
    /**
     * 001 : FSR = ±4.096 V
     */
    GAIN_4_096V(PgaEnums.FSR_4_096.getValue(), 125.0 / 1_000_000),
    /**
     * 010 : FSR = ±2.048 V
     */
    GAIN_2_048V(PgaEnums.FSR_2_048.getValue(), 62.5 / 1_000_000),
    /**
     * 011 : FSR = ±1.024 V
     */
    GAIN_1_024V(PgaEnums.FSR_1_024.getValue(), 31.25 / 1_000_000),
    /**
     * 100 : FSR = ±0.512 V
     */
    GAIN_0_512V(PgaEnums.FSR_0_512.getValue(), 15.625 / 1_000_000),
    /**
     * 101 : FSR = ±0.256 V
     */
    GAIN_0_256V(PgaEnums.FSR_0_256.getValue(), 7.8125 / 1_000_000);
    /**
     * bit structure for configuration
     */
    private final int gain;
    /**
     * gain per bit
     */
    private final double gainPerBit;
}
