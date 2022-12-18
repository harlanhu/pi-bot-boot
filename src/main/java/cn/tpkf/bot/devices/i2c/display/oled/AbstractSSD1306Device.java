package cn.tpkf.bot.devices.i2c.display.oled;

import cn.tpkf.bot.devices.i2c.AbstractI2cDevice;
import cn.tpkf.bot.devices.i2c.display.oled.font.Font;
import cn.tpkf.bot.devices.i2c.display.oled.font.Rotation;
import cn.tpkf.bot.enums.AddressEnums;
import com.pi4j.context.Context;
import lombok.Getter;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 11 下午 12:30
 */
@Getter
public abstract class AbstractSSD1306Device extends AbstractI2cDevice implements OledDisplayDevice {

    protected final int width;

    protected final int height;

    protected final int maxIndex;

    protected final Rotation rotation;

    protected final ReentrantLock lock;

    protected final byte[] dataBuffer;

    protected static final byte SSD1306_SET_CONTRAST = (byte) 0x81;

    protected static final byte SSD1306_DISPLAY_ALL_ON_RESUME = (byte) 0xA4;

    protected static final byte SSD1306_DISPLAY_ALL_ON = (byte) 0xA5;

    protected static final byte SSD1306_NORMAL_DISPLAY = (byte) 0xA6;

    protected static final byte SSD1306_INVERT_DISPLAY = (byte) 0xA7;

    protected static final byte SSD1306_DISPLAY_OFF = (byte) 0xAE;

    protected static final byte SSD1306_DISPLAY_ON = (byte) 0xAF;

    protected static final byte SSD1306_SET_DISPLAY_OFFSET = (byte) 0xD3;

    protected static final byte SSD1306_SET_COM_PINS = (byte) 0xDA;

    protected static final byte SSD1306_SET_VCOM_DETECT = (byte) 0xDB;

    protected static final byte SSD1306_SET_DISPLAY_CLOCK_DIV = (byte) 0xD5;

    protected static final byte SSD1306_SET_PRE_CHARGE = (byte) 0xD9;

    protected static final byte SSD1306_SET_MULTIPLEX = (byte) 0xA8;

    protected static final byte SSD1306_SET_LOW_COLUMN = (byte) 0x00;

    protected static final byte SSD1306_SET_HIGH_COLUMN = (byte) 0x10;

    protected static final byte SSD1306_SET_START_LINE = (byte) 0x40;

    protected static final byte SSD1306_MEMORY_MODE = (byte) 0x20;

    protected static final byte SSD1306_COLUMN_ADDR = (byte) 0x21;

    protected static final byte SSD1306_PAGE_ADDR = (byte) 0x22;

    protected static final byte SSD1306_COM_SCAN_INC = (byte) 0xC0;

    protected static final byte SSD1306_COM_SCAN_DEC = (byte) 0xC8;

    protected static final byte SSD1306_SEGRE_MAP = (byte) 0xA0;

    protected static final byte SSD1306_CHARGE_PUMP = (byte) 0x8D;

    protected static final byte SSD1306_EXTERNAL_VCC = (byte) 0x1;

    protected static final byte SSD1306_SWITCH_CAP_VCC = (byte) 0x2;

    protected AbstractSSD1306Device(Context pi4jContext, int i2cBus, AddressEnums address, String name, int width, int height, Rotation rotation) {
        super(pi4jContext, name, i2cBus, address);
        this.width = width;
        this.height = height;
        this.maxIndex = (height / 8) * width;
        this.rotation = rotation;
        this.dataBuffer = new byte[(height * width) / 8];
        this.lock = new ReentrantLock();
    }

    @Override
    public boolean isLocked() {
        return lock.isLocked();
    }

    @Override
    public void writeCommand(byte command) {
        lock.lock();
        try {
            getI2c().writeRegister(0x00, command);
        } finally {
            lock.unlock();
        }
    }

