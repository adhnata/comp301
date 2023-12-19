package com.comp301.a01sushi;

import java.util.*;

public class Roll implements Sushi {
  private final String name;
  private IngredientPortion[] ingredientList;

  public Roll(String name, IngredientPortion[] roll_ingredients) {
    if (name == null || roll_ingredients == null) {
      throw new IllegalArgumentException();
    }
    for (IngredientPortion portion : roll_ingredients) {
      if (portion == null) {
        throw new IllegalArgumentException();
      }
    }
    this.name = name;
    this.ingredientList = combineIngredients(roll_ingredients.clone());
    ensureSeaweed();
  }

  private IngredientPortion[] combineIngredients(IngredientPortion[] portions) {
    Map<String, IngredientPortion> combinedMap = new HashMap<>();
    for (IngredientPortion portion : portions) {
      String ingredientName = portion.getIngredient().getName();
      if (combinedMap.containsKey(ingredientName)) {
        IngredientPortion existingPortion = combinedMap.get(ingredientName);
        combinedMap.put(ingredientName, existingPortion.combine(portion));
      } else {
        combinedMap.put(ingredientName, portion);
      }
    }
    return combinedMap.values().toArray(new IngredientPortion[0]);
  }

  private void ensureSeaweed() {
    boolean hasSeaweed = false;
    for (int i = 0; i < ingredientList.length; i++) {
      IngredientPortion portion = ingredientList[i];
      if (portion.getIngredient().getName().equals("seaweed")) {
        if (portion.getAmount() < 0.1) {
          ingredientList[i] = new SeaweedPortion(0.1);
        }
        hasSeaweed = true;
        break;
      }
    }
    if (!hasSeaweed) {
      IngredientPortion seaweedPortion = new SeaweedPortion(0.1);
      ingredientList = addIngredient(ingredientList, seaweedPortion);
    }
  }

  private IngredientPortion[] addIngredient(
      IngredientPortion[] arr, IngredientPortion ingredientList) {
    IngredientPortion[] newArr = Arrays.copyOf(arr, arr.length + 1);
    newArr[arr.length] = ingredientList;
    return newArr;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public IngredientPortion[] getIngredients() {
    return ingredientList.clone();
  }

  @Override
  public int getCalories() {
    double cal = 0;
    for (IngredientPortion portion : ingredientList) {
      cal += portion.getAmount() * portion.getIngredient().getCaloriesPerOunce();
    }
    return (int) Math.round(cal);
  }

  @Override
  public double getCost() {
    double price = 0;
    for (IngredientPortion portion : ingredientList) {
      price +=
          Math.round(portion.getAmount() * portion.getIngredient().getPricePerOunce() * 100.0)
              / 100.0;
    }
    return price;
  }

  @Override
  public boolean getHasRice() {
    for (IngredientPortion portion : ingredientList) {
      if (portion.getIngredient().getIsRice()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean getHasShellfish() {
    for (IngredientPortion portion : ingredientList) {
      if (portion.getIngredient().getIsShellfish()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean getIsVegetarian() {
    for (IngredientPortion portion : ingredientList) {
      if (portion.getIngredient().getIsVegetarian()) {
        return true;
      }
    }
    return false;
  }
}
