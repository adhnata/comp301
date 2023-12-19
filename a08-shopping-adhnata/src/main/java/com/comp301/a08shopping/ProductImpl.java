package com.comp301.a08shopping;

import com.comp301.a08shopping.Product;

public class ProductImpl implements Product {
  private final String name;
  private final double basePrice;

  public ProductImpl(String name, double basePrice) {
    if (basePrice == 0.00) {
      throw new IllegalArgumentException();
    }
    this.name = name;
    this.basePrice = basePrice;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public double getBasePrice() {
    return basePrice;
  }
}
