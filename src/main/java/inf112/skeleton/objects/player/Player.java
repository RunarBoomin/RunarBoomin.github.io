package inf112.skeleton.objects.player;

import static inf112.skeleton.helper.Constants.PPM;
import static inf112.skeleton.helper.MyContactListener.groundAngle;
import static inf112.skeleton.helper.MyContactListener.isOnContact;
import static inf112.skeleton.helper.MyContactListener.isOnSlope;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

public class Player extends GameEntity {
    private int jumpCounter;
    private int maxJumps;
    private int jumpForce;
    private int framesGrounded;
    private Texture playerTexture;
    private int wallet;
   
    private float fastFallSpeed = -10.0f;
   
    private float slideForceMagnitude = 1.0f;

    public Player(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 5f;
        this.jumpCounter = 0;
        this.wallet = 100;
        this.maxJumps = 2;
        this.jumpForce = 10;
    
        

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

    public float getx() {
        return x;
    }

    public float gety() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    @Override
    public void render(SpriteBatch batch) {
/*         batch.begin();
        batch.draw(playerTexture, getx() - getWidth()/2, gety() - getHeight()/2, getWidth(), getHeight());
        batch.end(); */
        /*
         * batch.begin();
         * batch.draw(playerTexture, this.getx() - this.getWidth()/2, this.gety() -
         * this.getHeight()/2, this.getWidth(), this .getHeight());
         * batch.end();
         */
    }

    private void checkUserInput() {

        if (isOnSlope && jumpCounter == 0) {
            jumpCounter = 1;
        }

        if ((body.getLinearVelocity().y == 0) && framesGrounded != 5) {
            framesGrounded++;
            if(framesGrounded == 5){
                if (framesGrounded == 5) {
                    jumpCounter = 0;
                    
                }
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.W) && jumpCounter < maxJumps){
            float force = body.getMass() * jumpForce;
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.W) && jumpCounter < 2) {
            float force = body.getMass() * 10;
            body.setLinearVelocity(body.getLinearVelocity().x, 0);
            body.applyLinearImpulse(new Vector2(0, force), body.getPosition(), true);
            framesGrounded = 0;
            jumpCounter++;
        }
        move();
    }
    
    public void fastFall(){
        body.setLinearVelocity(velX*speed,fastFallSpeed);
        
    }
     


    @Override
    public void direction(float x, float y) {

    }

    
    

    public void move() {
        // Check if the player is on a slope
        // Check if the slope angle is significant (not flat)
        if (isOnSlope && !isOnContact) {                                            // && Math.abs(groundAngle) < 0.3f does not work as intended
            // Calculate the direction of the slide force based on the slope angle
            Vector2 slideDirection = new Vector2((float) Math.sin(groundAngle), (float) Math.cos(groundAngle));
            // Apply the slide force to the player's body
            body.applyForceToCenter(slideDirection.scl(slideForceMagnitude), true);
        }
        else {
            body.setLinearVelocity(velX * speed, body.getLinearVelocity().y < 18 ? body.getLinearVelocity().y : 18);
        }
        
    }

    public int getWallet() {
        return wallet;
    }
    public boolean useWallet(int cost) {
        if (wallet >= cost) {
            wallet -= cost;
            return true;
        }
        else {
            return false;
        }
    }

    public int getJumpCount() {
        return maxJumps;
    }

    public void addJumps(int jumps) {
        maxJumps += jumps;
    }

    public void addJumpForce(int force) {
        jumpForce += force;
    }

    
}
