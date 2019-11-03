package com.hotel.automation.hotel;

import com.hotel.automation.address.Address;
import com.hotel.automation.address.IAddress;
import com.hotel.automation.floors.IFloor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HotelTest {

  private IFloor floor1;
  private IFloor floor2;
  private IHotel hotel;
  private IAddress address;

  @BeforeEach
  public void setUp() {
    hotel = new Hotel("Conrad");
    floor1 = Mockito.mock(IFloor.class);
    floor2 = Mockito.mock(IFloor.class);
    Mockito.when(floor1.getFloorNumber()).thenReturn(1);
    Mockito.when(floor2.getFloorNumber()).thenReturn(2);
    address = new Address(1, 2);
    hotel.addFloor(floor1);
    hotel.addFloor(floor2);
  }

  @Test
  public void hotelTest_CallMotionSensedMethod_FloorMotionSensedMethodCalled() {
    hotel.motionSensed(address);
    Mockito.verify(floor1).motionSensed(2);
    Mockito.verify(floor2, Mockito.never()).motionSensed(Mockito.anyInt());
  }

  @Test
  public void hotelControllerTest_CallNoMotionSensedMethod_FloorNoMotionSensedMethodCalled() {
    hotel.noMotionSensed(address);
    Mockito.verify(floor1).noMotionSensed(2);
    Mockito.verify(floor2, Mockito.never()).noMotionSensed(Mockito.anyInt());
  }

  @Test
  public void hotelControllerTest_CallNightModeOnMethod_FloorNightModeOnMethodCalled() {
    hotel.nightModeON();
    Mockito.verify(floor1).nightModeON();
    Mockito.verify(floor2).nightModeON();
  }

  @Test
  public void hotelControllerTest_CallNightModeOFFMethod_floorNightModeOFFMethodCalled() {
    hotel.nightModeOFF();
    Mockito.verify(floor1).nightModeOFF();
    Mockito.verify(floor2).nightModeOFF();
  }

  @Test
  public void hotelControllerTest_CallPrintStatusMethod_floorPrintStatusMethodCalled() {
    hotel.printStatus();
    Mockito.verify(floor1).printStatus();
    Mockito.verify(floor2).printStatus();
  }
}
