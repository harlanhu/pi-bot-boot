package cn.tpkf.bot.devices.i2c.display.oled;

import cn.tpkf.bot.devices.i2c.display.oled.font.Font;
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
public class Oled12832 extends AbstractSSD1306Device {

    public Oled12832(Context pi4jContext, String name) {
        this(pi4jContext, name, 1, AddressEnums.OLED, Rotation.DEG_0);
    }

    public Oled12832(Context pi4jContext, String name, Rotation rotation) {
        this(pi4jContext, name, 1, AddressEnums.OLED, rotation);
    }

    public Oled12832(Context pi4jContext, String name, int i2cBus, AddressEnums address, Rotation rotation) {
        super(pi4jContext, i2cBus, address, name, 128, 32, rotation);
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
    protected void customDrawChar(char c, Font font, int x, int y, boolean state) {
        for (int i = 0; i < font.getWidth(); ++i) {
            int line = font.getData((c * font.getWidth()) + i);
            for (int j = 0; j < font.getHeight() * 2; j += 2) {
                if ((line & 0x01) > 0) {
                    setPixel(x + i, 1 + 2 * y + j, state);
                }
                line >>= 1;
            }
        }
    }
}
