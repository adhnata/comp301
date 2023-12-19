package com.comp301.a02adventure;

import java.util.List;

public class GameImpl implements Game {

  private final Map map;
  private Player player;

  public GameImpl(Map map, Player player) {
    if (map == null || player == null) {
      throw new IllegalArgumentException("Map and player can't be null.");
    }
    this.map = map;
    this.player = player;
  }

  @Override
  public Position getPlayerPosition() {
    return player.getPosition();
  }

  @Override
  public String getPlayerName() {
    return player.getName();
  }

  @Override
  public List<Item> getPlayerItems() {
    return player.getInventory().getItems();
  }

  @Override
  public boolean getIsWinner() {
    int totalItems = map.getNumItems();

    int collectedItems = player.getInventory().getNumItems();

    return collectedItems == totalItems;
  }

  @Override
  public void printCellInfo() {
    Position playerPosition = getPlayerPosition();
    Cell currentCell = map.getCell(playerPosition);

    System.out.println("Location: " + currentCell.getName());
    System.out.println(currentCell.getDescription());

    if (currentCell.getIsVisited()) {
      System.out.println("You have already visited this location.");
    }

    if (currentCell.hasChest()) {
      System.out.println("You found a chest! Type 'open' to see what's inside, or keep moving.");
    }

    currentCell.visit();
  }

  @Override
  public void openChest() {
    Position playerPosition = getPlayerPosition();
    Cell currentCell = map.getCell(playerPosition);
    if (currentCell != null && currentCell.hasChest()) {
      Inventory chest = currentCell.getChest();
      if (chest.isEmpty()) {
        System.out.println("The chest is empty.");
      } else {
        player.getInventory().transferFrom(chest);
        List<Item> collectedItems = player.getInventory().getItems();
        System.out.print("You collected these items: " + collectedItems);
      }
      currentCell.setChest(null);
    } else {
      System.out.println("No chest to open, sorry!");
    }
  }

  @Override
  public boolean canMove(Direction direction) {
    Position playerPosition = getPlayerPosition();
    Position newPosition = playerPosition.getNeighbor(direction);
    int mapWidth = map.getWidth();
    int mapHeight = map.getHeight();
    int newX = newPosition.getX();
    int newY = newPosition.getY();

    return newX >= 0
        && newX < mapWidth
        && newY >= 0
        && newY < mapHeight
        && map.getCell(newPosition) != null;
  }

  @Override
  public void move(Direction direction) {
    if (!canMove(direction)) {
      System.out.println("You can't go that way! Try another direction.");
      return;
    }

    Position playerPosition = getPlayerPosition();

    Position newPosition = playerPosition.getNeighbor(direction);

    player = new PlayerImpl(player.getName(), newPosition.getX(), newPosition.getY());

    printCellInfo();
  }
}
