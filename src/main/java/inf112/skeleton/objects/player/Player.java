package inf112.skeleton.objects.player;

import static inf112.skeleton.helper.Constants.PPM;
import static inf112.skeleton.helper.MyContactListener.isOnContact;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;



public class Player extends GameEntity{
    private int jumpCounter;
    private int framesGrounded;
    private Texture playerTexture;
    private float slideForceMagnitude = 10.5f;
    private float fastFallSpeed = -10.0f;
   

    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 5f;
        this.jumpCounter = 0;
        
        playerTexture = new Texture("images/button.jpg");
        for (Fixture fixture : body.getFixtureList()) {
            fixture.setUserData("player");
        }
    }

    @Override
    public void update() {
        x = body.getPosition().x * PPM;
        y = body.getPosition().y * PPM;
        
        checkUserInput();

       

    }

    public float getx(){
        return x;
    }
    public float gety(){
        return y;
    }
    public float getWidth(){
        return width;
    }
    public float getHeight(){
        return height;
    }
    @Override
    public void render(SpriteBatch batch) {
/*         batch.begin();
        batch.draw(playerTexture, getx() - getWidth()/2, gety() - getHeight()/2, getWidth(), getHeight());
        batch.end(); */
    }
    
    private void checkUserInput(){

        if(!isOnContact && jumpCounter == 0){
            jumpCounter = 1;
        }

        if((body.getLinearVelocity().y == 0) && framesGrounded != 5){
            framesGrounded++;
            if(framesGrounded == 3){
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
    
    public void fastFall(){
        body.setLinearVelocity(velX*speed,fastFallSpeed);
        
    }
    public void slide() {     
/*         Vector2 slideDirection = new Vector2((float) -Math.sin(body.getAngle()), (float) Math.cos(body.getAngle()));                
        body.applyForceToCenter(slideDirection.scl(slideForceMagnitude), true);   */  
/*         float force = body.getMass() * 10;
        body.setLinearVelocity(body.getLinearVelocity().x, 0);
        body.applyLinearImpulse(new Vector2(1, force*3*-1), body.getPosition(), true); */
    }

    @Override
    public void direction(float x, float y) {

    }

    
    
}
