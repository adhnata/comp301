package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {

  private int[][] board;

  public PuzzleImpl(int[][] board) {
    if (board != null && board.length > 0 && board[0].length > 0) {
      this.board = board;
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public int getWidth() {
    return board[0].length;
  }

  @Override
  public int getHeight() {
    return board.length;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r >= getHeight() || c >= getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    int value = board[r][c];
    if (value == 5) {
      return CellType.WALL;
    }
    if (value == 6) {
      return CellType.CORRIDOR;
    }
    return CellType.CLUE;
  }

  @Override
  public int getClue(int r, int c) {
    if (r >= getHeight() || c >= getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    } else {
      return board[r][c];
    }
  }
}
