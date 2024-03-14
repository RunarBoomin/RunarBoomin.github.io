package inf112.skeleton.objects.player;

import static inf112.skeleton.helper.Constants.PPM;
import static inf112.skeleton.helper.MyContactListener.isOnContact;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

import inf112.skeleton.states.PlayState;

public class Enemy extends GameEntity {

    private int jumpCounter;
    private int framesGrounded;
    private long lastJumpTime = 0;
    private static final long JUMP_COOLDOWN = 3000; // 3 seconds in milliseconds
    public Enemy(float width, float height, Body body) {
        super(width, height, body);
        this.speed = 2f;
        this.jumpCounter = 0;
        for (Fixture fixture : body.getFixtureList()) {
            fixture.setUserData("enemy");
        }
    }

    @Override
    public void update() {   
        x = body.getPosition().x * PPM;
        y = body.getPosition().y * PPM;
       
        
        body.setLinearVelocity(velX * speed, body.getLinearVelocity().y < 18 ? body.getLinearVelocity().y : 18);
    }

    public void jumping(){

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastJumpTime >= JUMP_COOLDOWN) {
            // Perform jumping action
            float force = body.getMass() * 10;
            body.setLinearVelocity(body.getLinearVelocity().x, 0);
            body.applyLinearImpulse(new Vector2(0, force), body.getPosition(), true);
            // Update last jump time
            lastJumpTime = currentTime;
        }

       
        
        
    }


    public void direction(float playerPos, float playerPosy){
        if(playerPos < this.x){
            velX = -1;
        }
        

        if(playerPos > this.x){
            velX = 1;
        }

        if(playerPosy > this.y+10){
           jumping();
            
        }


        

/*         if(playerPosy> this.y){
            velX = 1;
        } */
        
    }
    @Override
    public void render(SpriteBatch batch) {

    }
    
}
