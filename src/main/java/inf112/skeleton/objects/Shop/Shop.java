package inf112.skeleton.objects.Shop;

import java.util.ArrayList;
import java.util.Random;

import inf112.skeleton.objects.player.Player;

public class Shop {
  
  private int ITEMCOUNT = 3;
  Random rand;
  int shopSize;
  Player player;
  ArrayList<ShopItem> shopItems;

  public Shop(int shopSize, Player player) {
    this.shopSize = shopSize;
    this.player = player;
    rand = new Random();

    generateShop();
  }

  public void generateShop() {
    shopItems = new ArrayList<>();

    for(int i = 0; i < shopSize; i++) {
      shopItems.add(new ShopItem(player, rand.nextInt(ITEMCOUNT)));
    } 
  }


  public void purchase(int index) {
    if (shopItems.get(index).isPurchased()) {
      return;
    }
    ShopItem item = shopItems.get(index);
    if (player.useWallet(item.getCost())) {
      item.activate();
    }
  }

  public ArrayList<ShopItem> getShop() {
    return shopItems;
  }


}
