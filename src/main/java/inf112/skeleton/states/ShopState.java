package inf112.skeleton.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.skeleton.objects.Shop.Shop;
import inf112.skeleton.objects.player.Player;

public class ShopState extends State {

    Player player;
    Shop shop;

    public ShopState(GameStateManager gsm, Player player) {
        super(gsm);
        shop = new Shop(5, player);
        for (int i = 0; i < shop.getShop().size(); i++) {
            final int index = i;
            map.put(new Texture("images/button3.png"), () -> {
                // Handle button click event here
                shop.purchase(index);
            });
        }
      
        
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
