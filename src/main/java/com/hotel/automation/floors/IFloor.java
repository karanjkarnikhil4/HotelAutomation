package com.hotel.automation.floors;

import com.hotel.automation.corridor.Corridor;
import com.hotel.automation.exceptions.CorridorNotPresentException;

public interface IFloor extends Comparable {

  int getSubCorridorMultiplier();

  void setSubCorridorMultiplier(int subCorridorMultiplier);

  int getCorridorCount();

  void addCorridor(Corridor corridor);

  int getMainCorridorMultiplier();

  void setMainCorridorMultiplier(int mainCorridorMultiplier);

  void motionSensed(int corridorNumber);

  void noMotionSensed(int corridorNumber);

  void nightModeON();

  void nightModeOFF();

  int getFloorNumber();

  void setFloorNumber(int floorNumber);

  Corridor getCorridor(int corridorNumber) throws CorridorNotPresentException;

  void printStatus();
}
