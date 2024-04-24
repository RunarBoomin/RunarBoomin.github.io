package inf112.skeleton.objects.player;

import static inf112.skeleton.helper.Constants.PPM;
import static inf112.skeleton.helper.MyContactListener.groundAngle;
import static inf112.skeleton.helper.MyContactListener.isOnContact;
import static inf112.skeleton.helper.MyContactListener.isOnSlope;

import org.w3c.dom.Text;

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
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import inf112.skeleton.helper.SoundPlayer;
import inf112.skeleton.helper.BodyHelperService;
import inf112.skeleton.states.DeathState;
import inf112.skeleton.states.GameStateManager;

public class Player extends GameEntity {
    private Texture player0;
    private Texture player1;
    private Texture player2;
    private Texture player3;
    private Texture player4;
    private boolean lastMovementLeft = false;
    private int jumpCounter;
    private int maxJumps;
    private int jumpForce;
    private int framesGrounded;
    // private Texture playerTexture;
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

    Vector3 worldCoordinates = new Vector3();
    private Texture weaponTexture;
    private long weaponOutStartTime = 0;
    int countertest = 0;

    public Player(float width, float height, Body body, OrthographicCamera camera) {
        super(width, height, body);
        this.speed = 5f;
        this.jumpCounter = 0;
        test = new Texture("images/title.png");
        this.camera = camera;
        this.wallet = 100;
        this.maxJumps = 2;
        this.jumpForce = 10;
    
        // Load the player texture
        player0 = new Texture("images/player0.png");
        player1 = new Texture("images/player1.png");
        player2 = new Texture("images/player2.png");
        player3 = new Texture("images/player3.png");
        player4 = new Texture("images/player4.png");

        // Load the weapon texture
        // weaponTexture = new Texture("images/weapon.png");

        // playerTexture = new Texture("images/button.jpg");
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
        
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            float mouseXx = Gdx.input.getX();
            float mouseYy = Gdx.input.getY();
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
                SoundPlayer.playRandomSound("src\\main\\resources\\Sounds\\Misc\\SwordAttack");
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
            SoundPlayer.playRandomSound("src\\main\\resources\\Sounds\\Hero\\Death");
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
        // Calculate the position to center the texture on the player model
        float textureX = x + (width - 64) / 2; // Assuming the player model width is 32
        float textureY = y + (height - 64) / 2; // Assuming the player model height is 32

        // Determine if the player is moving to the left or right
        boolean isMovingLeft = velX < 0; // Assuming velX represents the player's velocity in the X direction
        boolean isMovingRight = velX > 0;

        // Update lastMovementLeft based on the current movement direction
        if (isMovingLeft) {
        lastMovementLeft = true;
        } else if (isMovingRight) {
        lastMovementLeft = false;
        }

        // Calculate the angle between the character's position and the clicked position
    float angle = MathUtils.atan2(worldCoordinates.y - y, worldCoordinates.x - x);

    // Determine if the character should face left or right based on the angle
    boolean isFacingLeft = (angle > MathUtils.PI / 2 || angle < -MathUtils.PI / 2);
        
        batch.begin();
            if(weaponOut == false){
            if (lastMovementLeft) {
                batch.draw(player0, textureX + 65, textureY-42, -150,100); // Flip the texture horizontally
                } else {
                batch.draw(player0, textureX - 65, textureY - 42, 150,100); // Normal drawing
                }
        }
        
        if(weaponOut==true){
            long elapsedTimeSinceWeaponOut = TimeUtils.timeSinceNanos(weaponOutStartTime);
            if (elapsedTimeSinceWeaponOut < 120000000) {
                if (isFacingLeft ) {
                    batch.draw(player1, textureX +65, textureY-42, -150,100); // Flip the texture horizontally
                    } else {
                    batch.draw(player1, textureX - 65, textureY - 42, 150,100); // Normal drawing
                    }
            } else if (elapsedTimeSinceWeaponOut < 250000000) {
                if (isFacingLeft) {
                    batch.draw(player2, textureX + 65, textureY-42, -150,100); // Flip the texture horizontally
                    } else {
                    batch.draw(player2, textureX - 65, textureY - 42, 150,100); // Normal drawing
                    }
            } else if (elapsedTimeSinceWeaponOut < 350000000) {
                if (isFacingLeft) {
                    batch.draw(player3, textureX + 65, textureY-42, -150,100); // Flip the texture horizontally
                    } else {
                    batch.draw(player3, textureX - 65, textureY - 42, 150,100); // Normal drawing
                    }
            } else 
                if (isFacingLeft) {
                    batch.draw(player4, textureX +65, textureY-42, -150,100); // Flip the texture horizontally
                    } else {
                    batch.draw(player4, textureX - 65, textureY - 42, 150,100); // Normal drawing
                    }
                       
        }
        
        batch.end();
        
    }

    


    public void createWeapon(){
        
        weaponOut = true;
        weaponOutStartTime = TimeUtils.nanoTime();
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
        }, 0.5f); // 2 seconds delay
        

       
    }

    private void setWeapon(Body weapon){
        this.weapon = weapon;
    }

    public void setWorld(World world){
        this.world = world;
    }
    private void checkUserInput() {
        
        if (velX != 0 && isOnContact && framesGrounded%30 == 0 && framesGrounded>5) {
            countertest ++;
            System.out.println(countertest);
            SoundPlayer.playRandomSound("src\\main\\resources\\Sounds\\Misc\\Step");
        } 

        if (isOnSlope && jumpCounter == 0) {
            jumpCounter = 1;
        }

        if ((body.getLinearVelocity().y == 0) ) {
            framesGrounded++;
            if(framesGrounded == 5){
                if (framesGrounded == 5) {
                    jumpCounter = 0;
                    
                }
            }
        }

        if (!isOnContact && jumpCounter == 0) {
            jumpCounter = 1;
            framesGrounded = 0;
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
            SoundPlayer.playRandomSound("src\\main\\resources\\Sounds\\Misc\\Jump");
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
