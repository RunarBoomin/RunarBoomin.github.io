package inf112.skeleton.states;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector2;





import static inf112.skeleton.helper.Constants.PPM;
import static inf112.skeleton.helper.Constants.width;
import static inf112.skeleton.helper.Constants.height;



import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;


import inf112.skeleton.helper.MyContactListener;
import inf112.skeleton.helper.TileMapHelper;
import inf112.skeleton.objects.player.Player;
import inf112.skeleton.objects.player.ShopKeeper;



public class PlayState extends State{
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private World world;
    private Box2DDebugRenderer box2dDebugRenderer;

    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private TileMapHelper tileMapHelper;

    

    
    BitmapFont font;
    int fpsCounter;
    private String filename;

    private Texture playerTexture;

    // game objects
    private Player player;
    private ShopKeeper keeper;

    public PlayState(GameStateManager gsm){
        super(gsm);
        filename = "maps/map3.tmx";
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0,-5f), false);
        this.box2dDebugRenderer = new Box2DDebugRenderer();
        playerTexture = new Texture("images/background.jpg");
        
        createMap(filename);
        this.world.setContactListener(new MyContactListener(gsm));
        font = new BitmapFont();

        
    } 

    public void createMap(String filename){
        this.tileMapHelper = new TileMapHelper(this,filename);
        this.orthogonalTiledMapRenderer = tileMapHelper.setupMap();
    }

    @Override
    public void update(float dt) {
        handleInput();
        world.step(1/60f, 6, 2);
        player.update();
        

        cameraUpdate();
        
        batch.setProjectionMatrix(cam.combined);
        orthogonalTiledMapRenderer.setView(cam);
    }

    @Override
    public void render(SpriteBatch sb) {
        
        
        
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.graphics.setForegroundFPS(120);

        orthogonalTiledMapRenderer.render();
        batch.begin();

        //batch.draw(playerTexture, player.getx() - player.getWidth()/2, player.gety() - player.getHeight()/2, player.getWidth(), player.getHeight());
        // render objects
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), cam.position.x - (width/2 ) + 50, cam.position.y + (height/2) - 50);
        batch.end();
        box2dDebugRenderer.render(world, cam.combined.scl(PPM));
        
    }

 
    
    private void cameraUpdate(){
        Vector3 position = cam.position;
        position.x = Math.round(player.getBody().getPosition().x * PPM * 10) / 10f;
        position.y = Math.round(player.getBody().getPosition().y * PPM * 10) / 10f;
        cam.position.set(position);
        
        cam.update();
    }

   

    

    
    
    public World getWorld(){
        return world;
    }

    public OrthographicCamera getCamera(){
        return camera;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }

    public void setKeeper(ShopKeeper keeper) {
        this.keeper = keeper;
    }

    public ShopKeeper getKeeper() {
        return keeper;
    }

  
    

    @Override
    public void handleInput() {
/*         if(Gdx.input.isKeyJustPressed(Input.Keys.E)){
            gsm.push(new PauseState(gsm));
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.F)){
            gsm.push(new MenuState(gsm));
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        } */
    }



}

