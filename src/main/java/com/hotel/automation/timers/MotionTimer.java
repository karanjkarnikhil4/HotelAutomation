package com.hotel.automation.timers;

import com.hotel.automation.address.IAddress;
import com.hotel.automation.constants.CutOffTime;
import com.hotel.automation.controllers.IController;
import com.hotel.automation.watch.IWatch;

import java.util.TimerTask;

public class MotionTimer implements ITimer {

  private IAddress address;
  private IWatch watch;
  private IController controller;
  private volatile boolean hasStarted;
  private int cutOfTimeInSeconds = CutOffTime.TIME_IN_SECONDS;

  public MotionTimer(IController controller, IAddress address, IWatch watch) {

    this.controller = controller;
    this.address = address;
    this.watch = watch;
  }

  @Override
  public void schedule() {

    if (this.hasRunStarted()) {
      watch.cancel();
    }

    watch.schedule(
        new TimerTask() {
          @Override
          public void run() {
            hasStarted = true;
            controller.noMotionSensed(address);
          }
        },
        getCutOfTimeInSeconds());
  }

  public boolean hasRunStarted() {
    return this.hasStarted;
  }

  public int getCutOfTimeInSeconds() {
    return cutOfTimeInSeconds;
  }

  public void setCutOfTimeInSeconds(int cutOfTimeInSeconds) {
    this.cutOfTimeInSeconds = cutOfTimeInSeconds;
  }
}
