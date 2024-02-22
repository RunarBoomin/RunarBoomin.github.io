package inf112.skeleton.app;

import inf112.skeleton.helper.GameState;

public class GameStateRenderer {
    private GameScreen gameScreen;
    private GameState gameState;

    public GameStateRenderer(GameScreen gameScreen, GameState gameState) {
        this.gameScreen = gameScreen;
        this.gameState = gameState;
    }

    public void render() {
        switch (gameState) {
            case MAIN_MENU:
                renderMainMenu();
                break;
            case PLAYING:
                renderGame();
                break;
            case PAUSED:
                renderPaused();
                break;
            case GAME_OVER:
                renderGameOver();
                break;
            case LEVEL_COMPLETE:
                renderLevelComplete();
                break;
            case SETTINGS:
                renderSettings();
                break;
            default:
                // Handle undefined state
                break;
        }
    }

    private void renderMainMenu() {
        // Render main menu
        gameScreen.renderMenu();
    }

    private void renderGame() {
        // Render game
        gameScreen.renderGame();
        
    }

    private void renderPaused() {
        // Render paused screen
    
    }

    private void renderGameOver() {
        // Render game over screen
        
    }

    private void renderLevelComplete() {
        // Render level complete screen
        
    }

    private void renderSettings() {
        // Render settings screen
        
    }
}
