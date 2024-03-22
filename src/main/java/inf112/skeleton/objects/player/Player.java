package inf112.skeleton.objects.player;

import static inf112.skeleton.helper.Constants.PPM;
import static inf112.skeleton.helper.MyContactListener.groundAngle;
import static inf112.skeleton.helper.MyContactListener.isOnContact;
import static inf112.skeleton.helper.MyContactListener.isOnSlope;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;

import inf112.skeleton.helper.BodyHelperService;
import inf112.skeleton.states.DeathState;
import inf112.skeleton.states.GameStateManager;

public class Player extends GameEntity {
    private int jumpCounter;
    private int maxJumps;
    private int jumpForce;
    private int framesGrounded;
    private Texture playerTexture;
    private int wallet;
   
    private float fastFallSpeed = -10.0f;
   
    private float slideForceMagnitude = 1.0f;
    private int lifes = 3;
    private long lastJumpTime = 0;
    private static final long JUMP_COOLDOWN = 3000; // 3 seconds in milliseconds

    private World world;

    private int playerDirection = 1;
   
    private GameStateManager gsm;
    private Body weapon;
    private boolean weaponOut;
    private boolean attacking = false;
    private float attackSpeed = 1f;

    private float mouseX;
    private float mouseY;

    private Texture test;

    private OrthographicCamera camera;

    Vector3 worldCoordinates = new Vector3();;

    public Player(float width, float height, Body body, OrthographicCamera camera) {
        super(width, height, body);
        this.speed = 5f;
        this.jumpCounter = 0;
        test = new Texture("images/title.png");
        this.camera = camera;
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

        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.input.getY();
        if(camera != null){
            worldCoordinates = camera.unproject(new Vector3(mouseX, mouseY, 0));
        }
        
        


        checkUserInput();
        checkLife();

        if(weaponOut){
            updateWeapon(worldCoordinates.x, worldCoordinates.y);
        }
        
    } 

    private void updateWeapon(float mouseX, float mouseY){

/*         Vector2 playerToMouse = new Vector2(mouseX - x, mouseY - y);
        float angle = playerToMouse.angleRad();
        weapon.setTransform(body.getPosition().x + 2f * playerDirection, body.getPosition().y, angle); */

                // Calculate the vector from the player's position to the mouse position
        Vector2 playerToMouse = new Vector2(mouseX - x, mouseY - y);
        
        // Normalize the vector (make it unit length)
        playerToMouse.nor();
        
        // Define the distance between the player and the weapon
        float weaponDistance = 2f; // Adjust as needed
        
        // Calculate the position of the weapon based on the player's position and direction towards the mouse
        float weaponX = body.getPosition().x + playerToMouse.x * weaponDistance;
        float weaponY = body.getPosition().y + playerToMouse.y * weaponDistance;
        
        // Calculate the angle between the player's position and the mouse position
        float angle = playerToMouse.angleRad();
        
        // Set the transform of the weapon
        weapon.setTransform(weaponX, weaponY, angle);
    }
    

/*     private void updateWeapon(float mouseX, float mouseY){
    float angle = MathUtils.atan2(mouseY - body.getPosition().y, mouseX - body.getPosition().x);
    weapon.setTransform(body.getPosition().x + 1.55f * MathUtils.cos(angle), 
                        body.getPosition().y + 1.55f * MathUtils.sin(angle), 
                        angle);
    } */

    public void attack(){
        if(!weaponOut){
            if(!attacking){
                createWeapon();
                attacking = true;
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        // Code to run after 2 seconds
                        attacking = false;
                    }
                }, attackSpeed); // 2 seconds delay
            }

            
        }


    }
    private void checkLife(){
        if(lifes == 0){
            gsm.push(new DeathState(gsm));
        }
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

    public void playerDirection(int direction){
        playerDirection = direction;
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



        batch.begin();
        //batch.draw(test, worldCoordinates.x, worldCoordinates.y, test.getWidth(), test.getHeight());
        batch.end();


  
   


    }


    public void createWeapon(){
        
        weaponOut = true;
        Body body = BodyHelperService.createBody(
            this.x + 100* playerDirection,
            this.y, 
            30, 
            10,
            false, 
            world
        ); 

        for (Fixture fixture : body.getFixtureList()) {
            fixture.setUserData("weapon");
        }
        
        setWeapon(body);

        weapon.setGravityScale(0);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                // Code to run after 2 seconds
                world.destroyBody(weapon);
                weaponOut = false;
            }
        }, 0.1f); // 2 seconds delay
        

       
    }

    private void setWeapon(Body weapon){
        this.weapon = weapon;
    }

    public void setWorld(World world){
        this.world = world;
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

    public void removeLife(){
        lifes -=1;
        
        for (Fixture fixture : body.getFixtureList()) {
            fixture.setUserData("hurt");
        }
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                // Code to run after 2 seconds
                for (Fixture fixture : body.getFixtureList()) {
                    fixture.setUserData("player");
                }
            }
        }, 1); // 2 seconds delay


    }
    
    

    public void setGsm(GameStateManager gsm){
        this.gsm = gsm;
    }

    public int getHearts() {
        return lifes;

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
