package com.comp301.a05driver;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ProximityIterator implements Iterator<Driver> {
    private Iterator<Driver> driverPool;
    private Position clientPosition;
    private int proximityRange;
    private Driver nextDriver;
    public ProximityIterator(Iterable<Driver> driverPool, Position clientPosition, int proximityRange){
        if (driverPool == null) {
            throw new IllegalArgumentException("Need to be in an area with drivers.");
        }
        this.driverPool = driverPool.iterator();
        this.clientPosition = clientPosition;
        this.proximityRange = proximityRange;
        checkNextDriver();
    }

    private void checkNextDriver(){
        if (clientPosition == null) {
            throw new IllegalArgumentException("Need Client Position.");
        }

        while(driverPool.hasNext()){
            Driver driver = driverPool.next();
            Position position = driver.getVehicle().getPosition();
            if (position.getManhattanDistanceTo(clientPosition) <= proximityRange){
                nextDriver = driver;
                return;
            }
        }
        nextDriver = null;
    }
    @Override
    public boolean hasNext() {
        if (nextDriver == null) {
            checkNextDriver();
        }
        return nextDriver != null;
    }

    @Override
    public Driver next() {
        if (!hasNext()){
      throw new NoSuchElementException("No more eligible drivers in proximity.");
        }
        Driver result = nextDriver;
        nextDriver = null;
        return result;
    }
}
