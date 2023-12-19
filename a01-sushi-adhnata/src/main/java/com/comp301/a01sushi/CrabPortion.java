package com.comp301.a01sushi;

public class CrabPortion extends IngredientPortionParent {
  public CrabPortion(double amount) {
    super(new Crab(), amount);
  }
}
