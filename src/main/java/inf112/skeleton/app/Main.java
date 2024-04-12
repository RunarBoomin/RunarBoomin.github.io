package inf112.skeleton.app;
import static inf112.skeleton.helper.Constants.width;
import static inf112.skeleton.helper.Constants.height;

import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
       
        
        cfg.setTitle("hello-world");
        cfg.setWindowedMode(width, height);
        

         // Get the display mode for fullscreen
        DisplayMode displayMode = Lwjgl3ApplicationConfiguration.getDisplayMode();
        cfg.useVsync(true);
        // Set fullscreen mode
        cfg.setFullscreenMode(displayMode);

        new Lwjgl3Application(new HelloWorld(), cfg);
    }
}