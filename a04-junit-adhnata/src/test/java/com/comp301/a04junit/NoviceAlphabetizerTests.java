package com.comp301.a04junit;
import static org.junit.Assert.*;

import static org.junit.Assert.assertTrue;

import com.comp301.a04junit.alphabetizer.Alphabetizer;

import org.junit.Test;

import java.util.NoSuchElementException;

/** Write tests for the Alphabetizer class here */
public class NoviceAlphabetizerTests {
  @Test
  public void unitTest1() {
    String[] originalArray = {"durian", "banana", "apple", "cherry"};
    Alphabetizer alpha = new Alphabetizer(originalArray);
    assertEquals("apple", alpha.next());
    assertEquals("banana", alpha.next());
    assertEquals("cherry", alpha.next());
    assertEquals("durian", alpha.next());
    try {
      alpha.next();
      fail();
    } catch (NoSuchElementException e) {
      return;
    }
    fail();
  }

  @Test
  public void unitTest2() {
    String[] originalArray = {"durian", "Banana", "Apple", "cherry"};
    Alphabetizer alpha = new Alphabetizer(originalArray);
    assertTrue(alpha.hasNext());
    assertEquals("Apple", alpha.next());
    assertTrue(alpha.hasNext());
    assertEquals("Banana", alpha.next());
    assertTrue(alpha.hasNext());
    assertEquals("cherry", alpha.next());
    assertTrue(alpha.hasNext());
    assertEquals("durian", alpha.next());
    assertFalse(alpha.hasNext());
  }

  @Test
  public void unitTest3() {
    String[] originalArray = {"durian", "banana", "apple", "cherry"};
    Alphabetizer alpha = new Alphabetizer(originalArray);
    assertEquals(4, originalArray.length);
    assertEquals("apple", originalArray[2]);
    assertEquals("banana", originalArray[1]);
    assertEquals("cherry", originalArray[3]);
    assertEquals("durian", originalArray[0]);

    assertEquals("apple", alpha.next());
    assertEquals("banana", alpha.next());
    assertEquals("cherry", alpha.next());
    assertEquals("durian", alpha.next());
  }

  @Test
  public void UnitTest4() {
    try {
      Alphabetizer noSpace = new Alphabetizer(null);
      return;
    } catch (RuntimeException e) {
      fail();
    }
  }
}
