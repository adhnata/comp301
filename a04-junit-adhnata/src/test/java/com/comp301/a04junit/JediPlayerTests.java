package com.comp301.a04junit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import com.comp301.a04junit.adventure.*;

import org.junit.Test;

/** Write unit tests for the PlayerImpl class here */
public class JediPlayerTests {
  @Test
  public void unitTest1() {
    Player contact = new PlayerImpl("bigBaller", 0, 0);
    assertEquals("bigBaller", contact.getName());
    Position check = new PositionImpl(0,0);
    assertEquals(contact.getPosition().getX(), check.getX());
    assertEquals(contact.getPosition().getY(),check.getY());
    assertEquals(contact.getInventory().getNumItems(), new InventoryImpl().getNumItems());
    try{
      Player playCheck = new PlayerImpl(null, 0,0);
      fail();
    }
    catch(IllegalArgumentException e){
      return;
    }
    fail();
  }

  @Test
  public void unitTest2(){
    Player contact = new PlayerImpl("bigBaller", 0, 0);
    Position check = new PositionImpl(0,0);
    assertEquals(contact.getPosition().getX(), check.getX());
    assertEquals(contact.getPosition().getY(),check.getY());
  }

  @Test
  public void unitTest3(){
    Player contact = new PlayerImpl("bigBaller", 0, 0);
    Inventory check = new InventoryImpl();
    assertEquals(contact.getInventory().getNumItems(), check.getNumItems());
    Inventory bag = new InventoryImpl();
    ItemImpl one = new ItemImpl("one");
    ItemImpl two = new ItemImpl("two");
    ItemImpl three = new ItemImpl("three");
    ItemImpl four = new ItemImpl("four");
    bag.addItem(one);
    bag.addItem(two);
    bag.addItem(three);
    bag.addItem(four);
    contact.getInventory().addItem(one);
    contact.getInventory().addItem(two);
    contact.getInventory().addItem(three);
    contact.getInventory().addItem(four);
    assertEquals(contact.getInventory().getNumItems(), bag.getNumItems());
  }

  @Test
  public void unitTest5(){
    Player contact = new PlayerImpl("bigBaller", 0, 0);
    assertEquals("bigBaller", contact.getName());
  }

  @Test
  public void unitTest6(){
    Player contact = new PlayerImpl("bigBaller", 0, 0);
    Player one = new PlayerImpl("bB1", 5, 5);
    Player two = new PlayerImpl("bB2", -5, -5);
    Player three = new PlayerImpl("bB3", -5, 5);
    Position north = new PositionImpl(0,1);
    Position south = new PositionImpl(-5,-6);
    Position east = new PositionImpl(6,5);
    Position west = new PositionImpl(-6,5);
    contact.move(Direction.NORTH);
    assertEquals(contact.getPosition().getX(), north.getX());
    assertEquals(contact.getPosition().getY(), north.getY());
    one.move(Direction.EAST);
    assertEquals(one.getPosition().getX(), east.getX());
    assertEquals(one.getPosition().getY(), east.getY());
    two.move(Direction.SOUTH);
    assertEquals(two.getPosition().getX(), south.getX());
    assertEquals(two.getPosition().getY(), south.getY());
    three.move(Direction.WEST);
    assertEquals(three.getPosition().getX(), west.getX());
    assertEquals(three.getPosition().getY(), west.getY());

  }
}
