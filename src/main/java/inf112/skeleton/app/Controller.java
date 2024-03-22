package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import inf112.skeleton.objects.player.ShopKeeper;
import inf112.skeleton.states.GameStateManager;
import inf112.skeleton.states.MenuState;
import inf112.skeleton.states.PauseState;
import inf112.skeleton.states.PlayState;
import inf112.skeleton.states.ShopState;
import inf112.skeleton.states.State;

public class Controller {
    private State state;
    private GameStateManager gsm;
    public Controller(GameStateManager gsm){
        this.gsm = gsm;
    }

    public void handleInput(State state){
        this.state = state;
        // Global ---------------------------------------
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        } 

        // Menu ---------------------------------------
        if (state instanceof MenuState) {
            
        }

        // Playing ---------------------------------------
        if (state instanceof PlayState) {
            if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
                gsm.push(new PauseState(gsm));
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.F)){
                gsm.push(new MenuState(gsm));
            }

            // Player input
            ((PlayState)state).getPlayer().velX = 0;
           
            if(Gdx.input.isKeyPressed(Input.Keys.D)){
                ((PlayState)state).getPlayer().velX = ((PlayState)state).getPlayer().getAccel();
            }
            if(Gdx.input.isKeyPressed(Input.Keys.A)){
                ((PlayState)state).getPlayer().velX = -((PlayState)state).getPlayer().getAccel();
             
            }

            if(Gdx.input.isKeyPressed(Input.Keys.S)){
                ((PlayState)state).getPlayer().fastFall();
             
            }

            if(Gdx.input.isKeyJustPressed(Input.Keys.Q)){
                Vector2 playerPos = ((PlayState)state).getPlayer().getBody().getPosition();
                for (ShopKeeper keeper : ((PlayState)state).getShops()) {
                    Vector2 keeperPos = keeper.getBody().getPosition();
                    if ((playerPos.x - keeperPos.x) + (playerPos.y - keeperPos.y) < 5) {
                        gsm.push(new ShopState(gsm, ((PlayState)state).getPlayer(), keeper.getShop()));
                    }
                }

            }
        }
        // Pause ---------------------------------------
        if (state instanceof PauseState) {
            if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
                gsm.pop();
            } 
        }


     
    }
}
