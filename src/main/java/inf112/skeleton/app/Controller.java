package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import inf112.skeleton.states.GameStateManager;
import inf112.skeleton.states.MenuState;
import inf112.skeleton.states.PauseState;
import inf112.skeleton.states.PlayState;
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
                ((PlayState)state).getPlayer().velX = 1;
                ((PlayState)state).getPlayer().playerDirection(1);
            }
            if(Gdx.input.isKeyPressed(Input.Keys.A)){
                ((PlayState)state).getPlayer().velX = -1;
                ((PlayState)state).getPlayer().playerDirection(-1);
             
            }

            if(Gdx.input.isKeyPressed(Input.Keys.S)){
                ((PlayState)state).getPlayer().fastFall();
             
            }

            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                ((PlayState)state).getPlayer().attack();
             
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
