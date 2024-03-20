package inf112.skeleton.objects.player;

import static inf112.skeleton.helper.Constants.PPM;
import static inf112.skeleton.helper.MyContactListener.projFixture;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.helper.BodyHelperService;

public class Enemy2 extends GameEntity {
    private World world;
    private boolean projectileAlive = false;
    private Body procBody;
    float bodyPosx;
    private float bodyPosy;
    float maxRotationSpeed = 1f; // Adjust as needed
    private float playerPosy;
    private float playerPosx;
    
    private int playerdirection;
    private boolean transformExecuted;
    public Enemy2(float width, float height, Body body, World world) {
        super(width, height, body);
        this.world = world;
        this.speed = 5f;
        body.setGravityScale(0);
        body.setType(BodyDef.BodyType.StaticBody);
        for (Fixture fixture : body.getFixtureList()) {
            fixture.setUserData("enemy2");
        }
    }

    @Override
    public void update() {

 
        x = body.getPosition().x * PPM;
        y = body.getPosition().y * PPM;

        if(y <= playerPosy){
            shootProjectile();
        }
        
        if(projectileAlive){
            checkProjectile();
        }
        
        
        
    }

   
    public void checkProjectile(){

        for (Fixture fixture : procBody.getFixtureList()) {
            if(fixture == projFixture){
                world.destroyBody(procBody);
                projectileAlive = false;
                transformExecuted = false;
            }
        }
    }

    public void shootProjectile(){
        if(projectileAlive == false){
            createProjectile();
        }

        else{
            projectileUpdate();
        }
        
    }

    public void projectileUpdate(){
        procBody.setGravityScale(0);
        
        /* procBody.setLinearVelocity(velX * speed, body.getLinearVelocity().y < 18 ? body.getLinearVelocity().y : 18); */
    }
    public void createProjectile(){
        projectileAlive = true;
        Body body = BodyHelperService.createBody(
            this.x + 50 * playerdirection,
            this.y, 
            30, 
            10,
            false, 
            world
            ); 

        for (Fixture fixture : body.getFixtureList()) {
            fixture.setUserData("projectile");
        }
        
        setProcBody(body);

       
    }
    
    public void setProcBody(Body body){
        this.procBody = body;
    }
    @Override
    public void render(SpriteBatch batch) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }
    
public void direction(float playerPosx, float playerPosy) {
    this.playerPosx = playerPosx;
    this.playerPosy = playerPosy;
    if(x<=playerPosx){
        this.playerdirection = 1;

    }
    if(x>playerPosx){
        this.playerdirection = -1;
   
    }
    if(projectileAlive){
    // Get the position of the projectile
    float bodyPosx = procBody.getPosition().x * PPM;
    float bodyPosy = procBody.getPosition().y * PPM;

    // Calculate the vector from the projectile to the player
    Vector2 directionToPlayer = new Vector2(playerPosx - bodyPosx, playerPosy - bodyPosy);

    // Calculate the angle in radians between the projectile and the player
    float angle = directionToPlayer.angleRad();
    setTransformOnce(procBody);

    

    

    // Get the current angle of the projectile
    float currentAngle = procBody.getAngle();

    // Calculate the difference between the current angle and the desired angle
    float angleDiff = angle - currentAngle;

    // Adjust the angle difference to be within the range of -PI to PI
    while (angleDiff > Math.PI) {
        angleDiff -= 2 * Math.PI;
    }
    while (angleDiff < -Math.PI) {
        angleDiff += 2 * Math.PI;
    }

    // Calculate the rotation speed based on the angle difference
    
    float rotationSpeed = Math.signum(angleDiff) * Math.min(maxRotationSpeed, Math.abs(angleDiff));

    // Apply the rotation speed to gradually rotate the projectile
    procBody.setAngularVelocity(rotationSpeed);

    // Calculate the velocity components using the normalized direction
    float velocityX = speed * MathUtils.cos(currentAngle);
    float velocityY = speed * MathUtils.sin(currentAngle);

    // Set the linear velocity of the projectile body
    procBody.setLinearVelocity(velocityX, velocityY);
    }

}

    public float gety(){
        return y;
    }

    

    public void setTransformOnce(Body body) {
        if (!transformExecuted) {
            float angle = playerdirection > 0 ? 0 : MathUtils.PI;
            body.setTransform(body.getPosition(), angle);
            transformExecuted = true;
        }
    }
}
