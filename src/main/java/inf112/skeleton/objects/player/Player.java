package inf112.skeleton.objects.player;

import static inf112.skeleton.helper.Constants.PPM;
import static inf112.skeleton.helper.MyContactListener.isOnContact;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;



public class Player extends GameEntity{
    private int jumpCounter;
    private int framesGrounded;
    private Texture playerTexture;

    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 4f;
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
        batch.draw(playerTexture, this.getx() - this.getWidth()/2, this.gety() - this.getHeight()/2, this.getWidth(), this .getHeight());
        batch.end(); */
    }
    
    private void checkUserInput(){

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

    
    
}
