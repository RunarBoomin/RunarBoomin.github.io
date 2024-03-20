package inf112.skeleton.objects.Shop;

import inf112.skeleton.objects.player.Player;

public class ShopItem {
  private final int SPEEDBOOST   = 0;
  private final int JUMPHEIGHT   = 1;
  private final int JUMPCOUNT    = 2;
  private final int SCREENSPEED  = 3;

  Player player;
  int itemType;
  int cost;
  boolean purchased;

  public ShopItem(Player player, int itemType) {
    this.player = player;
    this.itemType = itemType;
    this.cost = 10;
    this.purchased = false;
  
  }

  public int getCost() {
    return cost;
  }

  public boolean isPurchased() {
    return purchased;
  }

  public void activate() {
    switch (itemType) {
      case SPEEDBOOST -> player.addAccel(0.5f);
      case JUMPHEIGHT -> player.addJumpForce(2);
      case JUMPCOUNT  -> player.addJumps(1);
    }
    purchased = true;
  }
}
