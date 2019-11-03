package com.hotel.automation.corridor;

import com.hotel.automation.commands.ICommand;
import com.hotel.automation.devices.ElectronicDevice;
import com.hotel.automation.floors.Floor;

import java.util.*;

public abstract class Corridor implements Comparable {

  protected List<ElectronicDevice> devices;
  private int corridorNumber;
  private String corridorName;
  private List<ElectronicDevice> compromisableDevices;
  private Queue<ElectronicDevice> compromisedDevices;
  private ICommand motionSensedCommand;
  private ICommand nightModeCommand;
  private Floor floor;

  public Corridor(int corridorNumber, String corridorName) {
    this.corridorNumber = corridorNumber;
    this.corridorName = corridorName;
    devices = new ArrayList<>();
    compromisableDevices = new ArrayList<>();
    compromisedDevices = new LinkedList<>();
  }

  public String getCorridorName() {
    return corridorName;
  }

  public Floor getFloor() {
    return floor;
  }

  public void setFloor(Floor floor) {
    this.floor = floor;
  }

  public void setMotionSensedCommand(ICommand motionSensedCommand) {
    this.motionSensedCommand = motionSensedCommand;
  }

  public void setNightModeCommand(ICommand nightModeCommand) {
    this.nightModeCommand = nightModeCommand;
  }

  public int getCorridorNumber() {
    return corridorNumber;
  }

  public void setCorridorNumber(int corridorNumber) {
    this.corridorNumber = corridorNumber;
  }

  public void motionSensed() {
    this.motionSensedCommand.execute();
  }

  public void noMotionSensed() {
    this.motionSensedCommand.undo();
  }

  public void nightModeON() {
    this.nightModeCommand.execute();
  }

  public void nightModeOFF() {
    this.nightModeCommand.undo();
  }

  public int getConsumption() {
    return devices.stream()
        .filter(ElectronicDevice::isOn)
        .mapToInt(ElectronicDevice::getConsumption)
        .sum();
  }

  public void turnOnCompromisedDevice() {
    if (hasCompromisedDevices()) {
      ElectronicDevice electronicDevice = compromisedDevices.remove();
      electronicDevice.switchOn();
      compromisableDevices.add(electronicDevice);
    }
  }

  public boolean turnOffCompromisableDevice() {
    boolean compromised = false;
    if (hasCompromisableDevices()) {
      ElectronicDevice electronicDevice = compromisableDevices.get(0);
      if (electronicDevice.isOn()) {
        electronicDevice.switchOff();
        compromisedDevices.add(electronicDevice);
        compromisableDevices.remove(electronicDevice);
        compromised = true;
      }
    }
    return compromised;
  }

  public boolean hasCompromisedDevices() {
    return !compromisedDevices.isEmpty();
  }

  public boolean hasCompromisableDevices() {
    return !compromisableDevices.isEmpty();
  }

  public void addDevice(ElectronicDevice electronicDevice) {
    electronicDevice.setCorridor(this);
    devices.add(electronicDevice);
    if (electronicDevice.getCanBeCompromised()) {
      compromisableDevices.add(electronicDevice);
    }
  }

  public Optional<Integer> getNextCompromisedDeviceConsumption() {
    Optional<Integer> consumption = Optional.empty();
    if (!this.compromisedDevices.isEmpty()) {
      ElectronicDevice electronicDevice = this.compromisedDevices.peek();
      consumption = Optional.of(electronicDevice.getConsumption());
    }
    return consumption;
  }

  public void printStatus() {
    System.out.println();
    System.out.print(this.corridorName + " " + this.getCorridorNumber() + " ");
    devices.forEach(ElectronicDevice::printStatus);
  }
}
