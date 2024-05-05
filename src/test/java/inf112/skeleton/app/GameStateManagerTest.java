package inf112.skeleton.app;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import inf112.skeleton.states.GameStateManager;
import inf112.skeleton.states.State;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameStateManagerTest {
    private GameStateManager gsm;
    private State stateMock1;
    private State stateMock2;
    private SpriteBatch spriteBatchMock;

    @BeforeEach
    public void setUp() {
        gsm = new GameStateManager();
        stateMock1 = mock(State.class);
        stateMock2 = mock(State.class);
        spriteBatchMock = mock(SpriteBatch.class);
    }

    @Test
    public void testPushState() {
        gsm.push(stateMock1);
        assertSame(stateMock1, gsm.getState());
    }

    @Test
    public void testPopState() {
        gsm.push(stateMock1);
        gsm.push(stateMock2);
        gsm.pop();
        assertSame(stateMock1, gsm.getState());
    }

    @Test
    public void testSetState() {
        gsm.push(stateMock1);
        gsm.set(stateMock2);
        assertSame(stateMock2, gsm.getState());
    }

    @Test
    public void testUpdate() {
        gsm.push(stateMock1);
        float dt = 0.16f;
        gsm.update(dt);
        verify(stateMock1, times(1)).update(dt);
    }

    @Test
    public void testRender() {
        gsm.push(stateMock1);
        gsm.render(spriteBatchMock);
        verify(stateMock1, times(1)).render(spriteBatchMock);
    }

    @Test
    public void testLastState() {
        gsm.push(stateMock1);
        gsm.push(stateMock2);
        assertSame(stateMock1, gsm.lastState());
    }

    @Test
    public void testLastStateWhenSingleState() {
        gsm.push(stateMock1);
        assertNull(gsm.lastState());
    }

    @Test
    public void testLastStateWhenEmpty() {
        assertNull(gsm.lastState());
    }
}
