package inf112.skeleton.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Contact;

import inf112.skeleton.helper.MyContactListener;
import inf112.skeleton.states.GameStateManager;
import inf112.skeleton.states.PlayState;

public class ContactTester {
    private GameStateManager gsm;
    private MyContactListener contactListener;
    private PlayState playState;
    private Contact contact;

    @BeforeEach
    void setUp() {
        // Initialize tests
        contactListener = new MyContactListener(gsm);
        gsm.getState().equals(playState);
    }

    @Test
    void beginContact_PlayerGround_ShouldSetIsOnContactToTrue() {

        Texture Obj1 =
        Object Obj2 = contact.getFixtureB().getUserData();
        
        assertTrue(Obj1.equals(Obj2)); // Contact

        // Simulate a contact between player and ground
        contactListener.beginContact(contact);
        
        // Assert that isOnContact is set to true
        assertTrue(MyContactListener.isOnContact);
    }

    @Test
    void beginContact_PlayerSlope_ShouldSetIsOnSlopeToTrue() {
        // Simulate a contact between player and slope
        contactListener.beginContact(/* provide appropriate contact parameters */);
        
        // Assert that isOnSlope is set to true
        assertTrue(MyContactListener.isOnSlope);
    }

    @Test
    void endContact_PlayerGround_ShouldSetIsOnContactToFalse() {
        // Simulate end of contact between player and ground
        contactListener.endContact(/* provide appropriate contact parameters */);
        
        // Assert that isOnContact is set to false
        assertFalse(MyContactListener.isOnContact);
    }

    @Test
    void endContact_PlayerSlope_ShouldSetIsOnSlopeToFalse() {
        // Simulate end of contact between player and slope
        contactListener.endContact(/* provide appropriate contact parameters */);
        
        // Assert that isOnSlope is set to false
        assertFalse(MyContactListener.isOnSlope);
    }

    // Additional test cases can be added to cover other scenarios and methods
}