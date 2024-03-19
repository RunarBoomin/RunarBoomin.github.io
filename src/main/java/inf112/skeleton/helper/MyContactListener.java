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
import inf112.skeleton.states.MenuState;
import inf112.skeleton.states.PlayState;




// Define a contact listener class
public class MyContactListener implements ContactListener {
    public static boolean isOnContact;
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
                
                gsm.push(new DeathState(gsm));
            }
        }
    }

    private void getGroundAngle(Contact contact) {
        // Get the fixtures involved in the contact
        Fixture playerFixture = contact.getFixtureA().getUserData().equals("player") ? contact.getFixtureA() : contact.getFixtureB();
        Fixture groundFixture = contact.getFixtureA().getUserData().equals("slope") ? contact.getFixtureA() : contact.getFixtureB();

        // Get the direction vector from the player body to the ground 
        Vector2 playerToGround = new Vector2(groundFixture.getBody().getPosition()).sub(playerFixture.getBody().getPosition());

        // Calculate the ground angle
        groundAngle = playerToGround.angleRad();
        System.out.println(groundAngle);
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
            if (fixtureA.getUserData().equals("slope") || fixtureB.getUserData().equals("slope")) {
                isOnSlope = false;
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

}

// Somewhere in your initialization code, set up the contact listener
