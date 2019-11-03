package com.hotel.automation.controllers;

import com.hotel.automation.address.IAddress;

public interface IController {

  void motionSensed(IAddress address);

  void noMotionSensed(IAddress address);

  void nightModeON();

  void nightModeOFF();

  void printStatus();
}
