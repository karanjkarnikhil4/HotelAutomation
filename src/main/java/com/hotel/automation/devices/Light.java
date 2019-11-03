package com.hotel.automation.devices;

import com.hotel.automation.constants.DefaultConsumption;

public class Light extends ElectronicDevice {

  private int consumption = DefaultConsumption.LIGHT;

  public Light(String name) {
    super(name);
  }

  public void switchOn() {
    isOn = true;
  }

  public void switchOff() {
    isOn = false;
  }

  @Override
  public void printStatus() {
    System.out.print(" Light " + this.name + " " + (this.isOn ? "ON" : "OFF"));
  }

  @Override
  public int getConsumption() {
    return consumption;
  }

  @Override
  public void setConsumption(int consumption) {
    this.consumption = consumption;
  }
}
