package com.hotel.automation.timers;

import com.hotel.automation.address.Address;
import com.hotel.automation.address.IAddress;
import com.hotel.automation.controllers.IController;
import com.hotel.automation.watch.IWatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.TimerTask;

public class MotionTimerTest {

  private IAddress address;
  private IController hotelController;
  private ITimer motionTimer;
  private IWatch watch;

  @BeforeEach
  public void setUp() {

    address = new Address(1, 2);
    hotelController = Mockito.mock(IController.class);
    watch = Mockito.mock(IWatch.class);
    motionTimer = new MotionTimer(hotelController, address, watch);
  }

  @Test
  public void motionTimerTest_CallNoMotionSensedMethod_HotelControllerNoMotionSensedMethodCalled() {
    motionTimer.schedule();
    Mockito.verify(watch).schedule(Mockito.any(TimerTask.class), Mockito.any(int.class));
  }
}
