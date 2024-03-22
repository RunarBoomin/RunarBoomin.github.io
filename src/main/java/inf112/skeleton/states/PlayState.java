package inf112.skeleton.states;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector2;





import static inf112.skeleton.helper.Constants.PPM;
import static inf112.skeleton.helper.Constants.width;

import java.util.ArrayList;
import java.util.List;

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
import inf112.skeleton.objects.player.Enemy;
import inf112.skeleton.objects.player.Enemy2;
import inf112.skeleton.objects.player.GameEntity;
import inf112.skeleton.objects.player.Player;
import inf112.skeleton.objects.player.ShopKeeper;



public class PlayState extends State{
    
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
    private Texture heartTexture;

    private List<ShopKeeper> shops;

 
    List<GameEntity> enemies = new ArrayList<>();
    public PlayState(GameStateManager gsm){
        super(gsm);
        filename = "maps/map3.tmx";
        this.batch = new SpriteBatch();
        this.world = new World(new Vector2(0,-5f), false);
        this.box2dDebugRenderer = new Box2DDebugRenderer();
        playerTexture = new Texture("images/background.jpg");
        this.shops = new ArrayList<>();
        
        createMap(filename);
        this.world.setContactListener(new MyContactListener(gsm));
        font = new BitmapFont();

        player.setGsm(gsm);
        player.setWorld(world);

        
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

        for (GameEntity enemy : enemies) {
            enemy.update();
            if((cam.position.y + height/2) +30 > enemy.gety()){
                enemy.direction(player.getx(), player.gety());
            }
        }
    
        
        cameraUpdate();
        batch.setProjectionMatrix(cam.combined);
        orthogonalTiledMapRenderer.setView(cam);

        playerOnScreen();
    }

    public void playerOnScreen(){
        if((player.getBody().getPosition().y * PPM) < cam.position.y - (height/2) - 50){
            gsm.set(new MenuState(gsm));
        }
        
    }
    @Override
    public void render(SpriteBatch sb) {
        orthogonalTiledMapRenderer.render();
        
        sb.begin();
        renderHearts(sb);
        font.draw(sb, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, height-10 );
        sb.end();
        
       
        /* batch.draw(playerTexture, player.getx() - player.getWidth()/2, player.gety() - player.getHeight()/2, player.getWidth(), player.getHeight()); */
        player.render(batch);
        
        // render objects
        
        
        box2dDebugRenderer.render(world, cam.combined.scl(PPM));


        
    }

    public void renderHearts(SpriteBatch sb){
        
       
        int gap = 0;
        heartTexture = new Texture("images/heart.png");
        for (int i = 0; i < player.getHearts(); i++) {
            
            sb.draw(heartTexture, 100+gap, height-100, heartTexture.getWidth()/4, heartTexture.getHeight()/4);
            
            gap+= heartTexture.getWidth()/4;
        }
        
        
    }
 
    
    private void cameraUpdate(){
        Vector3 position = cam.position;
        position.x = Math.round(player.getBody().getPosition().x * PPM * 10) / 10f;

        /* position.y = Math.round(player.getBody().getPosition().y * PPM * 10) / 10f; */
        if(position.y < Math.round((player.getBody().getPosition().y - 3) * PPM * 10) / 10f){
            position.y = Math.round((player.getBody().getPosition().y - 3) * PPM * 10) / 10f;
        }
        
        cam.position.set(position);
        cam.update();
    }

   

    

    
    
    public World getWorld(){
        return world;
    }

    public OrthographicCamera getCamera(){
        return cam;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }

    public void addEnemy(GameEntity enemy){
        enemies.add(enemy);
    }


  
    

    @Override
    public void handleInput() {

    }

    public void addShop(ShopKeeper shopKeeper) {
        shops.add(shopKeeper);
    }

    public List<ShopKeeper> getShops() {
        return shops;
    }



}

