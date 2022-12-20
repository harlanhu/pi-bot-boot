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
     * 显示数据区内容
     */
    void display();

    /**
     * 写入指令
     * @param command 指令
     */
    void writeCommand(byte command);

    /**
     * 设置数据区
     * @param x x坐标
     * @param y y坐标
     * @param state 状态
     */
    void setDataBuffer(int x, int y, boolean state);

    /**
     * 设置像素点状态
     * @param x x坐标
     * @param y y坐标
     * @param state 状态
     */
    void setPixel(int x, int y, boolean state);

    /**
     * 设置字符像素
     * @param c 字符
     * @param font 字体
     * @param x 显示坐标 x
     * @param y 显示坐标 y
     * @param state 状态
     */
    void setCharPixel(char c, Font font, int x, int y, boolean state);

    /**
     * 设置字符像素
     * @param c 字符
     * @param x 显示坐标 x
     * @param y 显示坐标 y
     * @param state 状态
     */
    void setCharPixel(char c, int x, int y, boolean state);

    /**
     * 设置字符串像素
     * @param text 字符串
     * @param x 显示坐标 x
     * @param y 显示坐标 y
     * @param state 状态
     */
    void setStrPixel(String text, int x, int y, boolean state);

    /**
     * 添加设置字符串像素
     * @param text 字符串
     * @param font 字体
     * @param x 显示坐标 x
     * @param y 显示坐标 y
     * @param state 状态
     */
    void setStrPixel(String text, Font font, int x, int y, boolean state);

    /**
     * 显示字符
     * @param c 字符
     * @param font 字体
     * @param x 显示坐标 x
     * @param y 显示坐标 y
     * @param state 状态
     */
    void displayChar(char c, Font font, int x, int y, boolean state);

    /**
     * 显示字符
     * @param c 字符
     * @param x 显示坐标 x
     * @param y 显示坐标 y
     * @param state 状态
     */
    void displayChar(char c, int x, int y, boolean state);

    /**
     * 添加显示字符
     * @param c 字符
     * @param font 字体
     * @param x 显示坐标 x
     * @param y 显示坐标 y
     * @param state 状态
     */
    void appendDisplayChar(char c, Font font, int x, int y, boolean state);

    /**
     * 添加显示字符
     * @param c 字符
     * @param x 显示坐标 x
     * @param y 显示坐标 y
     * @param state 状态
     */
    void appendDisplayChar(char c, int x, int y, boolean state);

    /**
     * 显示字符串
     * @param text 字符串
     * @param font 字体
     * @param x 显示坐标 x
     * @param y 显示坐标 y
     * @param state 状态
     */
    void displayStr(String text, Font font, int x, int y, boolean state);

    /**
     * 显示字符串
     * @param text 字符串
     * @param x 显示坐标 x
     * @param y 显示坐标 y
     * @param state 状态
     */
    void displayStr(String text, int x, int y, boolean state);

    /**
     * 添加显示字符串
     * @param text 字符串
     * @param font 字体
     * @param x 显示坐标 x
     * @param y 显示坐标 y
     * @param state 状态
     */
    void appendDisplayStr(String text, Font font, int x, int y, boolean state);

    /**
     * 添加显示字符串
     * @param text 字符串
     * @param x 显示坐标 x
     * @param y 显示坐标 y
     * @param state 状态
     */
    void appendDisplayStr(String text, int x, int y, boolean state);



    /**
     * 左右居中显示字符串
     * @param text 字符串
     * @param y 显示坐标 y
     * @param state 状态
     */
    void displayStrCentered(String text, int y, boolean state);

    /**
     * 左右居中显示字符串
     * @param text 字符串
     * @param font 字体
     * @param y 显示坐标 y
     * @param state 状态
     */
    void displayStrCentered(String text, Font font, int y, boolean state);

    /**
     * 添加左右居中显示字符串
     * @param text 字符串
     * @param font 字体
     * @param y 显示坐标 y
     * @param state 状态
     */
    void appendDisplayStrCentered(String text, Font font, int y, boolean state);

    /**
     * 左右居中显示字符串
     * @param text 字符串
     * @param y 显示坐标 y
     * @param state 状态
     */
    void appendDisplayStrCentered(String text, int y, boolean state);

    /**
     * 显示图片
     * @param image 图片流
     * @param x 显示坐标 x
     * @param y 显示坐标 y
     */
    void displayImage(BufferedImage image, int x, int y);

    /**
     * 重置数据区
     */
    void resetDataBuffer();

}
