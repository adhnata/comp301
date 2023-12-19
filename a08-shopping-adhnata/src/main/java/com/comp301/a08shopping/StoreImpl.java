package com.comp301.a08shopping;

import com.comp301.a08shopping.events.*;
import com.comp301.a08shopping.exceptions.OutOfStockException;
import com.comp301.a08shopping.exceptions.ProductNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class StoreImpl implements Store {
  private final String name;
  private final ArrayList<StoreObserver> storeObserverList;
  private final ArrayList<Product> productList;
  private final ArrayList<Integer> productInventory;
  private final ArrayList<Double> productSalePercent;

  public StoreImpl(String name) {
    if (name == null) {
      throw new IllegalArgumentException();
    }
    this.name = name;
    this.storeObserverList = new ArrayList<>();
    this.productList = new ArrayList<>();
    this.productInventory = new ArrayList<>();
    this.productSalePercent = new ArrayList<>();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void addObserver(StoreObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException();
    }
    storeObserverList.add(observer);
  }

  @Override
  public void removeObserver(StoreObserver observer) {
    storeObserverList.remove(observer);
  }

  @Override
  public List<Product> getProducts() {
    return new ArrayList<>(productList);
  }

  @Override
  public Product createProduct(String name, double basePrice, int inventory) {
    if (basePrice == 0.0) {
      throw new IllegalArgumentException();
    }
    if (inventory == -1) {
      throw new IllegalArgumentException();
    }
    if (name == null) {
      throw new IllegalArgumentException();
    }
    Product product = new ProductImpl(name, basePrice);
    productList.add(product);
    productInventory.add(inventory);
    productSalePercent.add(0.0);
    notifyObservers(new SaleStartEvent(product, this));
    return product;
  }

  @Override
  public ReceiptItem purchaseProduct(Product product) {
    if (product == null) {
      throw new IllegalArgumentException();
    }
    int index = productList.indexOf(product);
    if (index == -1) {
      throw new ProductNotFoundException();
    }
    int inventory = productInventory.get(index);
    if (inventory == 0) {
      notifyObservers(new OutOfStockEvent(product, this));
      throw new OutOfStockException();
    }
    int newInventory = inventory - 1;
    if (newInventory == 0) {
      notifyObservers(new OutOfStockEvent(product, this));
    }
    productInventory.set(index, newInventory);

    double price = product.getBasePrice();

    if (getIsOnSale(product)) {
      price = getSalePrice(product);
    }
    ReceiptItem receiptItem = new ReceiptItemImpl(product.getName(), price, this.getName());
    notifyObservers(new PurchaseEvent(product, this));
    return receiptItem;
  }

  @Override
  public void restockProduct(Product product, int numItems) {

    if (product == null) {
      throw new IllegalArgumentException();
    }
    if (numItems < 0) {
      throw new IllegalArgumentException();
    }
    int index = productList.indexOf(product);
    if (index == -1) {
      throw new ProductNotFoundException();
    }
    int currentInventory = productInventory.get(index);
    if (currentInventory == 0) {
      notifyObservers(new BackInStockEvent(product, this));
    }
    int newInventory = currentInventory + numItems;
    productInventory.set(index, newInventory);
  }

  @Override
  public void startSale(Product product, double percentOff) {
    if (product == null) {
      throw new IllegalArgumentException();
    }
    if (percentOff < 0.0 || percentOff > 1.0) {
      throw new IllegalArgumentException();
    }
    int index = productList.indexOf(product);
    if (index == -1) {
      throw new ProductNotFoundException();
    }
    double discount = product.getBasePrice() * (1.0 - percentOff);
    discount = Math.round(discount * 100) / 100.0;
    productSalePercent.set(index, discount);
    notifyObservers(new SaleStartEvent(product, this));
  }

  @Override
  public void endSale(Product product) {
    if (product == null) {
      throw new IllegalArgumentException();
    }
    int index = productList.indexOf(product);
    if (index == -1) {
      throw new ProductNotFoundException();
    }
    productSalePercent.set(index, product.getBasePrice() * 1);
    notifyObservers(new SaleEndEvent(product, this));
  }

  @Override
  public int getProductInventory(Product product) {
    int index = productList.indexOf(product);
    if (product == null) {
      throw new IllegalArgumentException();
    }
    if (index == -1) {
      throw new ProductNotFoundException();
    }
    return productInventory.get(index);
  }

  @Override
  public boolean getIsInStock(Product product) {
    int inventory = getProductInventory(product);
    return inventory > 0;
  }

  @Override
  public double getSalePrice(Product product) {
    if (product == null) {
      throw new IllegalArgumentException();
    }

    int index = productList.indexOf(product);
    if (index == -1) {
      throw new ProductNotFoundException();
    }
    return productSalePercent.get(index);
  }

  @Override
  public boolean getIsOnSale(Product product) {
    int index = productList.indexOf(product);
    if (index == -1) {
      throw new IllegalArgumentException();
    }
    return productSalePercent.get(index) > 0;
  }

  private void notifyObservers(StoreEvent event) {
    for (StoreObserver observer : storeObserverList) {
      observer.update(event);
    }
  }
}
