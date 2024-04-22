package inf112.skeleton.app;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.backends.headless.HeadlessApplication;

import inf112.skeleton.objects.player.Player;
import inf112.skeleton.states.GameStateManager;
import inf112.skeleton.states.MenuState;
import inf112.skeleton.states.PauseState;
import inf112.skeleton.states.PlayState;
import inf112.skeleton.states.State;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class ControllerTest {
    private GameStateManager gsm;
    private Controller controller;
    private Input mockInput;
    
    @Before
    public void setUp() {
        gsm = mock(GameStateManager.class);
        controller = new Controller(gsm);
        mockInput = mock(Input.class);
        Gdx.input = mockInput;
    }

    @Test
    public void testHandleInputEscShouldExit() {
        when(mockInput.isKeyJustPressed(Keys.ESCAPE)).thenReturn(true);
        controller.handleInput(null); // Null state to test only the ESC functionality
        Mockito.verify(mockInput).isKeyJustPressed(Keys.ESCAPE);
    }


    @Test
    public void testHandleInputPlayStatePause() {
        PlayState playState = mock(PlayState.class);
        when(mockInput.isKeyJustPressed(Keys.E)).thenReturn(true);
        controller.handleInput(playState);
        verify(gsm).push(any(PauseState.class));
    }

    @Test
    public void testHandleInputPlayStateMenu() {
        PlayState playState = mock(PlayState.class);
        when(mockInput.isKeyJustPressed(Keys.F)).thenReturn(true);
        controller.handleInput(playState);
        verify(gsm).push(any(MenuState.class));
    }

    @Test
    public void testPlayerMovementInPlayState() {
        PlayState playState = mock(PlayState.class);
        Player player = mock(Player.class);
        when(playState.getPlayer()).thenReturn(player);
        
        when(mockInput.isKeyPressed(Keys.D)).thenReturn(true);
        controller.handleInput(playState);
        verify(player).velX = 1;
        verify(player).playerDirection(1);

        when(mockInput.isKeyPressed(Keys.A)).thenReturn(true);
        controller.handleInput(playState);
        verify(player).velX = -1;
        verify(player, times(2)).playerDirection(-1);
    }

    @Test
    public void testPlayerFastFallInPlayState() {
        PlayState playState = mock(PlayState.class);
        Player player = mock(Player.class);
        when(playState.getPlayer()).thenReturn(player);
        
        when(mockInput.isKeyPressed(Keys.S)).thenReturn(true);
        controller.handleInput(playState);
        verify(player).fastFall();
    }

    @Test
    public void testPlayerAttackInPlayState() {
        PlayState playState = mock(PlayState.class);
        Player player = mock(Player.class);
        when(playState.getPlayer()).thenReturn(player);
        
        when(mockInput.isButtonPressed(Input.Buttons.LEFT)).thenReturn(true);
        controller.handleInput(playState);
        verify(player).attack();
    }

    @Test
    public void testHandleInputPauseState() {
        PauseState pauseState = mock(PauseState.class);
        when(mockInput.isKeyJustPressed(Keys.E)).thenReturn(true);
        controller.handleInput(pauseState);
        verify(gsm).pop();
    }

    @Test
    public void testHandleInput_UnpauseGame_WhenInPauseStateAndPressE() {
        // Arrange
        when(Gdx.input.isKeyJustPressed(Input.Keys.E)).thenReturn(true);
        PauseState pauseState = mock(PauseState.class);

        // Act
        controller.handleInput(pauseState);

        // Assert
        verify(gsm).pop(); // Verify that the game state was popped from the stack, resuming the game
    }
}
