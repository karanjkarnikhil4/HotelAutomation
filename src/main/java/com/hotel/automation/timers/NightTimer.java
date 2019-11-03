package com.hotel.automation.timers;

import com.hotel.automation.controllers.IController;
import com.hotel.automation.watch.IWatch;

public class NightTimer implements ITimer {

  private IWatch watch;

  private IController controller;

  public NightTimer(IController controller, IWatch watch) {

    this.controller = controller;
    this.watch = watch;
  }

  @Override
  public void schedule() {

    if (watch.isNightTime()) {
      this.controller.nightModeON();
    }

    watch.schedule(watch.getStartTime(), () -> controller.nightModeON());

    watch.schedule(watch.getEndTime(), () -> controller.nightModeOFF());
  }
}
