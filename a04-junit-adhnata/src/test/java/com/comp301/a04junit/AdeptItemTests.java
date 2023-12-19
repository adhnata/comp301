package com.comp301.a04junit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;


import com.comp301.a04junit.adventure.Item;
import com.comp301.a04junit.adventure.ItemImpl;

import org.junit.Test;

/** Write unit tests for the ItemImpl class here */
public class  AdeptItemTests {
  @Test
  public void unitTest1() {
    Item one = new ItemImpl("diamond");
    Item two = new ItemImpl("ruby");
    assertEquals("diamond", one.getName());
    assertEquals("ruby", two.getName());
    try{
      Item three = new ItemImpl(null);
      fail();
    }
    catch(RuntimeException e){
      return;
    }
  }
  @Test
  public void unitTest2(){
    Item one = new ItemImpl("Diamond");
    Item two = new ItemImpl("Diamond");
    assertEquals(one, two);
  }
  @Test
  public void UnitTest3(){
    Item one = new ItemImpl("ruby");
    Item two = new ItemImpl("ruby");
    assertEquals(one, two);
  }
  @Test
  public void UnitTest4(){
    Item one = new ItemImpl("diamond");
    Item two = new ItemImpl("ruby");
    assertTrue(one.getName().equals("diamond") && two.getName().equals("ruby"));
  }
  @Test
  public void UnitTest5(){
    Item one = new ItemImpl("Diamond");
    Item two = new ItemImpl("diamond");
    assertEquals("Diamond", one.toString());
    assertEquals("diamond", two.toString());

  }

}
