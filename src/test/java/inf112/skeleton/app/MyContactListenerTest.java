package inf112.skeleton.app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;

import inf112.skeleton.helper.MyContactListener;
import inf112.skeleton.states.GameStateManager;

class MyContactListenerTest {

    private MyContactListener contactListener;
    private GameStateManager mockGSM;
    private Contact mockContact;
    private Fixture mockFixtureA;
    private Fixture mockFixtureB;

    @BeforeEach
    void setUp() {
        mockGSM = Mockito.mock(GameStateManager.class);
        contactListener = new MyContactListener(mockGSM);
        mockContact = Mockito.mock(Contact.class);
        mockFixtureA = Mockito.mock(Fixture.class);
        mockFixtureB = Mockito.mock(Fixture.class);

        Mockito.when(mockContact.getFixtureA()).thenReturn(mockFixtureA);
        Mockito.when(mockContact.getFixtureB()).thenReturn(mockFixtureB);
    }

    @Test
    void testBeginContactWithPlayerAndGround() {
        Mockito.when(mockFixtureA.getUserData()).thenReturn("player");
        Mockito.when(mockFixtureB.getUserData()).thenReturn("ground");
        contactListener.beginContact(mockContact);
        assertTrue(MyContactListener.isOnContact);
    }

    @Test
    void testBeginContactWithPlayerAndSlope() {
        Mockito.when(mockFixtureA.getUserData()).thenReturn("player");
        Mockito.when(mockFixtureB.getUserData()).thenReturn("slope");
        contactListener.beginContact(mockContact);
        assertTrue(MyContactListener.isOnSlope);
    }

    @Test
    void testBeginContactProjectileWithGround() {
        Mockito.when(mockFixtureA.getUserData()).thenReturn("projectile");
        Mockito.when(mockFixtureB.getUserData()).thenReturn("ground");
        contactListener.beginContact(mockContact);
        assertSame(MyContactListener.projFixture, mockFixtureA);
    }

    @Test
    void testBeginContactWeaponWithEnemy() {
        Mockito.when(mockFixtureA.getUserData()).thenReturn("weapon");
        Mockito.when(mockFixtureB.getUserData()).thenReturn("enemy");
        contactListener.beginContact(mockContact);
        assertSame(MyContactListener.enemyHurt, mockFixtureB);
    }

    @Test
    void testEndContactPlayerWithGround() {
        Mockito.when(mockFixtureA.getUserData()).thenReturn("player");
        Mockito.when(mockFixtureB.getUserData()).thenReturn("ground");
        contactListener.endContact(mockContact);
        assertFalse(MyContactListener.isOnContact);
    }

    @Test
    void testEndContactPlayerWithSlope() {
        Mockito.when(mockFixtureA.getUserData()).thenReturn("player");
        Mockito.when(mockFixtureB.getUserData()).thenReturn("slope");
        contactListener.endContact(mockContact);
        assertFalse(MyContactListener.isOnSlope);
    }

    @Test
    void testEndContactWeaponWithEnemy() {
        Mockito.when(mockFixtureA.getUserData()).thenReturn("weapon");
        Mockito.when(mockFixtureB.getUserData()).thenReturn("enemy");
        contactListener.endContact(mockContact);
        assertNull(MyContactListener.enemyHurt);
    }

}