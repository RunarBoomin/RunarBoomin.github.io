package inf112.skeleton.helper;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class Constants {
    static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    static DisplayMode currentMode = gd.getDisplayMode();

    static int screenWidth = currentMode.getWidth();
    static int screenHeight = currentMode.getHeight();

    public static final float PPM = 32.0f;
    public static final int width = screenWidth;
    public static final int height = screenHeight;
}
