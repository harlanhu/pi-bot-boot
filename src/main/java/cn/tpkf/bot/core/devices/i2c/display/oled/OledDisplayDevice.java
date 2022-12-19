package cn.tpkf.bot.core.devices.i2c.display.oled;

import cn.tpkf.bot.core.devices.i2c.display.oled.font.Font;
import cn.tpkf.bot.core.devices.SingleDevice;
import cn.tpkf.bot.core.devices.i2c.I2cDevice;
import cn.tpkf.bot.core.devices.i2c.display.DisplayDevice;

import java.awt.image.BufferedImage;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 11 下午 04:35
 */
public interface OledDisplayDevice extends DisplayDevice, SingleDevice, I2cDevice {

    /**
     * 初始化oled
     */
    void init();

    /**
     * 刷新数据缓存
     */
    void update();

    /**
     * 写入指令
     * @param command 指令
     */
    void writeCommand(byte command);

    /**
     * 更新数据区
     * @param x x坐标
     * @param y y坐标
     * @param state 状态
     */
    void updateDataBuffer(int x, int y, boolean state);

    /**
     * 设置像素点状态
     * @param x x坐标
     * @param y y坐标
     * @param state 状态
     */
    void setPixel(int x, int y, boolean state);

    /**
     * 显示字符
     * @param c 字符
     * @param font 字体
     * @param x 显示坐标 x
     * @param y 显示坐标 y
     * @param state 状态
     */
    void drawChar(char c, Font font, int x, int y, boolean state) throws Exception;

    /**
     * 显示字符
     * @param c 字符
     * @param x 显示坐标 x
     * @param y 显示坐标 y
     * @param state 状态
     */
    void drawChar(char c, int x, int y, boolean state);

    /**
     * 显示字符串
     * @param text 字符串
     * @param font 字体
     * @param x 显示坐标 x
     * @param y 显示坐标 y
     * @param state 状态
     */
    void drawString(String text, Font font, int x, int y, boolean state);

    /**
     * 显示字符串
     * @param text 字符串
     * @param x 显示坐标 x
     * @param y 显示坐标 y
     * @param state 状态
     */
    void clearAndDrawString(String text, int x, int y, boolean state);

    /**
     * 显示字符串
     * @param text 字符串
     * @param x 显示坐标 x
     * @param y 显示坐标 y
     * @param state 状态
     */
    void drawString(String text, int x, int y, boolean state);

    /**
     * 显示图片
     * @param image 图片流
     * @param x 显示坐标 x
     * @param y 显示坐标 y
     */
    void drawImage(BufferedImage image, int x, int y);

    /**
     * 左右居中显示字符串
     * @param text 字符串
     * @param font 字体
     * @param y 显示坐标 y
     * @param state 状态
     */
    void drawStringCentered(String text, Font font, int y, boolean state);

    /**
     * 左右居中显示字符串
     * @param text 字符串
     * @param y 显示坐标 y
     * @param state 状态
     */
    void drawStringCentered(String text, int y, boolean state);

    /**
     * 清空数据区
     */
    void resetDataBuffer();

    /**
     * 重置显示
     */
    void resetDraw();

}
