package com.comp301.a01sushi;

public class IngredientPortionParent implements IngredientPortion {

  private final Ingredient ingredient;
  private final double amount;

  public IngredientPortionParent(Ingredient ingredient, double amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount specified is less than 0.");
    }
    this.ingredient = ingredient;
    this.amount = amount;
  }

  @Override
  public Ingredient getIngredient() {
    return ingredient;
  }

  @Override
  public double getAmount() {
    return amount;
  }

  @Override
  public String getName() {
    return ingredient.getName();
  }

  @Override
  public boolean getIsVegetarian() {
    return ingredient.getIsVegetarian();
  }

  @Override
  public boolean getIsRice() {
    return ingredient.getIsRice();
  }

  @Override
  public boolean getIsShellfish() {
    return ingredient.getIsShellfish();
  }

  @Override
  public double getCalories() {
    return ingredient.getCaloriesPerOunce() * getAmount();
  }

  @Override
  public double getCost() {
    return ingredient.getPricePerOunce() * getAmount();
  }

  @Override
  public IngredientPortion combine(IngredientPortion other) {
    if (this.getIngredient().equals(other.getIngredient())) {
      return new IngredientPortionParent(
          this.getIngredient(), this.getAmount() + other.getAmount());
    } else {
      throw new IllegalArgumentException();
    }
  }
}
