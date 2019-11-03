package com.hotel.automation.hotel;

import com.hotel.automation.address.IAddress;
import com.hotel.automation.floors.IFloor;

import java.util.Hashtable;
import java.util.Map;

public class Hotel implements IHotel {

  private String name;
  private Map<Integer, IFloor> floors;

  public Hotel(String name) {
    this.name = name;
    floors = new Hashtable<>();
  }

  @Override
  public Map<Integer, IFloor> getFloors() {
    return floors;
  }

  @Override
  public IFloor getFloor(int floorNumber) {
    return this.floors.get(floorNumber);
  }

  @Override
  public void addFloor(IFloor floor) {
    floors.put(floor.getFloorNumber(), floor);
  }

  @Override
  public void motionSensed(IAddress address) {

    floors.get(address.getFloorNumber()).motionSensed(address.getCorridorNumber());
  }

  @Override
  public void noMotionSensed(IAddress address) {

    floors.get(address.getFloorNumber()).noMotionSensed(address.getCorridorNumber());
  }

  @Override
  public void nightModeON() {
    floors.values().forEach(IFloor::nightModeON);
  }

  @Override
  public void nightModeOFF() {
    floors.values().forEach(IFloor::nightModeOFF);
  }

  @Override
  public void printStatus() {
    this.floors.values().stream().sorted().forEach(IFloor::printStatus);
  }

  public String getName() {
    return name;
  }
}
