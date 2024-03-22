package inf112.skeleton.states;

import static inf112.skeleton.helper.Constants.height;
import static inf112.skeleton.helper.Constants.width;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected GameStateManager gsm;
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    Map<Texture, Runnable> map = new LinkedHashMap<>();
    List<Button> buttons = new ArrayList<>();

    private ShapeRenderer shapeRenderer;
    private float screenWidth;
    private float screenHeight;

    private float scaledWidth;
    protected State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, width, height);

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        
        shapeRenderer = new ShapeRenderer();
    }

    public void createButtons(Integer topButton, int number, float scale){
        int gap = 0;
        for (Map.Entry<Texture, Runnable> entry : map.entrySet()) {
            Texture texture = entry.getKey();
            Runnable value = entry.getValue();
            

            this.scaledWidth = texture.getWidth() / scale;
            float scaledHeight = texture.getHeight() / scale;
            
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

    public void backgroundFrame() {
        // Render shapes
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    
        // Calculate the total height required for all buttons
        float totalButtonHeight = 0;
        float scale = 1.0f; // Assuming a default scale if buttons are not yet created
        for (Button button : buttons) {
            totalButtonHeight += button.getHeight() * scale; // Adjust scale if needed
        }
    
        // Calculate the coordinates to center the rectangle
        
        float rectHeight = totalButtonHeight; // Height based on total button height
        float rectX = ((screenWidth/2)- scaledWidth/2);
        float rectY = (screenHeight - rectHeight) / 2;
    
/*         // Draw drop shadow
        shapeRenderer.setColor(0, 0, 0, 0.5f);
        drawRoundedRect(shapeRenderer, rectX + 5, rectY - 5, scaledWidth, rectHeight, 20);
    
        // Draw border
        shapeRenderer.setColor(0.2f, 0.2f, 0.2f, 1);
        drawRoundedRect(shapeRenderer, rectX, rectY, scaledWidth, rectHeight, 20); */
    
        // Draw main rectangle
        shapeRenderer.setColor(1, 0, 0, 1);
        drawRoundedRect(shapeRenderer, rectX, rectY + 100, scaledWidth, rectHeight, 20);
    
        shapeRenderer.end();
    }
    
    private void drawRoundedRect(ShapeRenderer shapeRenderer, float x, float y, float width, float height, float cornerRadius) {
        // Drawing the rounded rectangle using ShapeRenderer
        shapeRenderer.rect(x + cornerRadius, y, width - 2 * cornerRadius, height);
        shapeRenderer.rect(x, y + cornerRadius, cornerRadius, height - 2 * cornerRadius);
        shapeRenderer.rect(x + width - cornerRadius, y + cornerRadius, cornerRadius, height - 2 * cornerRadius);
        shapeRenderer.arc(x + cornerRadius, y + cornerRadius, cornerRadius, 180f, 90f);
        shapeRenderer.arc(x + width - cornerRadius, y + cornerRadius, cornerRadius, 270f, 90f);
        shapeRenderer.arc(x + width - cornerRadius, y + height - cornerRadius, cornerRadius, 0f, 90f);
        shapeRenderer.arc(x + cornerRadius, y + height - cornerRadius, cornerRadius, 90f, 90f);
    }
    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
}
