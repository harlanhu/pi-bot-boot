package cn.tpkf.bot.devices.i2c.display.oled;

import cn.tpkf.bot.devices.i2c.AbstractI2cDevice;
import cn.tpkf.bot.devices.i2c.display.oled.font.Font;
import cn.tpkf.bot.devices.i2c.display.oled.font.Rotation;
import cn.tpkf.bot.enums.AddressEnums;
import cn.tpkf.bot.utils.GpioConfigUtils;
import com.pi4j.context.Context;
import com.pi4j.io.i2c.I2C;
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
public abstract class AbstractOledDisplayDevice extends AbstractI2cDevice implements OledDisplayDevice {

    protected final int width;

    protected final int height;

    protected final int maxIndex;

    protected final Rotation rotation;

    protected final ReentrantLock lock;

    protected final byte[] dataBuffer;

    protected AbstractOledDisplayDevice(Context pi4jContext, int i2cBus, AddressEnums address, String name, int width, int height, Rotation rotation) {
        super(pi4jContext, name, i2cBus, address);
        this.width = width;
        this.height = height;
        this.maxIndex = (height / 8) * width;
        this.rotation = rotation;
        this.dataBuffer = new byte[(height * width) / 8];
        this.lock = new ReentrantLock();
    }

    @Override
    public int getMaxIndex() {
        return maxIndex;
    }

    @Override
    public Rotation getRotation() {
        return rotation;
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
                    this.dataBuffer[pos] |= (1 << (y & 0x07));
                } else {
                    this.dataBuffer[pos] &= ~(1 << (y & 0x07));
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void setPixel(int x, int y, boolean state) {
        lock.lock();
        try {
            int currX, currY;
            switch (rotation) {
                case DEG_90 -> {
                    currX = y;
                    currY = getDisplayWidth() - x - 1;
                }
                case DEG_180 -> {
                    currX = getDisplayWidth() - x - 1;
                    currY = getDisplayHeight() - y - 1;
                }
                case DEG_270 -> {
                    currX = getDisplayHeight() - y - 1;
                    currY = x;
                }
                default -> {
                    currX = x;
                    currY = y;
                }
            }
            updateDataBuffer(currX, currY, state);
        } finally {
            lock.unlock();
        }
    }

    public void drawChar(char c, Font font, int x, int y, boolean state) {
        lock.lock();
        try {
            font.drawChar(this, c, x, y, state);
        } finally {
            lock.unlock();
        }
    }

    public void drawChar(char c, int x, int y, boolean state) {
        Font font = Font.FONT_5X8;
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
                    if (posX >= 0 && posX + font.getWidth() < this.getDisplayWidth()
                            && posY >= 0 && posY + font.getHeight() < this.getDisplayHeight()) {
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
        int pixelval;
        final byte[] pixels = ((DataBufferByte) tmpImage.getRaster().getDataBuffer()).getData();
        for (int posY = 0; posY < getDisplayHeight(); posY++) {
            for (int posX = 0; posX < getDisplayWidth() / 8; posX++) {
                for (int bit = 0; bit < 8; bit++) {
                    pixelval = (byte) ((pixels[index / 8] >> (7 - bit)) & 0x01);
                    setPixel(posX * 8 + bit, posY, pixelval > 0);
                    index++;
                }
            }
        }
    }

    public void drawStringCentered(String text, Font font, int y, boolean state) {
        final int strSizeX = text.length() * font.getOuterWidth();
        final int x = (width - strSizeX) / 2;
        drawString(text, font, x, y, state);
    }

    public void clear() {
        Arrays.fill(dataBuffer, (byte) 0x00);
    }

    public void reset() {
        //before we shut down we clear the display
        clear();
        updateDataBuffer();
    }

    @Override
    public int getDisplayHeight() {
        if (rotation == Rotation.DEG_270) {
            return width;
        } else {
            return height;
        }
    }

    @Override
    public int getDisplayWidth() {
        if (rotation == Rotation.DEG_270) {
            return height;
        } else {
            return width;
        }
    }
}
