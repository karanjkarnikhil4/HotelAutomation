package com.hotel.automation.sensor;

import com.hotel.automation.address.Address;
import com.hotel.automation.address.IAddress;
import com.hotel.automation.controllers.IController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HotelSensorTest {

  private IAddress address;
  private IController hotelController;
  private HotelSensor hotelSensor;

  @BeforeEach
  public void setUp() {

    address = new Address(1, 2);
    hotelController = Mockito.mock(IController.class);
    hotelSensor = new HotelSensor(hotelController);
  }

  @Test
  public void hotelSensorTest_CallMotionSensedMethod_HotelControllerMotionSensedMethodCalled() {
    hotelSensor.motionSensed(address);
    Mockito.verify(hotelController).motionSensed(address);
  }
}
