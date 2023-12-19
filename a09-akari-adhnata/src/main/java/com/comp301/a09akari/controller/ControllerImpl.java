package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import java.util.Objects;

public class ControllerImpl implements ClassicMvcController {
  private final Model model;

  public ControllerImpl(Model model) {
    if (model == null) {
      throw new IllegalArgumentException();
    }
    this.model = model;
  }

  @Override
  public void clickNextPuzzle() {
    System.out.print(this.model.getActivePuzzleIndex());
    try {
      this.model.setActivePuzzleIndex(this.model.getActivePuzzleIndex() + 1);
    } catch (IndexOutOfBoundsException e) {
      this.model.setActivePuzzleIndex(0);
      System.out.print("Success for this part");
    }
  }

  @Override
  public void clickPrevPuzzle() {
    try {
      this.model.setActivePuzzleIndex(this.model.getActivePuzzleIndex() - 1);
    } catch (IndexOutOfBoundsException e) {
      this.model.setActivePuzzleIndex(this.model.getPuzzleLibrarySize() - 1);
    }
  }

  @Override
  public void clickRandPuzzle() {
    int idSelect = (int) (Math.random() * this.model.getPuzzleLibrarySize());
    while (idSelect == this.model.getActivePuzzleIndex()) {
      idSelect = (int) (Math.random() * this.model.getPuzzleLibrarySize());
    }
    try {
      this.model.setActivePuzzleIndex(idSelect);
    } catch (IndexOutOfBoundsException e) {
      System.out.println("There may be an error with the controller");
    }
  }

  @Override
  public void clickResetPuzzle() {
    this.model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    try {
      CellType type = this.model.getActivePuzzle().getCellType(r, c);
      if (Objects.requireNonNull(type) == CellType.CORRIDOR) {
        if (!this.model.isLamp(r, c)) {
          this.model.addLamp(r, c);
        } else {
          this.model.removeLamp(r, c);
        }
      }
    } catch (IndexOutOfBoundsException e) {
      System.out.println("This cell selection is not valid");
    }
  }
}
