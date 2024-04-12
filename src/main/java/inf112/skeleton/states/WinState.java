package inf112.skeleton.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static inf112.skeleton.helper.Constants.height;
import static inf112.skeleton.helper.Constants.width;

public class WinState extends State {

    public WinState(GameStateManager gsm) {
        super(gsm);
        map.put(new Texture("images/buttonRestart.png"), () -> {
            // Handle button click event here
            gsm.push(new MenuState(gsm));
        });

        createButtons(height/2, 120, 2);
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
        Texture test = new Texture("images/frame5.png");
        Texture win = new Texture("images/win.png");
        /* sb.draw(test, width/2-test.getWidth()/2, height/4, test.getWidth(), test.getHeight()); */
        sb.draw(win, width/2-win.getWidth()/2, height/2 + 100, win.getWidth(), win.getHeight());
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
