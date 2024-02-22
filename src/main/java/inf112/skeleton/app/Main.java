package inf112.skeleton.app;
import static inf112.skeleton.helper.Constants.width;
import static inf112.skeleton.helper.Constants.height;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setIdleFPS(60);
        cfg.useVsync(true);
        cfg.setTitle("hello-world");
        cfg.setWindowedMode(width, height);
        

        new Lwjgl3Application(new HelloWorld(), cfg);
    }
}