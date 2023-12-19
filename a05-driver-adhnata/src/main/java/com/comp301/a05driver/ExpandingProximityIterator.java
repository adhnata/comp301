package com.comp301.a05driver;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ExpandingProximityIterator implements Iterator<Driver> {
  private Iterable<Driver> drivers;
  private int index;
  private Iterator<Driver> driverPool;
  private final Position clientPosition;
  private final int expansionStep;
  private Driver nextDriver;
  private boolean check;



  public ExpandingProximityIterator(
      Iterable<Driver> driverPool, Position clientPosition, int expansionStep) {
    if(driverPool == null || clientPosition == null || expansionStep <= 0){
      throw new IllegalArgumentException();
    }
    this.clientPosition = clientPosition;
    this.expansionStep = expansionStep;
    this.driverPool = driverPool.iterator();
    nextDriver = null;
    check = false;
    drivers = driverPool;
    index = 0;
  }

  @Override
  public boolean hasNext() {
    checkNextDriver();
    return this.nextDriver != null;}

  @Override
  public Driver next() {
    if(this.hasNext()){
      Driver b = this.nextDriver;
      this.nextDriver = null;
      return b;
    }
    else{
      throw new NoSuchElementException();
    }
  }

  public void checkNextDriver() {
    while(this.nextDriver == null){
      while(this.nextDriver == null && this.driverPool.hasNext()){
        this.inLine(index);
      }
      if (this.nextDriver != null){
        return;
      }
      if(!this.driverPool.hasNext() && !check){
        return;
      }
      else{
        this.driverPool = this.drivers.iterator();
        index++;
        check = false;
      }
    }
  }

  private void inLine(int line){
    Driver a = this.driverPool.next();
    double dist = a.getVehicle().getPosition().getManhattanDistanceTo(this.clientPosition);
    double prev = 1 + (line - 1) * this.expansionStep;
    double next = 1 + line * this.expansionStep;
    if ((line == 0) && (dist <= next && prev < dist)) {
      this.nextDriver = a;
    }
    else if(dist <= next && prev < dist){
      this.nextDriver = a;
    }
    else if(dist > next){
      check = true;
    }
  }


}


