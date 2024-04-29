package inf112.skeleton.app;

import static inf112.skeleton.helper.Constants.PPM;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;

import inf112.skeleton.helper.BodyHelperService;
import inf112.skeleton.helper.TileMapHelper;
import inf112.skeleton.states.PlayState;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.physics.box2d.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class BodyHelperServiceTest {
    @Mock private World world;
    @Mock private Body body;
    private BodyHelperService bodyHelperService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        bodyHelperService = new BodyHelperService(PPM);

        when(world.createBody(any(BodyDef.class))).thenReturn(body);
        when(body.createFixture(any(FixtureDef.class))).thenReturn(mock(Fixture.class));
    }

    @Test
    void createBody_createsDynamicBodyCorrectly() {
        float x = 10.0f;
        float y = 20.0f;
        float width = 30.0f;
        float height = 40.0f;
        boolean isStatic = false;

        Body result = bodyHelperService.createBody(x, y, width, height, isStatic, world);

        assertNotNull(result);
        verify(world).createBody(argThat(bodyDef -> 
            bodyDef.type == BodyDef.BodyType.DynamicBody &&
            bodyDef.position.x == x / PPM &&
            bodyDef.position.y == y / PPM &&
            bodyDef.fixedRotation
        ));

        verify(body).createFixture(argThat(fixtureDef -> 
            fixtureDef.shape instanceof PolygonShape &&
            fixtureDef.friction == 0
        ));
    }

    @Test
    void createBody_createsStaticBodyCorrectly() {
        float x = 10.0f;
        float y = 20.0f;
        float width = 30.0f;
        float height = 40.0f;
        boolean isStatic = true;

        Body result = bodyHelperService.createBody(x, y, width, height, isStatic, world);

        assertNotNull(result);
        verify(world).createBody(argThat(bodyDef -> 
            bodyDef.type == BodyDef.BodyType.StaticBody &&
            bodyDef.position.x == x / PPM &&
            bodyDef.position.y == y / PPM &&
            bodyDef.fixedRotation
        ));

        verify(body).createFixture(argThat(fixtureDef -> 
            fixtureDef.shape instanceof PolygonShape &&
            fixtureDef.friction == 0
        ));
    }
}