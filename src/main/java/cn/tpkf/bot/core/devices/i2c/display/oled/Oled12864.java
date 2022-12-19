package cn.tpkf.bot.core.devices.i2c.display.oled;

import cn.tpkf.bot.core.devices.i2c.display.oled.font.Rotation;
import cn.tpkf.bot.core.devices.i2c.display.oled.font.Font;
import cn.tpkf.bot.enums.AddressEnums;
import com.pi4j.context.Context;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 17 上午 11:23
 */
public class Oled12864 extends AbstractSSD1306Device {

    public Oled12864(Context pi4jContext, String name) {
        this(pi4jContext, 1, AddressEnums.OLED, name, Rotation.DEG_0);
    }

    public Oled12864(Context pi4jContext, int i2cBus, AddressEnums address, String name) {
        this(pi4jContext, i2cBus, address, name, Rotation.DEG_0);
    }

    public Oled12864(Context pi4jContext, int i2cBus, AddressEnums address, String name, Rotation rotation) {
        super(pi4jContext, i2cBus, address, name, 128, 64, rotation);
        resetDataBuffer();
        Runtime.getRuntime().addShutdownHook(new Thread(this::resetDraw));
        init();
    }

    @Override
    public void init() {
        writeCommand(SSD1306_DISPLAY_OFF);
        writeCommand(SSD1306_SET_DISPLAY_CLOCK_DIV);
        writeCommand((byte) 0x80);
        writeCommand(SSD1306_SET_MULTIPLEX);
        writeCommand((byte) 0x3F);
        writeCommand(SSD1306_SET_DISPLAY_OFFSET);
        writeCommand((byte) 0x0);
        writeCommand((byte) (SSD1306_SET_START_LINE | 0x0));
        writeCommand(SSD1306_CHARGE_PUMP);
        writeCommand((byte) 0x14);
        writeCommand(SSD1306_MEMORY_MODE);
        writeCommand((byte) 0x00);
        writeCommand((byte) (SSD1306_SEGRE_MAP | 0x1));
        writeCommand(SSD1306_COM_SCAN_DEC);
        writeCommand(SSD1306_SET_COM_PINS);
        writeCommand((byte) 0x12);
        writeCommand(SSD1306_SET_CONTRAST);
        writeCommand((byte) 0xCF);
        writeCommand(SSD1306_SET_PRE_CHARGE);
        writeCommand((byte) 0xF1);
        writeCommand(SSD1306_SET_VCOM_DETECT);
        writeCommand((byte) 0x40);
        writeCommand(SSD1306_DISPLAY_ALL_ON_RESUME);
        writeCommand(SSD1306_NORMAL_DISPLAY);
        //--turn on an oled panel
        writeCommand(SSD1306_DISPLAY_ON);
    }

    @Override
    public void setUp() {

    }

    @Override
    protected void customDrawChar(char c, Font font, int x, int y, boolean state) {
        for (int i = 0; i < font.getWidth(); ++i) {
            int line = font.getData((c * font.getWidth()) + i);
            for (int j = 0; j < font.getHeight(); ++j) {
                if ((line & 0x01) > 0) {
                    setPixel(x + i, y + j, state);
                }
                line >>= 1;
            }
        }
    }
}
