package inf112.skeleton.helper;

import static inf112.skeleton.helper.Constants.PPM;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.utils.Array;

import inf112.skeleton.objects.player.Player;
import inf112.skeleton.states.PlayState;
public class TileMapHelper {
    private TiledMap tiledMap;
    private PlayState playstate;
    private String filename;
    
    public TileMapHelper(PlayState playstate, String filename){
        this.playstate = playstate;
        this.filename = filename;
    }

    public OrthogonalTiledMapRenderer setupMap(){
        clearWorldBodies();
        tiledMap = new TmxMapLoader().load(filename);
        parseMapObjects(tiledMap.getLayers().get("objects").getObjects());
        return new OrthogonalTiledMapRenderer(tiledMap);
    }

    private void parseMapObjects(MapObjects mapObjects){
        for(MapObject mapObject : mapObjects){
            
            if(mapObject instanceof PolygonMapObject){
               
                createStaticBody((PolygonMapObject) mapObject, mapObject.getName());
            }
            
            if(mapObject instanceof RectangleMapObject){
                Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
                String rectangleName = mapObject.getName();

                if(rectangleName != null && rectangleName.equals("player")){
                    Body body = BodyHelperService.createBody(
                        rectangle.getX() + rectangle.getWidth() / 2,
                        rectangle.getY() + rectangle.getHeight()/2, 
                        rectangle.getWidth(), 
                        rectangle.getHeight(), 
                        false, 
                        playstate.getWorld()
                    );

                    playstate.setPlayer(new Player(rectangle.getWidth(), rectangle.getHeight(), body));
                }
            }
            
        }
    }

    private void createStaticBody(PolygonMapObject polygonMapObject, String userData){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = playstate.getWorld().createBody(bodyDef);
        Shape shape = createPolygonShape(polygonMapObject);
        Fixture fixture = body.createFixture(shape, 1000);
    
        // Set the user data for the fixture to "ground"
        fixture.setUserData(userData);
        
    }

    private Shape createPolygonShape(PolygonMapObject polygonMapObject){
        float[] vertices = polygonMapObject.getPolygon().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for(int i = 0; i < vertices.length / 2; i++){
            Vector2 current = new Vector2(vertices[i * 2] / PPM, vertices[i * 2 + 1] / PPM);
            worldVertices[i] = current;
        }

        PolygonShape shape = new PolygonShape();
        shape.set(worldVertices);
        return shape;
    }

    private void clearWorldBodies() {
        Array<Body> bodies = new Array<Body>();
        playstate.getWorld().getBodies(bodies);
        
        for (Body body : bodies) {
            // Check if the body is not the player body    
            playstate.getWorld().destroyBody(body);
            
        }
    }

}
