package inf112.skeleton.objects.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public abstract class GameEntity {
    protected float x, y;
    public float velX;
    protected float velY;
    protected float speed;
    protected float width, height;
    protected Body body;
    protected float accel;

    public GameEntity(float width, float height, Body body){
        this.x = body.getPosition().x;
        this.y = body.getPosition().y;
        this.width = width;
        this.height = height;
        this.body = body;
        this.velX = 0;
        this.velY = 0;
        this.speed = 0;
        this.accel = 1f;
    }

    public abstract void update();

    public abstract void render(SpriteBatch batch);

    public Body getBody(){
        return body;
    }

    public float getAccel() {
        return accel;
    }

    public void addAccel(float accel) {
        this.accel += accel;
    }
}
