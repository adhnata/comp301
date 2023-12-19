package com.comp301.a02adventure;

import java.util.ArrayList;
import java.util.List;

public final class InventoryImpl implements Inventory {
  private final List<Item> items;

  public InventoryImpl() {
    items = new ArrayList<>();
  }

  @Override
  public boolean isEmpty() {
    return items.isEmpty();
  }

  @Override
  public int getNumItems() {
    return items.size();
  }

  @Override
  public List<Item> getItems() {
    return new ArrayList<>(items);
  }

  @Override
  public void addItem(Item item) {
    items.add(item);
  }

  @Override
  public void removeItem(Item item) {
    items.remove(item);
  }

  @Override
  public void clear() {
    items.clear();
  }

  @Override
  public void transferFrom(Inventory other) {
    List<Item> x = other.getItems();
    items.addAll(x);
    other.clear();
  }
}
