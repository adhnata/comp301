package com.comp301.a01sushi;

public class RicePortion extends IngredientPortionParent {
  public RicePortion(double amount) {
    super(new Rice(), amount);
  }
}
