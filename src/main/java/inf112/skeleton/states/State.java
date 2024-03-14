package inf112.skeleton.states;

import static inf112.skeleton.helper.Constants.height;
import static inf112.skeleton.helper.Constants.width;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected GameStateManager gsm;
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    Map<Texture, Runnable> map = new LinkedHashMap<>();
    List<Button> buttons = new ArrayList<>();
    protected State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, width, height);
    }

    public void createButtons(Integer topButton, int number, int scale){
        int gap = 0;
        for (Map.Entry<Texture, Runnable> entry : map.entrySet()) {
            Texture texture = entry.getKey();
            Runnable value = entry.getValue();
            

            int scaledWidth = texture.getWidth() / scale;
            int scaledHeight = texture.getHeight() / scale;
            
            Button button = new Button(texture,
                (width / 2) - (scaledWidth / 2),
                height - topButton - gap,
                scaledWidth,
                scaledHeight);


            button.setOnClickListener(value);
            buttons.add(button);
            gap+=number;
        }
    }
    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
}
