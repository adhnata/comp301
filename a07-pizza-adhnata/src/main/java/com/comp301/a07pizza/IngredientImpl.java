package com.comp301.a07pizza;

public class IngredientImpl implements Ingredient {
  private final String name;
  private final boolean isVegeta;
  private final boolean isVegan;

  public IngredientImpl(String name, boolean isVegeta, boolean isVegan) {
    this.name = name;
    this.isVegeta = isVegeta;
    this.isVegan = isVegan;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean isVegetarian() {
    return isVegeta;
  }

  @Override
  public boolean isVegan() {
    return isVegan;
  }
}
