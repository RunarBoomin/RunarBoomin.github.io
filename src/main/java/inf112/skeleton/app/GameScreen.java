package inf112.skeleton.app;


import static inf112.skeleton.helper.Constants.PPM;
import static inf112.skeleton.helper.Constants.width;
import static inf112.skeleton.helper.Constants.height;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.helper.GameState;
import inf112.skeleton.helper.MyContactListener;
import inf112.skeleton.helper.TileMapHelper;
import inf112.skeleton.objects.player.Player;

public class GameScreen extends ScreenAdapter{
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private World world;
    private Box2DDebugRenderer box2dDebugRenderer;

    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TileMapHelper tileMapHelper;

    
    BitmapFont font;
    int fpsCounter;
    private String filename = "maps/map2.tmx";

    private GameStateRenderer gameStateRenderer;
    public GameState currentState;
    // game objects
    private Player player;
    public GameScreen(OrthographicCamera camera){
        this.currentState = GameState.PLAYING;
        this.camera = camera;
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0,-5f), false);
        this.box2dDebugRenderer = new Box2DDebugRenderer();
        this.gameStateRenderer = new GameStateRenderer(this,currentState);
        
        createMap(filename);
        this.player.setGameScreen(this);
        this.world.setContactListener(new MyContactListener());
        font = new BitmapFont();
    } 

    public void createMap(String filename){
        this.tileMapHelper = new TileMapHelper(this,filename);
        this.orthogonalTiledMapRenderer = tileMapHelper.setupMap();
    }
    public void update(){
        
        world.step(1/60f, 6, 2);
        cameraUpdate();
        batch.setProjectionMatrix(camera.combined);

        orthogonalTiledMapRenderer.setView(camera);
        player.update();

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
    }

    private void cameraUpdate(){
        Vector3 position = camera.position;
        position.x = Math.round(player.getBody().getPosition().x * PPM * 10) / 10f;
        position.y = Math.round(player.getBody().getPosition().y * PPM * 10) / 10f;
        camera.position.set(position);
        camera.update();
    }
    @Override
    public void render(float delta){
        
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.graphics.setForegroundFPS(120);

        gameStateRenderer.render();
        
        
    }

    public void renderGame(){
        update();
        orthogonalTiledMapRenderer.render();
        batch.begin();
        // render objects
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), camera.position.x - (width/2 ) + 50, camera.position.y + (height/2) - 50);
        batch.end();
        box2dDebugRenderer.render(world, camera.combined.scl(PPM));
    }

    public void renderMenu() {
    batch.begin();
    // Render cute menu elements
    Texture cuteBackground = new Texture("maps/test.jpg");
    batch.draw(cuteBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    
    BitmapFont cuteFont = new BitmapFont();
    cuteFont.setColor(Color.PINK);
    GlyphLayout layout = new GlyphLayout(cuteFont, "Welcome to the Cute Menu!");
    float textWidth = layout.width;
    float textHeight = layout.height;
    cuteFont.draw(batch, layout, (Gdx.graphics.getWidth() - textWidth) / 2, (Gdx.graphics.getHeight() + textHeight) / 2);
    
    Texture cuteButton = new Texture("obligator.png");
    batch.draw(cuteButton, (Gdx.graphics.getWidth() - cuteButton.getWidth()) / 2, (Gdx.graphics.getHeight() - cuteButton.getHeight()) / 2);
    batch.end();
}
    public World getWorld(){
        return world;
    }

    public void setPlayer(Player player){
        this.player = player;
    }
}
