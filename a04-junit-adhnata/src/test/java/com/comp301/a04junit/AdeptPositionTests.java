package com.comp301.a04junit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import com.comp301.a04junit.adventure.Direction;
import com.comp301.a04junit.adventure.Position;
import com.comp301.a04junit.adventure.PositionImpl;

import org.junit.Test;

/** Write unit tests for the PositionImpl class here */
public class AdeptPositionTests {
  @Test
  public void unitTest1(){
    Position origin = new PositionImpl(0,0);
    assertTrue(origin.getX() == 0 && origin.getY() == 0);
  }

  @Test
  public void unitTest2(){
    Position origin = new PositionImpl(0,0);
    Position pos = new PositionImpl(2,2);
    Position neg = new PositionImpl(-2,2);
    assertTrue(origin.getX() == 0 && pos.getX() == 2 && neg.getX() == -2);
  }

  @Test
  public void unitTest3(){
    Position origin = new PositionImpl(0,0);
    Position pos = new PositionImpl(2,2);
    Position neg = new PositionImpl(-2,2);
    assertTrue(origin.getY() == 0 && pos.getY() == 2 && neg.getY() == 2);
  }

  @Test
  public void unitTest4(){
    Position origin = new PositionImpl(0,0);
    Position check = new PositionImpl(0,0);
    Position north = new PositionImpl(0,1);
    Position south = new PositionImpl(0,-1);
    Position east = new PositionImpl(1,0);
    Position west = new PositionImpl(-1,0);
    assertEquals(origin.getNeighbor(Direction.NORTH).getX(), north.getX());
    assertEquals(origin.getNeighbor(Direction.NORTH).getY(), north.getY());
    assertEquals(origin.getX(), check.getX());
    assertEquals(origin.getY(), check.getY());
    assertEquals(origin.getNeighbor(Direction.SOUTH).getX(), south.getX());
    assertEquals(origin.getNeighbor(Direction.SOUTH).getY(), south.getY());
    assertEquals(origin.getX(), check.getX());
    assertEquals(origin.getY(), check.getY());
    assertEquals(origin.getNeighbor(Direction.WEST).getX(), west.getX());
    assertEquals(origin.getNeighbor(Direction.WEST).getY(), west.getY());
    assertEquals(origin.getX(), check.getX());
    assertEquals(origin.getY(), check.getY());
    assertEquals(origin.getNeighbor(Direction.EAST).getX(), east.getX());
    assertEquals(origin.getNeighbor(Direction.EAST).getY(), east.getY());
    assertEquals(origin.getX(), check.getX());
    assertEquals(origin.getY(), check.getY());
  }
  @Test
  public void unitTest5(){
    Position one = new PositionImpl(30, 30);
    Position two = new PositionImpl(12, -35);
    Position three = new PositionImpl(-17, 45);
    Position a = new PositionImpl(-17, -45);
    assertEquals(30, one.getNeighbor(Direction.NORTH).getX());
    assertEquals(31, one.getNeighbor(Direction.NORTH).getY());
    assertEquals(-16, three.getNeighbor(Direction.EAST).getX());
    assertEquals(45, three.getNeighbor(Direction.EAST).getY());
    assertEquals(-17, a.getNeighbor(Direction.SOUTH).getX());
    assertEquals(-46, a.getNeighbor(Direction.SOUTH).getY());
    assertEquals(11, two.getNeighbor(Direction.WEST).getX());
    assertEquals(-35, two.getNeighbor(Direction.WEST).getY());
    assertEquals(30, one.getX());
    assertEquals(30, one.getY());

  }
}
