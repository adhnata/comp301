package com.comp301.a08shopping;

import com.comp301.a08shopping.events.*;

import java.util.ArrayList;
import java.util.List;

public class CustomerImpl implements Customer {
  private final String name;
  private double budget;
  private final List<ReceiptItem> receiptItemList;

  public CustomerImpl(String name, double budget) {
    if (name == null) {
      throw new IllegalArgumentException();
    }
    if (budget < 0.0) {
      throw new IllegalArgumentException();
    }
    this.name = name;
    this.budget = budget;
    this.receiptItemList = new ArrayList<ReceiptItem>();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public double getBudget() {
    return budget;
  }

  @Override
  public void purchaseProduct(Product product, Store store) {
    if (product == null || store == null) {
      throw new IllegalArgumentException();
    }

    if (getBudget() < store.getSalePrice(product)) {
      throw new IllegalStateException();
    }
    budget -= store.getSalePrice(product);
    receiptItemList.add(store.purchaseProduct(product));
  }

  @Override
  public List<ReceiptItem> getPurchaseHistory() {
    return new ArrayList<ReceiptItem>(receiptItemList);
  }

  @Override
  public void update(StoreEvent event) {
    if (event.getClass() == BackInStockEvent.class) {
      System.out.println(
          event.getProduct().getName() + " is back in stock at " + event.getStore().getName());
    } else if (event.getClass() == OutOfStockEvent.class) {
      System.out.println(
          event.getProduct().getName() + " is now out of stock at " + event.getStore().getName());
    } else if (event.getClass() == PurchaseEvent.class) {
      System.out.println(
          "Someone purchased "
              + event.getProduct().getName()
              + " at "
              + event.getStore().getName());
    } else if (event.getClass() == SaleEndEvent.class) {
      System.out.println(
          "The sale for "
              + event.getProduct().getName()
              + " at "
              + event.getStore().getName()
              + " has ended");
    } else if (event.getClass() == SaleStartEvent.class) {
      System.out.println(
          "New sale for "
              + event.getProduct().getName()
              + " at "
              + event.getStore().getName()
              + "!");
    }
  }
}
