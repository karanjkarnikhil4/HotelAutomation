package com.hotel.automation.corridor;

public class SubCorridor extends Corridor {

  public SubCorridor(int corridorNumber, String name) {
    super(corridorNumber, name);
  }

  @Override
  public int compareTo(Object o) {
    int value = 0;
    Corridor corridor = (Corridor) o;
    value = this.getCorridorNumber() > corridor.getCorridorNumber() ? 1 : -1;
    return value;
  }
}
