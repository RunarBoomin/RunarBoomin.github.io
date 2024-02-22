package inf112.skeleton.objects.player;

import static inf112.skeleton.helper.Constants.PPM;
import static inf112.skeleton.helper.MyContactListener.isOnContact;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import inf112.skeleton.app.GameScreen;
import inf112.skeleton.helper.GameState;

public class Player extends GameEntity{
    private int jumpCounter;
    private int framesGrounded;
    private GameScreen gameScreen;
    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 4f;
        this.jumpCounter = 0;
    }

    @Override
    public void update() {
        x = body.getPosition().x * PPM;
        y = body.getPosition().y * PPM;

        checkUserInput();
        
    }

    @Override
    public void render(SpriteBatch batch) {
        
    }
    
    private void checkUserInput(){
        velX = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            velX = 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            velX = -1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.E)){
            gameScreen.currentState = GameState.PLAYING;
        }

        
        if(!isOnContact && jumpCounter == 0){
            jumpCounter = 1;
        }

        if((body.getLinearVelocity().y == 0) && framesGrounded != 5){
            framesGrounded++;
            if(framesGrounded == 5){
                jumpCounter = 0;
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.W) && jumpCounter < 2){
            float force = body.getMass() * 10;
            body.setLinearVelocity(body.getLinearVelocity().x, 0);
            body.applyLinearImpulse(new Vector2(0, force), body.getPosition(), true);
            framesGrounded = 0;
            jumpCounter++;
        }
        
        body.setLinearVelocity(velX * speed, body.getLinearVelocity().y < 18 ? body.getLinearVelocity().y : 18);
    }

    public void setGameScreen(GameScreen gameScreen){
        this.gameScreen = gameScreen;
    }
    
}
