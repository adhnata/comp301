package com.comp301.a01sushi;

public class Sashimi implements Sushi {

  private final String x;
  private Ingredient sashimiIngredient;

  public enum SashimiType {
    TUNA,
    YELLOWTAIL,
    EEL,
    CRAB,
    SHRIMP
  }

  public Sashimi(SashimiType type) {
    x = type.toString().toLowerCase();
    if (x.equals("eel")) {
      sashimiIngredient = new Eel();
    } else if (x.equals("shrimp")) {
      sashimiIngredient = new Shrimp();
    } else if (x.equals("crab")) {
      sashimiIngredient = new Crab();
    } else if (x.equals("yellowtail")) {
      sashimiIngredient = new Yellowtail();
    } else if (x.equals("tuna")) {
      sashimiIngredient = new Tuna();
    }
  }

  @Override
  public String getName() {
    return x + " sashimi";
  }

  @Override
  public IngredientPortion[] getIngredients() {
    IngredientPortion[] xPortion = new IngredientPortion[1];
    xPortion[0] = new IngredientPortionParent(sashimiIngredient, .75);
    return xPortion;
  }

  @Override
  public int getCalories() {
    double cals = sashimiIngredient.getCaloriesPerOunce() * .75;
    int newCals = (int) Math.ceil(cals);

    return newCals;
  }

  @Override
  public double getCost() {
    double cost = sashimiIngredient.getPricePerOunce() * .75;

    return cost;
  }

  @Override
  public boolean getHasRice() {
    return false;
  }

  @Override
  public boolean getHasShellfish() {
    return false;
  }

  @Override
  public boolean getIsVegetarian() {
    return false;
  }
}
