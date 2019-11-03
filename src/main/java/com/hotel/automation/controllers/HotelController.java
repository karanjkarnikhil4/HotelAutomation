package com.hotel.automation.controllers;

import com.hotel.automation.address.IAddress;
import com.hotel.automation.hotel.IHotel;
import com.hotel.automation.timers.ITimer;
import com.hotel.automation.timers.MotionTimer;
import com.hotel.automation.timers.NightTimer;
import com.hotel.automation.watch.IWatch;
import com.hotel.automation.watch.Watch;

import java.util.Hashtable;
import java.util.Map;

public class HotelController implements IController {

  private IHotel hotel;
  private IWatch watch;
  private Map<IAddress, ITimer> addressMotionTimerMap;
  private ITimer nightTimer;

  public HotelController(IHotel hotel, IWatch watch) {
    this.hotel = hotel;
    this.watch = watch;
    addressMotionTimerMap = new Hashtable<>();
    nightTimer = new NightTimer(this, watch);
    nightTimer.schedule();
  }

  @Override
  public void motionSensed(IAddress address) {
    if (this.watch.isNightTime()) {
      hotel.motionSensed(address);
      if (addressMotionTimerMap.get(address) != null) {
        addressMotionTimerMap.get(address).schedule();
      } else {
        MotionTimer motionTimer = new MotionTimer(this, address, new Watch());
        addressMotionTimerMap.put(address, motionTimer);
        motionTimer.schedule();
      }
    }
  }

  @Override
  public synchronized void noMotionSensed(IAddress address) {
    hotel.noMotionSensed(address);
  }

  @Override
  public void nightModeON() {
    this.hotel.nightModeON();
  }

  @Override
  public void nightModeOFF() {
    this.hotel.nightModeOFF();
  }

  @Override
  public void printStatus() {
    hotel.printStatus();
  }
}
