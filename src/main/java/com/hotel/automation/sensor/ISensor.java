package com.hotel.automation.sensor;

import com.hotel.automation.address.IAddress;

public interface ISensor {

  void motionSensed(IAddress address);
}
