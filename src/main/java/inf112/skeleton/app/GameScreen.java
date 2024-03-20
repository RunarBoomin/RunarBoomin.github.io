package inf112.skeleton.app;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.skeleton.states.GameStateManager;
import inf112.skeleton.states.MenuState;

public class GameScreen extends ScreenAdapter{
    private SpriteBatch batch;
    private GameStateManager gameStateManager;
    private Controller controller;
   
    public GameScreen(OrthographicCamera camera){
        this.batch = new SpriteBatch();
        this.gameStateManager = new GameStateManager();
        gameStateManager.push(new MenuState(gameStateManager));
        this.controller = new Controller(gameStateManager);
    } 

   
    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.graphics.setForegroundFPS(120);
        controller.handleInput(gameStateManager.getState());
		gameStateManager.update(Gdx.graphics.getDeltaTime());
        gameStateManager.render(batch);
        
    }
}
