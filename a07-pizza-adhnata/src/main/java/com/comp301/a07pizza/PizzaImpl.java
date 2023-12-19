package com.comp301.a07pizza;

import java.util.*;

public class PizzaImpl implements Pizza {
  private final Size size;
  private final Crust crust;
  private final Sauce sauce;
  private final Cheese cheese;
  private final List<Topping> toppings;

  public PizzaImpl(Size size, Crust crust, Sauce sauce, Cheese cheese, List<Topping> toppings) {
    this.size = size;
    this.crust = crust;
    this.sauce = sauce;
    this.cheese = cheese;
    this.toppings = toppings;
  }

  @Override
  public boolean isVegetarian() {
    if (crust.isVegetarian() && sauce.isVegetarian() && cheese.isVegetarian()) {
      for (Topping top : toppings) {
        if (!top.isVegetarian()) {
          return false;
        }
      }
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean isVegan() {
    if (crust.isVegan() && sauce.isVegan() && cheese.isVegan()) {
      for (Topping top : toppings) {
        if (!top.isVegan()) {
          return false;
        }
      }
      return true;
    } else {
      return false;
    }
  }

  @Override
  public double getPrice() {
    double basePrice;
    switch (size) {
      case SMALL:
        basePrice = 7.00;
        basePrice = basePrice + (0.25 * toppings.size());
        break;
      case MEDIUM:
        basePrice = 9.00;
        basePrice = basePrice + (0.50 * toppings.size());
        break;
      case LARGE:
        basePrice = 11.00;
        basePrice = basePrice + (0.75 * toppings.size());
        break;
      default:
        basePrice = 0.00;
        break;
    }
    return basePrice;
  }

  @Override
  public Size getSize() {
    return size;
  }

  @Override
  public Ingredient getSauce() {
    return sauce;
  }

  @Override
  public Ingredient getCheese() {
    return cheese;
  }

  @Override
  public Ingredient getCrust() {
    return crust;
  }

  @Override
  public Ingredient[] getToppings() {
    Ingredient[] toppingList = new Ingredient[toppings.size()];
    for (int i = 0; i < toppings.size(); i++) {
      toppingList[i] = toppings.get(i);
    }
    return toppingList;
  }

  @Override
  public Ingredient[] getIngredients() {
    Ingredient[] ingredientList = new Ingredient[toppings.size() + 3];
    ingredientList[0] = crust;
    ingredientList[1] = cheese;
    ingredientList[2] = sauce;
    Ingredient[] topListAdd = getToppings();
    System.arraycopy(topListAdd, 0, ingredientList, 3, topListAdd.length);
    return ingredientList;
  }
}
