package com.comp301.a02adventure;

public final class PositionImpl implements Position {
  private final int x;
  private final int y;

  public PositionImpl(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int getX() {
    return x;
  }

  @Override
  public int getY() {
    return y;
  }

  @Override
  public Position getNeighbor(Direction direction) {
    int dirX = x;
    int dirY = y;

    switch (direction) {
      case NORTH:
        dirY++;
        break;
      case SOUTH:
        dirY--;
        break;
      case WEST:
        dirX--;
        break;
      case EAST:
        dirX++;
        break;
      default:
        break;
    }

    return new PositionImpl(dirX, dirY);
  }
}
