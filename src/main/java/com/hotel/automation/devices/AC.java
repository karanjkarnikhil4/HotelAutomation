package com.hotel.automation.devices;

import com.hotel.automation.constants.DefaultConsumption;

public class AC extends ElectronicDevice {

  private int consumption = DefaultConsumption.AC;

  public AC(String name) {
    super(name);
    this.switchOn();
  }

  @Override
  public int getConsumption() {
    return consumption;
  }

  @Override
  public void setConsumption(int consumption) {
    this.consumption = consumption;
  }

  public void switchOn() {
    isOn = true;
  }

  public void switchOff() {
    isOn = false;
  }

  @Override
  public void printStatus() {
    System.out.print(" AC " + this.name + " " + (this.isOn ? "ON" : "OFF"));
  }
}
