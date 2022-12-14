package cn.tpkf.bot.devices.i2c.display.oled;

import cn.tpkf.bot.devices.i2c.display.oled.font.Rotation;
import cn.tpkf.bot.enums.AddressEnums;
import com.pi4j.context.Context;
import lombok.Getter;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 10 下午 11:10
 */
@Getter
public class SSD1306 extends AbstractOledDisplayDevice {

    private static final byte SSD1306_SET_CONTRAST = (byte) 0x81;

    private static final byte SSD1306_DISPLAY_ALL_ON_RESUME = (byte) 0xA4;

    private static final byte SSD1306_DISPLAY_ALL_ON = (byte) 0xA5;

    private static final byte SSD1306_NORMAL_DISPLAY = (byte) 0xA6;

    private static final byte SSD1306_INVERT_DISPLAY = (byte) 0xA7;

    private static final byte SSD1306_DISPLAY_OFF = (byte) 0xAE;

    private static final byte SSD1306_DISPLAY_ON = (byte) 0xAF;

    private static final byte SSD1306_SET_DISPLAY_OFFSET = (byte) 0xD3;

    private static final byte SSD1306_SET_COM_PINS = (byte) 0xDA;

    private static final byte SSD1306_SET_VCOM_DETECT = (byte) 0xDB;

    private static final byte SSD1306_SET_DISPLAY_CLOCK_DIV = (byte) 0xD5;

    private static final byte SSD1306_SET_PRE_CHARGE = (byte) 0xD9;

    private static final byte SSD1306_SET_MULTIPLEX = (byte) 0xA8;

    private static final byte SSD1306_SET_LOW_COLUMN = (byte) 0x00;

    private static final byte SSD1306_SET_HIGH_COLUMN = (byte) 0x10;

    private static final byte SSD1306_SET_START_LINE = (byte) 0x40;

    private static final byte SSD1306_MEMORY_MODE = (byte) 0x20;

    private static final byte SSD1306_COLUMN_ADDR = (byte) 0x21;

    private static final byte SSD1306_PAGE_ADDR = (byte) 0x22;

    private static final byte SSD1306_COM_SCAN_INC = (byte) 0xC0;

    private static final byte SSD1306_COM_SCAN_DEC = (byte) 0xC8;

    private static final byte SSD1306_SEGRE_MAP = (byte) 0xA0;

    private static final byte SSD1306_CHARGE_PUMP = (byte) 0x8D;

    private static final byte SSD1306_EXTERNAL_VCC = (byte) 0x1;

    private static final byte SSD1306_SWITCH_CAP_VCC = (byte) 0x2;

    public SSD1306(Context pi4jContext, String name, int width, int height) {
        this(pi4jContext, name, 1, AddressEnums.OLED, width, height, Rotation.DEG_0);
    }

    public SSD1306(Context pi4jContext, String name, int width, int height,Rotation rotation) {
        this(pi4jContext, name, 1, AddressEnums.OLED, width, height, rotation);
    }

    public SSD1306(Context pi4jContext, String name, int i2cBus, AddressEnums address, int width, int height, Rotation rotation) {
        super(pi4jContext, i2cBus, address, name + "-SSD1306@" + width + height, width, height, rotation);
        clear();
        Runtime.getRuntime().addShutdownHook(new Thread(this::reset));
        init();
    }

    @Override
    public void setUp() {
        //DO NOTHING
    }

    @Override
    public void init() {
        //关显示
        writeCommand(SSD1306_DISPLAY_OFF);
        //设置显示始终 分比率
        writeCommand(SSD1306_SET_DISPLAY_CLOCK_DIV);
        //建议比率0x80
        writeCommand((byte) 0x80);
        //设置MUX比率
        writeCommand(SSD1306_SET_MULTIPLEX);
        //设置为0x1F，即十进制31
        writeCommand((byte) 0x1F);
        //设置显示补偿
        writeCommand(SSD1306_SET_DISPLAY_OFFSET);
        //no offset
        writeCommand((byte) 0x0);
        //line #0
        writeCommand((byte) (SSD1306_SET_START_LINE | 0x0));
        //是否使用电源
        writeCommand(SSD1306_CHARGE_PUMP);
        //使用外置电源，固定值，见ssd1306文档
        writeCommand((byte) 0x14);
        //设置内存地址模式
        writeCommand(SSD1306_MEMORY_MODE);
        //水平地址模式
        writeCommand(SSD1306_SET_LOW_COLUMN);
        //实在没搞懂这个是什么用处的
        writeCommand((byte) (SSD1306_SEGRE_MAP | 0x1));
        //设置列输出扫描方向, Scan from COM[N-1] to COM0
        writeCommand(SSD1306_COM_SCAN_DEC);
        //设置列引脚硬件配置
        writeCommand(SSD1306_SET_COM_PINS);
        //上文已经描述该字段
        writeCommand((byte) 0x02);
        //设置对比度
        writeCommand(SSD1306_SET_CONTRAST);
        writeCommand((byte) 0x8F);
        //设置预充电周期
        writeCommand(SSD1306_SET_PRE_CHARGE);
        writeCommand((byte) 0xF1);
        //设置VCOM反压值
        writeCommand(SSD1306_SET_VCOM_DETECT);
        writeCommand((byte) 0x40);
        //启用输出GDD RAM中的数据
        writeCommand(SSD1306_DISPLAY_ALL_ON_RESUME);
        //设置正常显示，A7表示反转显示
        writeCommand(SSD1306_NORMAL_DISPLAY);
        //--turn on an oled panel
        writeCommand(SSD1306_DISPLAY_ON);
    }

    @Override
    public void updateDataBuffer() {
        int displayWidth = getDisplayWidth();
        int displayHeight = getDisplayHeight();
        writeCommand(SSD1306_COLUMN_ADDR);
        // Column start address (0 = reset)
        writeCommand((byte) 0);
        // Column end address (127 = reset)
        writeCommand((byte) (displayWidth - 1));
        writeCommand(SSD1306_PAGE_ADDR);
        // Page start address (0 = reset)
        writeCommand((byte) 0);
        // Page end address
        writeCommand((byte) 7);
        for (int i = 0; i < ((displayWidth * displayHeight / 8) / 16); i++) {
            // send a bunch of data in one x mission
            i2c.writeRegister((byte) 0x40, dataBuffer, i * 16, 16);
        }
    }
}
