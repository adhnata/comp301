package com.comp301.a07pizza;

import java.util.List;

public class PizzaFactory {
  public static Pizza makeCheesePizza(Pizza.Size size) {
    return new PizzaImpl(size, Crust.HAND_TOSSED, Sauce.TOMATO, Cheese.BLEND, List.of());
  }

  public static Pizza makeHawaiianPizza(Pizza.Size size) {
    List<Topping> toppings = List.of(Topping.HAM, Topping.PINEAPPLE);
    return new PizzaImpl(size, Crust.HAND_TOSSED, Sauce.TOMATO, Cheese.BLEND, toppings);
  }

  public static Pizza makeMeatLoversPizza(Pizza.Size size) {
    List<Topping> toppings =
        List.of(Topping.PEPPERONI, Topping.SAUSAGE, Topping.BACON, Topping.GROUND_BEEF);
    return new PizzaImpl(size, Crust.DEEP_DISH, Sauce.TOMATO, Cheese.BLEND, toppings);
  }

  public static Pizza makeVeggieSupremePizza(Pizza.Size size) {
    List<Topping> toppings =
        List.of(Topping.SUN_DRIED_TOMATO, Topping.GREEN_PEPPER, Topping.MUSHROOMS, Topping.OLIVES);
    return new PizzaImpl(size, Crust.THIN, Sauce.TOMATO, Cheese.BLEND, toppings);
  }

  public static Pizza makeDailySpecialPizza() {
    return new PizzaImpl(
        Pizza.Size.LARGE,
        Crust.DEEP_DISH,
        Sauce.TOMATO,
        Cheese.BLEND,
        List.of(Topping.MUSHROOMS, Topping.OLIVES));
  }
}
