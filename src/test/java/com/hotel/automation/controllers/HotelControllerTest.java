package com.hotel.automation.controllers;

import com.hotel.automation.address.Address;
import com.hotel.automation.address.IAddress;
import com.hotel.automation.hotel.IHotel;
import com.hotel.automation.watch.IWatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HotelControllerTest {

  private IWatch watch;
  private IHotel hotel;
  private IAddress address;
  private IController hotelController;

  @BeforeEach
  public void setUp() {
    watch = Mockito.mock(IWatch.class);
    hotel = Mockito.mock(IHotel.class);
    Mockito.when(watch.isNightTime()).thenReturn(true);
    address = new Address(1, 2);
    hotelController = new HotelController(hotel, watch);
  }

  @Test
  public void hotelControllerTest_CallMotionSensedMethod_HotelMotionSensedMethodCalled() {
    hotelController.motionSensed(address);
    Mockito.verify(hotel).motionSensed(address);
  }

  @Test
  public void hotelControllerTest_CallNoMotionSensedMethod_HoteNoMotionSensedMethodCalled() {
    hotelController.noMotionSensed(address);
    Mockito.verify(hotel).noMotionSensed(address);
  }

  @Test
  public void hotelControllerTest_CallNightModeOnMethod_HotelNightModeOnMethodCalled() {
    hotelController.nightModeON();
    Mockito.verify(hotel,Mockito.times(2)).nightModeON();
  }

  @Test
  public void hotelControllerTest_CallNightModeOFFMethod_HotelNightModeOFFMethodCalled() {
    hotelController.nightModeOFF();
    Mockito.verify(hotel).nightModeOFF();
  }

  @Test
  public void hotelControllerTest_CallPrintStatusMethod_HotelPrintStatusMethodCalled() {
    hotelController.printStatus();
    Mockito.verify(hotel).printStatus();
  }
}
