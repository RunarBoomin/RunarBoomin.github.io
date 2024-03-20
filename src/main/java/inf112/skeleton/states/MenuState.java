package inf112.skeleton.states;

import static inf112.skeleton.helper.Constants.height;
import static inf112.skeleton.helper.Constants.width;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class MenuState extends State{
    Texture background;
    Texture title;
    private OrthographicCamera camera;

    
    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("images/background2.jpg");
        title = new Texture("images/title.png");



        map.put(new Texture("images/button3.png"), () -> {
            // Handle button click event here
            gsm.set(new PlayState(gsm));
        });

        map.put(new Texture("images/buttonSettings.png"), () -> {
            // Handle button click event here
            System.out.println("settings pressed");
        });

        map.put(new Texture("images/buttonQuit.png"), () -> {
            // Handle button click event here
            Gdx.app.exit();
        });

        createButtons(300, 200, 1f);
        
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Flip Y coordinate
    
            // Iterate through the list of buttons
            for (Button button : buttons) {
                if (button.contains(mouseX, mouseY)) {
                    button.handleClick();
                    // If you want to handle only one button click, break out of the loop
                    // break;
                }
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {

    
        // Render background and sprites
        sb.begin();
        sb.draw(background, 0, 0, width, height);
        sb.end();
        //backgroundFrame();
        sb.begin();
/*         Texture test = new Texture("images/frame3.png");
        sb.draw(test, width/2-test.getWidth()/2, -200, test.getWidth(), test.getHeight()); */
        // Render buttons
        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Flip Y coordinate
        for (Button button : buttons) {
            if (button.contains(mouseX, mouseY)) {
                button.hover(sb);
            } else {
                button.render(sb);
            }
        }
        
        sb.end();
        
        
    }

    
}