package inf112.skeleton.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PauseState extends State {


    public PauseState(GameStateManager gsm) {
        super(gsm);
        map.put(new Texture("images/button3.png"), () -> {
            // Handle button click event here
            gsm.pop();
        });
        
        map.put(new Texture("images/buttonRestart.png"), () -> {
            // Handle button click event here
            gsm.set(new PlayState(gsm));
        });

        map.put(new Texture("images/buttonSettings.png"), () -> {
            // Handle button click event here
            Gdx.app.exit();
        });

        map.put(new Texture("images/buttonMenu.png"), () -> {
            // Handle button click event here
            gsm.set(new MenuState(gsm));
        });

        map.put(new Texture("images/buttonQuit.png"), () -> {
            // Handle button click event here
            Gdx.app.exit();
        });

        createButtons(300, 100, 2);

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
        gsm.lastState().render(sb);
        sb.begin();
        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Flip Y coordinate
        for (Button button : buttons) {
             // Assuming 'sb' is your SpriteBatch object
            if (button.contains(mouseX, mouseY)) {
                button.hover(sb);
            }
            else
            button.render(sb);

        }
        sb.end();
    }
    
}
