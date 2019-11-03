package com.hotel.automation.devices;

import com.hotel.automation.corridor.Corridor;

public abstract class ElectronicDevice {

  protected String name;
  protected Corridor corridor;
  protected boolean canBeCompromised;
  protected boolean isOn;

  public ElectronicDevice(String name) {
    this.name = name;
  }

  public Corridor getCorridor() {
    return corridor;
  }

  public void setCorridor(Corridor corridor) {
    this.corridor = corridor;
  }

  public boolean getCanBeCompromised() {
    return canBeCompromised;
  }

  public void setCanBeConpromised(boolean canBeConpromised) {
    this.canBeCompromised = canBeConpromised;
  }

  public boolean isOn() {
    return isOn;
  }

  public String getName() {
    return name;
  }

  public abstract int getConsumption();

  public abstract void setConsumption(int conumption);

  public abstract void switchOn();

  public abstract void switchOff();

  public abstract void printStatus();
}
