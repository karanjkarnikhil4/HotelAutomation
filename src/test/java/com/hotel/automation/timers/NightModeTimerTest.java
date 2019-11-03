package com.hotel.automation.timers;

import com.hotel.automation.controllers.IController;
import com.hotel.automation.watch.IWatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class NightModeTimerTest {

  private IController hotelController;
  private ITimer nightTimer;
  private IWatch watch;

  @BeforeEach
  public void setUp() {

    hotelController = Mockito.mock(IController.class);
    watch = Mockito.mock(IWatch.class);
    nightTimer = new NightTimer(hotelController, watch);
  }

  @Test
  public void
      nightTimerTest_ScheduleNightModeMethod_HotelControllerNightModeMethodGetsCalledAtNight() {
    nightTimer.schedule();
    Mockito.verify(watch, Mockito.times(2)).schedule(Mockito.any(), Mockito.any());
  }
}
