package inf112.skeleton.helper;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import inf112.skeleton.objects.player.Player;
import inf112.skeleton.states.DeathState;
import inf112.skeleton.states.GameStateManager;
import inf112.skeleton.states.PlayState;
import inf112.skeleton.states.WinState;
import inf112.skeleton.states.MenuState;
import inf112.skeleton.states.PlayState;
import java.io.File;




// Define a contact listener class
public class MyContactListener implements ContactListener {
    public static boolean isOnContact;


    public static Fixture projFixture;
    public static Fixture enemyHurt;
    public static boolean isOnSlope;
    public static float groundAngle;
    private GameStateManager gsm;

    public MyContactListener(GameStateManager gsm) {
        this.gsm = gsm;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        // Check if fixtureA or fixtureB is the player's fixture
        if (fixtureA.getUserData().equals("player") || fixtureB.getUserData().equals("player")) {
            // Check if the other fixture is the ground
            if (fixtureA.getUserData().equals("ground") || fixtureB.getUserData().equals("ground")) {
                isOnContact = true;
            } 
        }

        // Check if fixtureA or fixtureB is the player's fixture
        if (fixtureA.getUserData().equals("player") || fixtureB.getUserData().equals("player")) {
            // Check if the other fixture is the ground
            if (fixtureA.getUserData().equals("slope") || fixtureB.getUserData().equals("slope")) {
                isOnSlope = true;
                getGroundAngle(contact);
            }
        }  

        if (fixtureA.getUserData().equals("player") || fixtureB.getUserData().equals("player")) {
            // Check if the other fixture is the ground
            if (fixtureA.getUserData().equals("enemy") || fixtureB.getUserData().equals("enemy")) {
                SoundPlayer.playRandomSound("src\\main\\resources\\Sounds\\Enemy\\Attack");
                ((PlayState)gsm.getState()).getPlayer().removeLife();
            }
        }

        if (fixtureA.getUserData().equals("player") || fixtureB.getUserData().equals("player")) {
            // Check if the other fixture is the ground
            if (fixtureA.getUserData().equals("goal") || fixtureB.getUserData().equals("goal")) {
                gsm.push(new WinState(gsm));
            }
        }

/*         testing(fixtureA, fixtureB, "player", "enemy", () -> {
            // Handle button click event here
            ((PlayState)gsm.getState()).getPlayer().removeLife();
        }); */
    

        if (fixtureA.getUserData().equals("projectile") || fixtureB.getUserData().equals("projectile")) {
            // Check if the other fixture is the ground
            if (fixtureA.getUserData().equals("ground") || fixtureB.getUserData().equals("ground")) {
                
                if(fixtureA.getUserData().equals("projectile")){
                    projFixture = fixtureA;
                }
                if(fixtureB.getUserData().equals("projectile")){
                    projFixture = fixtureB;
                }
            }
        } 

        if (fixtureA.getUserData().equals("projectile") || fixtureB.getUserData().equals("projectile")) {
            // Check if the other fixture is the ground
            if (fixtureA.getUserData().equals("player") || fixtureB.getUserData().equals("player")) {
                
                ((PlayState)gsm.getState()).getPlayer().removeLife();
                if(fixtureA.getUserData().equals("projectile")){
                    projFixture = fixtureA;
                    SoundPlayer.playRandomSound("src\\main\\resources\\Sounds\\Misc\\MissileExp");
                }
                if(fixtureB.getUserData().equals("projectile")){                   
                    projFixture = fixtureB;
                    SoundPlayer.playRandomSound("src\\main\\resources\\Sounds\\Misc\\MissileExp");
                }
               
            }
        } 


        if (fixtureA.getUserData().equals("weapon") || fixtureB.getUserData().equals("weapon")) {
            // Check if the other fixture is the ground
            if (fixtureA.getUserData().equals("projectile") || fixtureB.getUserData().equals("projectile")) {
                
                
                if(fixtureA.getUserData().equals("projectile")){
                    projFixture = fixtureA;
                    SoundPlayer.playRandomSound("src\\main\\resources\\Sounds\\Misc\\MissileExp");
                }
                if(fixtureB.getUserData().equals("projectile")){
                    projFixture = fixtureB;
                    SoundPlayer.playRandomSound("src\\main\\resources\\Sounds\\Misc\\MissileExp");
                }
               
            }
        } 

        testing(fixtureA, fixtureB, "weapon", "enemy", () -> {
            // Handle button click event here
            if(fixtureA.getUserData().equals("enemy")){
                enemyHurt = fixtureA;
            }
            if(fixtureB.getUserData().equals("enemy")){
                enemyHurt = fixtureB;
                
            }
            
        });
    }
    private void getGroundAngle(Contact contact) {
        // Get the fixtures involved in the contact
        Fixture playerFixture = contact.getFixtureA().getUserData().equals("player") ? contact.getFixtureA() : contact.getFixtureB();
        Fixture groundFixture = contact.getFixtureA().getUserData().equals("slope") ? contact.getFixtureA() : contact.getFixtureB();

        // Get the direction vector from the player body to the ground 
        Vector2 playerToGround = new Vector2(groundFixture.getBody().getPosition()).sub(playerFixture.getBody().getPosition());

        // Calculate the ground angle
        groundAngle = playerToGround.angleRad();
        
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        /*
         * fixtureA.setUserData("ground");
         * fixtureB.setUserData("player");
         */

        // Check if fixtureA or fixtureB is the player's fixture
        if (fixtureA.getUserData().equals("player") || fixtureB.getUserData().equals("player")) {
            // Check if the other fixture is the groundw
            if (fixtureA.getUserData().equals("ground") || fixtureB.getUserData().equals("ground")) {
                isOnContact = false;
            }
        }  

            if (fixtureA.getUserData().equals("slope") || fixtureB.getUserData().equals("slope")) {
                isOnSlope = false;
            }


            testing(fixtureA, fixtureB, "weapon", "enemy", () -> {
                // Handle button click event here
                if(fixtureA.getUserData().equals("enemy")){
                    enemyHurt = null;
                }
                if(fixtureB.getUserData().equals("enemy")){
                    enemyHurt = null;
                }
            });
        }
    

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private void testing(Fixture fixtureA, Fixture fixtureB,String obj1, String obj2, Runnable code){

        if (fixtureA.getUserData().equals(obj1) || fixtureB.getUserData().equals(obj1)) {
            // Check if the other fixture is the groundw
            if (fixtureA.getUserData().equals(obj2) || fixtureB.getUserData().equals(obj2)) {
                code.run();
            }
        }  


    }
}



