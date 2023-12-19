package com.comp301.a01sushi;

public class IngredientParent implements Ingredient {
  private final String name;
  private final int caloriesPerOunce;
  private final double pricePerOunce;
  private final double caloriesPerDollar;
  private final boolean isVegetarian;
  private final boolean isRice;
  private final boolean isShellfish;

  public IngredientParent(
      String name,
      int caloriesPerOunce,
      double pricePerOunce,
      double caloriesPerDollar,
      boolean isVegetarian,
      boolean isRice,
      boolean isShellfish) {
    this.name = name;
    this.caloriesPerOunce = caloriesPerOunce;
    this.pricePerOunce = pricePerOunce;
    this.caloriesPerDollar = caloriesPerDollar;
    this.isVegetarian = isVegetarian;
    this.isRice = isRice;
    this.isShellfish = isShellfish;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getCaloriesPerOunce() {
    return caloriesPerOunce;
  }

  @Override
  public double getPricePerOunce() {
    return pricePerOunce;
  }

  @Override
  public double getCaloriesPerDollar() {
    return (caloriesPerOunce / pricePerOunce);
  }

  @Override
  public boolean getIsVegetarian() {
    return isVegetarian;
  }

  @Override
  public boolean getIsRice() {
    return isRice;
  }

  @Override
  public boolean getIsShellfish() {
    return isShellfish;
  }

  @Override
  public boolean equals(Ingredient other) {
    return getName() == other.getName()
        && getPricePerOunce() == other.getPricePerOunce()
        && getCaloriesPerDollar() == other.getCaloriesPerDollar()
        && getCaloriesPerOunce() == other.getCaloriesPerOunce()
        && getIsVegetarian() == other.getIsVegetarian()
        && getIsRice() == other.getIsRice()
        && getIsShellfish() == other.getIsShellfish();
  }
}
