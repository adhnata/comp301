package com.comp301.a02adventure;

import java.util.Objects;

public final class ItemImpl implements Item {
  private final String name;

  public ItemImpl(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Please enter a name.");
    }
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    ItemImpl item = (ItemImpl) other;
    return Objects.equals(name, item.name);
  }

  @Override
  public String toString() {
    return name;
  }
}
