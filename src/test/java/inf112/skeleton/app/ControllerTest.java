package inf112.skeleton.app;

import com.badlogic.gdx.Application;
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
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ControllerTest {
    private GameStateManager gsm;
    private Controller controller;
    private Input mockInput;
    
    @BeforeEach
    public void setUp() {
        gsm = mock(GameStateManager.class);
        controller = new Controller(gsm);
        mockInput = mock(Input.class);
        Gdx.input = mockInput;
        Gdx.app = mock(Application.class);
    }

    @Test
    public void testHandleInputEscShouldExit() {
        when(mockInput.isKeyJustPressed(Keys.ESCAPE)).thenReturn(true);
        controller.handleInput(null); // Null state to test only the ESC functionality
        
        verify(mockInput).isKeyJustPressed(Keys.ESCAPE);
        verify(Gdx.app).exit(); // Verify that exit() was called
    }





    @Test
    public void testPlayerMovementInPlayState() {
        PlayState playState = mock(PlayState.class);
        Player player = mock(Player.class);
        when(playState.getPlayer()).thenReturn(player);
        
        when(mockInput.isKeyPressed(Keys.D)).thenReturn(false);
        when(mockInput.isKeyPressed(Keys.A)).thenReturn(true);
        controller.handleInput(playState);
    
        verify(player).playerDirection(-1); // Verifying this is called once correctly
        verify(player, times(1)).playerDirection(-1); // Explicitly stating once for clarity
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
