package com.hotel.automation.application;

import com.hotel.automation.address.Address;
import com.hotel.automation.builder.HotelBuilder;
import com.hotel.automation.builder.HotelSpecification;
import com.hotel.automation.builder.IHotelSpecification;
import com.hotel.automation.constants.CutOffTime;
import com.hotel.automation.controllers.HotelController;
import com.hotel.automation.controllers.IController;
import com.hotel.automation.hotel.IHotel;
import com.hotel.automation.sensor.HotelSensor;
import com.hotel.automation.sensor.ISensor;
import com.hotel.automation.watch.IWatch;
import com.hotel.automation.watch.Watch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class ApplicationTest {
  private HotelBuilder hotelBuilder;
  private IHotelSpecification hotelSpecification;
  private ISensor sensor;
  private IController hotelController;
  private IWatch watch;
  private IHotel hotel;

  @BeforeEach
  public void setUp() {}

  @Test
  public void applicationTest_SimulateMotionOnGroundFloorSubCorridor1_SubCorridor1LightTurnedON()
      throws InterruptedException {
    hotelSpecification = new HotelSpecification();
    hotelBuilder = new HotelBuilder(hotelSpecification);
    hotel = hotelBuilder.getHotel();
    watch = new Watch();
    hotelController = new HotelController(hotel, watch);
    sensor = new HotelSensor(hotelController);

    System.out.print("***********Before Motion***********");
    System.out.println();
    hotelController.printStatus();
    sensor.motionSensed(new Address(0, 1));
    System.out.println();
    System.out.println();
    System.out.println("***********After Motion***********");
    hotelController.printStatus();
    TimeUnit.SECONDS.sleep(CutOffTime.TIME_IN_SECONDS);
    System.out.println();
    System.out.println();
    System.out.println("***********After 5 Seconds***********");
    hotelController.printStatus();
  }

  @Test
  public void applicationTest_SimulateNightMode_AllMainCorridorLightsTurnedON()
      throws InterruptedException {
    hotelSpecification = new HotelSpecification();
    hotelBuilder = new HotelBuilder(hotelSpecification);
    hotel = hotelBuilder.getHotel();
    watch = new Watch();
    hotelController = new HotelController(hotel, watch);
    sensor = new HotelSensor(hotelController);
    hotelController.nightModeON();
    hotel.printStatus();
  }

  @Test
  public void applicationTest_SimulateMotionOnALLSubCorridors__AllSubCorridorLightsTurnedON()
      throws InterruptedException {
    hotelSpecification = new HotelSpecification();
    hotelBuilder = new HotelBuilder(hotelSpecification);
    hotel = hotelBuilder.getHotel();
    watch = new Watch();
    hotelController = new HotelController(hotel, watch);
    sensor = new HotelSensor(hotelController);
    hotelController.nightModeON();

    System.out.print("***********Before Motion***********");
    System.out.println();
    hotelController.printStatus();
    sensor.motionSensed(new Address(0, 1));
    sensor.motionSensed(new Address(0, 2));
    sensor.motionSensed(new Address(1, 1));
    sensor.motionSensed(new Address(1, 2));
    System.out.println();
    System.out.println();
    System.out.println("***********After Motion***********");
    hotelController.printStatus();
    TimeUnit.SECONDS.sleep(CutOffTime.TIME_IN_SECONDS);
    System.out.println();
    System.out.println();
    System.out.println("***********After 5 Seconds***********");
    hotelController.printStatus();
  }

  @Test
  public void
      applicationTest_SimulateMotionOnALLSubCorridors__AllSubCorridorLightsTurnedONAndACOFF()
          throws InterruptedException {

    hotelSpecification = new HotelSpecification();
    hotelSpecification.setNoOfLightsInEachCorridor(2);
    hotelBuilder = new HotelBuilder(hotelSpecification);
    hotel = hotelBuilder.getHotel();

    watch = new Watch();
    hotelController = new HotelController(hotel, watch);
    sensor = new HotelSensor(hotelController);
    hotelController.nightModeON();

    System.out.print("***********Before Motion***********");
    System.out.println();
    hotelController.printStatus();
    sensor.motionSensed(new Address(0, 1));
    sensor.motionSensed(new Address(0, 2));
    sensor.motionSensed(new Address(1, 1));
    sensor.motionSensed(new Address(1, 2));
    System.out.println();
    System.out.println();
    System.out.println("***********After Motion***********");
    hotelController.printStatus();
    TimeUnit.SECONDS.sleep(CutOffTime.TIME_IN_SECONDS);
    System.out.println();
    System.out.println();
    System.out.println("***********After 5 Seconds***********");
    hotelController.printStatus();
  }
}
