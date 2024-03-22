package inf112.skeleton.objects.player;

import static inf112.skeleton.helper.Constants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

import inf112.skeleton.objects.Shop.Shop;

public class ShopKeeper extends GameEntity {

  private Body body;
  private boolean showShop = false;
  private Shop shop;
  Rectangle rect = new Rectangle(75, 200, 600, 200);

  public ShopKeeper(float width, float height, Body body) {
    super(width, height, body);

    for (Fixture fixture : body.getFixtureList()) {
      fixture.setUserData("shop");
    }
 
  }

  @Override
  public void update() {
    if (!showShop) {
      return;
    }

    if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
    }
  }

  @Override
  public void render(SpriteBatch batch) {
    
  }

  
  public void setShop(Body body) {
    body.setActive(showShop);
    this.body = body;
  }

  @Override
  public void direction(float x, float y) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'direction'");
  }

  public void addPlayer(Player player) {
    this.shop = new Shop(5, player);
  }

  public Shop getShop() {
    return shop;
  }

}
