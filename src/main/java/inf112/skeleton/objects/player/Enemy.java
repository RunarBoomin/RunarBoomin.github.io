package inf112.skeleton.objects.player;

import static inf112.skeleton.helper.Constants.PPM;
import static inf112.skeleton.helper.MyContactListener.enemyHurt;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

public class Enemy extends GameEntity {

    private Texture enemyT;
    private long lastJumpTime = 0;
    private long lastHitTime = 0;
    private World world;
    /* private int life = 2; */
    private float playerPos;
    private float playerPosy;
    private static final long hitCD = 1000;
    private static final long JUMP_COOLDOWN = 3000; // 3 seconds in milliseconds

    private boolean canAct = false;

    private boolean dead = false;
    public Enemy(float width, float height, Body body, World world) {
        super(width, height, body);
        this.speed = 2f;
        this.world = world;
        
        // Load the enemy texture
        enemyT = new Texture("images/enemy.png");
        for (Fixture fixture : body.getFixtureList()) {
            fixture.setUserData("enemy");
        }
    }

    @Override
    public void update() {   
        x = body.getPosition().x * PPM;
        y = body.getPosition().y * PPM;
    
        
       
       
        body.setLinearVelocity(velX * speed, body.getLinearVelocity().y < 18 ? body.getLinearVelocity().y : 18);
        checkHP();
    }

    private void checkHP(){
        for (Fixture fixture : body.getFixtureList()) {

            if(fixture == enemyHurt){
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastHitTime >= hitCD) {
                    knockBack(x, y, playerPos, playerPosy);
                    life -= 1;
                    lastHitTime = currentTime;
                }
                

            }
            if(life == 0 && dead == false){
                world.destroyBody(this.body);
                dead = true;
            }
        }
    }


    public void jumping(){

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastJumpTime >= JUMP_COOLDOWN) {
            // Perform jumping action
            float force = body.getMass() * 7;
            body.setLinearVelocity(body.getLinearVelocity().x, 0);
            body.applyLinearImpulse(new Vector2(0, force), body.getPosition(), true);
            // Update last jump time
            lastJumpTime = currentTime;
        }
    }


    public void direction(float playerPos, float playerPosy){
        this.playerPos = playerPos;
        this.playerPosy = playerPosy;
        if(canAct){

            if(playerPos < this.x){
                velX = -1;
            }
            
    
            if(playerPos > this.x){
                velX = 1;
            }
    
            if(playerPosy > this.y+10){
               jumping();
                
            }
        }

        if(this.y <= playerPosy+40){
            canAct = true;
        }

    }

    public float getx(){
        return x;
    }

    public float gety(){
        return y;
    }
    @Override
    public void render(SpriteBatch batch) {
        // Calculate the position to center the texture on the player model
        float textureX = x ; // Assuming the player model width is 32
        float textureY = y ; // Assuming the player model height is 32

        batch.begin();
        // batch.draw(enemyT, textureX-42, textureY-40, 80, 75); // Normal drawing
        // batch.end();
        if (velX == -1) {
            batch.draw(enemyT, textureX+42, textureY-40, -80, 75); // Normal drawing // Flip the texture horizontally
            } else {
                batch.draw(enemyT, textureX-42, textureY-40, 80, 75); // Normal drawing
            }
        batch.end();
    }
    
}