    public void updateDataBuffer(int x, int y, boolean state) {
        lock.lock();
        try {
            final int pos = x + (y / 8) * width;
            if (pos >= 0 && pos < maxIndex) {
                if (state) {
                    dataBuffer[pos] |= (1 << (y & 0x07));
                } else {
                    dataBuffer[pos] &= ~(1 << (y & 0x07));
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void setPixel(int x, int y, boolean state) {
        lock.lock();
        try {
            switch (rotation) {
                case DEG_0 -> updateDataBuffer(x, y, state);
                case DEG_90 -> updateDataBuffer(y, getWidth() - x - 1, state);
                case DEG_180 -> updateDataBuffer(getWidth() - x - 1, getHeight() - y - 1, state);
                case DEG_270 -> updateDataBuffer(getHeight() - y - 1, x, state);
            }
        } finally {
            lock.unlock();
        }
    }

    public void drawChar(char c, Font font, int x, int y, boolean state) {
        lock.lock();
        try {
            if (c > font.getMaxChar() || c < font.getMinChar()) {
                c = '?';
            }
            c -= font.getMinChar();
            customDrawChar(c, font, x, y, state);
        } finally {
            lock.unlock();
        }
    }

    protected abstract void customDrawChar(char c, Font font, int x, int y, boolean state);

    public void drawChar(char c, int x, int y, boolean state) {
        Font font = Font.FONT_4X5;
        drawChar(c, font, x, y, state);
    }

    public void drawString(String text, Font font, int x, int y, boolean state) {
        lock.lock();
        try {
            int posX = x;
            int posY = y;
            for (char c : text.toCharArray()) {
                if (c == '\n') {
                    posY += font.getOuterHeight();
                    posX = x;
                } else {
                    if (posX >= 0 && posX + font.getWidth() < getDisplayWidth()
                            && posY >= 0 && posY + font.getHeight() < getDisplayHeight()) {
                        drawChar(c, font, posX, posY, state);
                    }
                    posX += font.getOuterWidth();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void drawString(String text, int x, int y, boolean state) {
        Font font = Font.FONT_5X8;
        drawString(text, font, x, y, state);
    }

    public void drawImage(BufferedImage image, int x, int y) {
        BufferedImage tmpImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        tmpImage.getGraphics().drawImage(image, x, y, null);
        int index = 0;
        int pixelVal;
        final byte[] pixels = ((DataBufferByte) tmpImage.getRaster().getDataBuffer()).getData();
        for (int posY = 0; posY < height; posY++) {
            for (int posX = 0; posX < width / 8; posX++) {
                for (int bit = 0; bit < 8; bit++) {
                    pixelVal = (byte) ((pixels[index / 8] >> (7 - bit)) & 0x01);
                    setPixel(posX * 8 + bit, posY, pixelVal > 0);
                    index++;
                }
            }
        }
    }

    public void drawStringCentered(String text, Font font, int y, boolean state) {
        final int strSizeX = text.length() * font.getOuterWidth();
        final int x = (getDisplayWidth() - strSizeX) / 2;
        drawString(text, font, x, y, state);
    }

    public void drawStringCentered(String text, int y, boolean state) {
        drawStringCentered(text, Font.FONT_5X8, y, state);
    }

    @Override
    public void clearAndDrawString(String text, int x, int y, boolean state) {
        resetDataBuffer();
        drawString(text, x, y, state);
        update();
    }

    public void resetDataBuffer() {
        Arrays.fill(dataBuffer, (byte) 0x00);
    }

    public void resetDraw() {
        //before we shut down we resetDataBuffer the display
        resetDataBuffer();
        update();
    }

    @Override
    public void update() {
        writeCommand(SSD1306_COLUMN_ADDR);
        // Column start address (0 = resetDraw)
        writeCommand((byte) 0);
        // Column end address (127 = resetDraw)
        writeCommand((byte) (width - 1));
        writeCommand(SSD1306_PAGE_ADDR);
        // Page start address (0 = resetDraw)
        writeCommand((byte) 0);
        // Page end address
        writeCommand((byte) 7);
        for (int i = 0; i < ((width * height / 8) / 16); i++) {
            // send a bunch of data in one x mission
            byte[] tmpData = Arrays.copyOfRange(dataBuffer, i * 16, i * 16 + 16);
            i2c.writeRegister((byte) 0x40, tmpData);
        }
    }

    @Override
    public int getDisplayHeight() {
        return switch (rotation) {
            case DEG_90, DEG_270 -> width;
            case DEG_0, DEG_180 -> height;
        };
    }

    @Override
    public int getDisplayWidth() {
        return switch (rotation) {
            case DEG_90, DEG_270 -> height;
            case DEG_0, DEG_180 -> width;
        };
    }
}
