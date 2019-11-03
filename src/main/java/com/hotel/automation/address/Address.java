package com.hotel.automation.address;

public class Address implements IAddress {
  private int floorNumber;
  private int corridorNumber;

  public Address(int floorNumber, int corridorNumber) {
    this.floorNumber = floorNumber;
    this.corridorNumber = corridorNumber;
  }

  @Override
  public int getCorridorNumber() {
    return corridorNumber;
  }

  @Override
  public void setCorridorNumber(int corridorNumber) {
    this.corridorNumber = corridorNumber;
  }

  @Override
  public int getFloorNumber() {
    return floorNumber;
  }

  @Override
  public void setFloorNumber(int floorNumber) {
    this.floorNumber = floorNumber;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Address)) {
      return false;
    }

    Address address = (Address) obj;

    return address.getCorridorNumber() == this.corridorNumber
        && address.getFloorNumber() == this.getFloorNumber();
  }

  @Override
  public int hashCode() {
    final int primeFloorMultiplier = 31;
    final int primeCorridorMultiplier = 47;
    int result = 1;
    result =
        result + (floorNumber * primeFloorMultiplier) + (corridorNumber * primeCorridorMultiplier);
    return result;
  }
}
