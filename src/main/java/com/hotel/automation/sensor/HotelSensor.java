package com.hotel.automation.sensor;

import com.hotel.automation.address.IAddress;
import com.hotel.automation.controllers.IController;

public class HotelSensor implements ISensor {

  private IController controller;

  public HotelSensor(IController controller) {

    this.controller = controller;
  }

  @Override
  public void motionSensed(IAddress address) {

    controller.motionSensed(address);
  }
}
