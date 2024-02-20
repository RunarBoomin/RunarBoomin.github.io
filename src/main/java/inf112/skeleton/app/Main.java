package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setIdleFPS(60);
        cfg.useVsync(true);
        cfg.setTitle("hello-world");
        cfg.setWindowedMode(1980, 1080);

        new Lwjgl3Application(new HelloWorld(), cfg);
    }
}