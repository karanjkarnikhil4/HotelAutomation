package com.hotel.automation.hotel;

import com.hotel.automation.address.IAddress;
import com.hotel.automation.exceptions.CorridorNotPresentException;
import com.hotel.automation.floors.IFloor;

import java.util.Map;

public interface IHotel {

  Map<Integer, IFloor> getFloors();

  IFloor getFloor(int floorNumber) throws CorridorNotPresentException;

  void addFloor(IFloor floor);

  void motionSensed(IAddress address);

  void noMotionSensed(IAddress address);

  void nightModeON();

  void nightModeOFF();

  void printStatus();
}
