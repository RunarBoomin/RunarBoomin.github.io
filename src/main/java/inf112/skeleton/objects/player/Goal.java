package inf112.skeleton.objects.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import static inf112.skeleton.helper.Constants.PPM;
public class Goal extends  GameEntity{

    public Goal(float width, float height, Body body) {
        super(width, height, body);
        
        body.setGravityScale(0);
        body.setType(BodyDef.BodyType.StaticBody);

        for (Fixture fixture : body.getFixtureList()) {
            fixture.setUserData("goal");
        }
    }

    @Override
    public void update() {
        x = body.getPosition().x * PPM;
        y = body.getPosition().y * PPM;
    }

    @Override
    public void render(SpriteBatch batch) {

    }

    @Override
    public void direction(float x, float y) {

    }
    
}
