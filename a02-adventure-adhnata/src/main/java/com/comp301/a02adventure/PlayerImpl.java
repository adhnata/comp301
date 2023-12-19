package com.comp301.a02adventure;

public class PlayerImpl implements Player {
  private final String name;
  private final Inventory inventory;
  private Position position;

  public PlayerImpl(String name, int startX, int startY) {
    this.position = new PositionImpl(startX, startY);
    if (name == null) {
      throw new IllegalArgumentException("Name can't be null");
    }
    this.name = name;
    this.inventory = new InventoryImpl();
  }

  @Override
  public Position getPosition() {
    return position;
  }

  @Override
  public Inventory getInventory() {
    return inventory;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void move(Direction direction) {
    Position pos2 = position.getNeighbor(direction);
    position = pos2;
  }
}
