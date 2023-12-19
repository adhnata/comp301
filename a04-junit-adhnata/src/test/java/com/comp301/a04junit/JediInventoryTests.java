package com.comp301.a04junit;

import static org.junit.Assert.assertTrue;

import com.comp301.a04junit.adventure.Item;
import com.comp301.a04junit.adventure.ItemImpl;
import com.comp301.a04junit.adventure.Inventory;
import com.comp301.a04junit.adventure.InventoryImpl;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

/** Write unit tests for the InventoryImpl class here */
public class JediInventoryTests {
  @Test
  public void unitTest1() {
    Inventory bag = new InventoryImpl();
    assertTrue(bag.isEmpty());
    assertTrue(bag.getNumItems() == new InventoryImpl().getNumItems());
  }

  @Test
  public void unitTest2(){
    InventoryImpl bag = new InventoryImpl();
    ArrayList list = new ArrayList();
    ItemImpl one = new ItemImpl("one");
    ItemImpl two = new ItemImpl("two");
    ItemImpl three = new ItemImpl("three");
    ItemImpl four = new ItemImpl("four");
    bag.addItem(one);
    bag.addItem(two);
    bag.addItem(three);
    bag.addItem(four);
    list.add(one);
    list.add(two);
    list.add(three);
    list.add(four);
    assertEquals(bag.getItems(), list);
  }

  @Test
  public void unitTest3(){
    InventoryImpl bag = new InventoryImpl();
    ItemImpl one = new ItemImpl("one");
    ItemImpl two = new ItemImpl("two");
    ItemImpl three = new ItemImpl("three");
    ItemImpl four = new ItemImpl("four");
    bag.addItem(one);
    bag.addItem(two);
    bag.addItem(three);
    bag.addItem(four);
    assertEquals(4, bag.getNumItems());
  }

  @Test
  public void unitTest4(){
    InventoryImpl bag = new InventoryImpl();
    ItemImpl one = new ItemImpl("one");
    ItemImpl two = new ItemImpl("two");
    ItemImpl three = new ItemImpl("three");
    ItemImpl four = new ItemImpl("four");
    bag.addItem(one);
    bag.addItem(two);
    bag.addItem(three);
    bag.addItem(four);
    assertEquals(4, bag.getNumItems());
    assertFalse(bag.isEmpty());
  }

  @Test
  public void unitTest5(){
    InventoryImpl bag = new InventoryImpl();
    ItemImpl one = new ItemImpl("one");
    ItemImpl two = new ItemImpl("two");
    ItemImpl three = new ItemImpl("three");
    ItemImpl four = new ItemImpl("four");
    bag.addItem(one);
    bag.addItem(two);
    bag.addItem(three);
    bag.addItem(four);
    bag.removeItem(one);
    assertEquals(3, bag.getNumItems());
    assertFalse(bag.isEmpty());
  }

  @Test
  public void unitTest6(){
    InventoryImpl bag = new InventoryImpl();
    ItemImpl one = new ItemImpl("one");
    ItemImpl two = new ItemImpl("two");
    ItemImpl three = new ItemImpl("three");
    ItemImpl four = new ItemImpl("four");
    bag.addItem(one);
    bag.addItem(two);
    bag.addItem(three);
    bag.addItem(four);
    bag.clear();
    InventoryImpl bagTwo = new InventoryImpl();
    assertEquals(bag.getNumItems(), bagTwo.getNumItems());
  }

  @Test
  public void unitTest7(){
    InventoryImpl bag = new InventoryImpl();
    InventoryImpl bagTwo = new InventoryImpl();
    ItemImpl one = new ItemImpl("one");
    ItemImpl two = new ItemImpl("two");
    ItemImpl three = new ItemImpl("three");
    ItemImpl four = new ItemImpl("four");
    bag.addItem(one);
    bag.addItem(two);
    bagTwo.addItem(three);
    bagTwo.addItem(four);
    bag.transferFrom(bagTwo);
    assertTrue(bagTwo.isEmpty());
    assertEquals(4, bag.getNumItems());
    bag.transferFrom(null);
    assertEquals(4, bag.getNumItems());
  }
}
