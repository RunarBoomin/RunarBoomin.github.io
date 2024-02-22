package inf112.skeleton.helper;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import inf112.skeleton.objects.player.Player;

// Define a contact listener class
public class MyContactListener implements ContactListener {
    public static boolean isOnContact;
    
    
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        fixtureA.setUserData("ground");
        fixtureB.setUserData("player");
        
        
        // Check if fixtureA or fixtureB is the player's fixture
        if (fixtureA.getUserData().equals("player") || fixtureB.getUserData().equals("player")) {
            // Check if the other fixture is the ground
            if (fixtureA.getUserData().equals("ground") || fixtureB.getUserData().equals("ground")) {
                isOnContact = true;
            }
        }  
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        fixtureA.setUserData("ground");
        fixtureB.setUserData("player");
        
        // Check if fixtureA or fixtureB is the player's fixture
        if (fixtureA.getUserData().equals("player") || fixtureB.getUserData().equals("player")) {
            // Check if the other fixture is the groundw
            if (fixtureA.getUserData().equals("ground") || fixtureB.getUserData().equals("ground")) {
                isOnContact = false;
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





