package com.comp301.a01sushi;

public class Nigiri implements Sushi {
  private final String x;
  private Ingredient nigiriIngredient;
  private final Ingredient riceComponent = new Rice();

  public enum NigiriType {
    TUNA,
    YELLOWTAIL,
    EEL,
    CRAB,
    SHRIMP
  }

  public Nigiri(NigiriType type) {
    x = type.toString().toLowerCase();
    if (x.equals("eel")) {
      nigiriIngredient = new Eel();
    } else if (x.equals("shrimp")) {
      nigiriIngredient = new Shrimp();
    } else if (x.equals("crab")) {
      nigiriIngredient = new Crab();
    } else if (x.equals("yellowtail")) {
      nigiriIngredient = new Yellowtail();
    } else if (x.equals("tuna")) {
      nigiriIngredient = new Tuna();
    }
  }

  @Override
  public String getName() {
    return x + " nigiri";
  }

  @Override
  public IngredientPortion[] getIngredients() {
    IngredientPortion[] xPortion = new IngredientPortion[2];
    xPortion[0] = new IngredientPortionParent(nigiriIngredient, .75);
    xPortion[1] = new IngredientPortionParent(riceComponent, .5);
    return xPortion;
  }

  @Override
  public int getCalories() {
    double meatCals = nigiriIngredient.getCaloriesPerOunce() * .75;
    double riceCals = riceComponent.getCaloriesPerOunce() * .5;
    int newCals = (int) Math.ceil(meatCals + riceCals);

    return newCals;
  }

  @Override
  public double getCost() {

    double meatCost = nigiriIngredient.getPricePerOunce() * .75;
    double riceCost = riceComponent.getPricePerOunce() * .5;
    double cost = meatCost + riceCost;
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
