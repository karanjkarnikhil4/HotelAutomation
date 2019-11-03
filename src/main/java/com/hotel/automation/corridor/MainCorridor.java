package com.hotel.automation.corridor;

public class MainCorridor extends Corridor {

  public MainCorridor(int corridorNumber, String corridorName) {
    super(corridorNumber, corridorName);
  }

  @Override
  public int compareTo(Object o) {
    int value = 0;
    Corridor corridor = (Corridor) o;
    value = this.getCorridorNumber() > corridor.getCorridorNumber() ? 1 : -1;
    return value;
  }
}
