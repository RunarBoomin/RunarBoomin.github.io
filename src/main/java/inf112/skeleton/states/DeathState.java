package inf112.skeleton.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.List;
import java.util.ArrayList;

public class DeathState extends State {
    private List<Button> buttons;
    private GameStateManager gsm;

    public DeathState(GameStateManager gsm) {
        super(gsm);
        this.gsm = gsm;
        buttons = new ArrayList<>();
        
        // Button initialization logic moved here
        addButton(new Texture("images/buttonRestart.png"), () -> gsm.set(new PlayState(gsm)));
        createButtons(300, 100, 2); // assuming createButtons is implemented elsewhere
    }

    private void addButton(Texture texture, Runnable action) {
        // Define initial button properties
        float x = 10;  // Example coordinates; adapt as necessary
        float y = 20;
        float width = 100; // Example dimensions; adapt as needed
        float height = 50;
    
        // Create and configure the button
        Button button = new Button(texture, x, y, width, height); // Initialize visually
        button.setOnClickListener(action); // Set action via method
        buttons.add(button); // Add to the list
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
                    break; // Handle only one click per input
                }
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput(); // Keep game logic here
    }

    @Override
    public void render(SpriteBatch sb) {
        gsm.lastState().render(sb); // Render previous state

        sb.begin();

        for (Button button : buttons) {
            button.render(sb); // Separate rendering logic for each button
        }

        sb.end();
    }
    public List<Button> getterButton() {
        return this.buttons;
    }
}