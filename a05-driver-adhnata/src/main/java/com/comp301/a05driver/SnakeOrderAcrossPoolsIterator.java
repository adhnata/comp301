package com.comp301.a05driver;

import java.util.*;

public class SnakeOrderAcrossPoolsIterator implements Iterator<Driver> {
    private List<Iterator<Driver>> driverPoolList;
    private int upOrDown;
    private boolean check;

    public SnakeOrderAcrossPoolsIterator(List<Iterable<Driver>> driverPools) {
        if (driverPools == null) {
            throw new IllegalArgumentException();
        }
        this.driverPoolList = new ArrayList<>();
        for ( Iterable<Driver> driverPool : driverPools){
            driverPoolList.add(driverPool.iterator());
        }
        check = true;
        upOrDown = 0;
    }
    @Override
    public boolean hasNext() {
        for (Iterator<Driver> x: driverPoolList){
            if (x.hasNext()) {
                return true;
            }
        }
        return false;
    }


    @Override
  public Driver next() {
        if (!hasNext()){
            throw new NoSuchElementException();
        }
        Driver drivers = null;
        while(drivers == null){
            if(check){
                if(driverPoolList.get(upOrDown).hasNext()){
                    drivers = driverPoolList.get(upOrDown).next();
                }
                upOrDown++;
            }
            if(upOrDown == driverPoolList.size()){
                upOrDown = driverPoolList.size() - 1;
                check = false;
            }

            else if(!check){
                if(driverPoolList.get(upOrDown).hasNext()){
                    drivers = driverPoolList.get(upOrDown).next();
                }
                upOrDown--;
            }
            if(upOrDown == -1){
                upOrDown = 0;
                check = true;
            }

        }
        return drivers;
        }
}