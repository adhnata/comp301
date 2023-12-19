package com.comp301.a09akari.model;

import java.util.*;

public class ModelImpl implements Model {
  private PuzzleLibrary puzzleLibrary;
  private int activePuzzleIndex;
  private List<ModelObserver> observers;
  private Set<String> inCells;
  private Map<String, Integer> placedLamps;

  public ModelImpl(PuzzleLibrary library) {
    if (library == null) {
      throw new IllegalArgumentException();
    }
    puzzleLibrary = library;
    activePuzzleIndex = 0;
    placedLamps = new HashMap<>();
    observers = new ArrayList<>();
    inCells = new HashSet<>();
  }

  private void notifyObservers(Model model) {
    observers.forEach(obs -> obs.update(model));
  }

  @Override
  public void addLamp(int r, int c) {
    if (r >= getActivePuzzle().getHeight() || c >= getActivePuzzle().getWidth() || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    if (!placedLamps.containsKey(r + " " + c)) {
      placedLamps.put(r + " " + c, 1);
    }
    System.out.println("added lamp");
    System.out.println(isSolved());
    notifyObservers(this);
  }

  @Override
  public void removeLamp(int r, int c) {
    if (r >= getActivePuzzle().getHeight() || c >= getActivePuzzle().getWidth() || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    placedLamps.remove(r + " " + c);
    notifyObservers(this);
  }

  @Override
  public boolean isLit(int r, int c) {
    if (r >= getActivePuzzle().getHeight() || c >= getActivePuzzle().getWidth() || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    String key = r + " " + c;
    if (placedLamps.containsKey(key)) {
      return true;
    }
    for (int i = 1; i <= r; i++) {
      if (placedLamps.containsKey(r - i + " " + c)) {
        return true;
      } else if (getActivePuzzle().getCellType(r - i, c) != CellType.CORRIDOR) {
        break;
      }
    }
    for (int i = r + 1; i < getActivePuzzle().getHeight(); ++i) {
      if (placedLamps.containsKey(i + " " + c)) {
        return true;
      } else if (getActivePuzzle().getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
    }
    for (int i = 1; i <= c; ++i) {
      if (placedLamps.containsKey(r + " " + (c - i))) {
        return true;
      } else if (getActivePuzzle().getCellType(r, c - i) != CellType.CORRIDOR) {
        break;
      }
    }
    for (int i = c + 1; i < getActivePuzzle().getWidth(); ++i) {
      if (placedLamps.containsKey(r + " " + i)) {
        return true;
      } else if (getActivePuzzle().getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
    }
    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (r >= getActivePuzzle().getHeight() || c >= getActivePuzzle().getWidth() || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    return placedLamps.containsKey(r + " " + c);
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (r >= getActivePuzzle().getHeight() || c >= getActivePuzzle().getWidth() || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (!placedLamps.containsKey(r + " " + c)) {
      throw new IllegalArgumentException();
    }
    placedLamps.remove(r + " " + c);
    boolean illegal = isLit(r, c);
    placedLamps.put(r + " " + c, 1);
    return illegal;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return puzzleLibrary.getPuzzle(activePuzzleIndex);
  }

  @Override
  public int getActivePuzzleIndex() {
    return activePuzzleIndex;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    if (index >= puzzleLibrary.size() || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    activePuzzleIndex = index;
    notifyObservers(this);
  }

  @Override
  public int getPuzzleLibrarySize() {
    return puzzleLibrary.size();
  }

  @Override
  public void resetPuzzle() {
    placedLamps.clear();
    notifyObservers(this);
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    if (r >= getActivePuzzle().getHeight() || c >= getActivePuzzle().getWidth() || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (getActivePuzzle().getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    Puzzle puzzle = getActivePuzzle();
    int val = puzzle.getClue(r, c);
    int lampsAround = 0;
    try {
      if (isLamp(r - 1, c)) {
        lampsAround++;
      }
    } catch (RuntimeException e) {
      System.out.println("outside bounds");
    }
    try {
      if (isLamp(r + 1, c)) {
        lampsAround++;
      }
    } catch (RuntimeException e) {
      System.out.println("outside bounds");
    }
    try {
      if (isLamp(r, c + 1)) {
        lampsAround++;
      }
    } catch (RuntimeException e) {
      System.out.println("outside bounds");
    }
    try {
      if (isLamp(r, c - 1)) {
        lampsAround++;
      }
    } catch (RuntimeException e) {
      System.out.println("outside bounds");
    }
    return lampsAround == val;
  }

  @Override
  public boolean isSolved() {
    for (int i = 0; i < getActivePuzzle().getHeight(); i++) {
      for (int j = 0; j < getActivePuzzle().getWidth(); j++) {
        if (getActivePuzzle().getCellType(i, j) == CellType.CORRIDOR) {
          if (isLit(i, j)) {
            if (isLamp(i, j)) {
              if (isLampIllegal(i, j)) {
                return false;
              }
            }
          } else {
            return false;
          }
        } else if (getActivePuzzle().getCellType(i, j) == CellType.CLUE) {
          if (!isClueSatisfied(i, j)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  @Override
  public void addObserver(ModelObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    if (!observers.remove(observer)) {
      throw new NoSuchElementException();
    }
  }
}
