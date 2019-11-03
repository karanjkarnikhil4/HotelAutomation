package com.hotel.automation.floors;

import com.hotel.automation.constants.CorridorMultiplier;
import com.hotel.automation.corridor.Corridor;
import com.hotel.automation.corridor.MainCorridor;
import com.hotel.automation.exceptions.CorridorNotPresentException;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Floor implements IFloor {

  private int floorNumber;
  private Map<Integer, Corridor> corridors;
  private int mainCorridorMultiplier = CorridorMultiplier.MAIN_CORRIDOR_MULTIPLIER;
  private int subCorridorMultiplier = CorridorMultiplier.SUB_CORRIDOR_MULTIPLIER;
  private Queue<Corridor> compromisedCorridors;

  public Floor(int floorNumber) {
    this.floorNumber = floorNumber;
    this.compromisedCorridors = new ConcurrentLinkedQueue<>();
    this.corridors = new Hashtable<>();
  }

  @Override
  public int getCorridorCount() {
    return corridors.size();
  }

  @Override
  public void addCorridor(Corridor corridor) {
    corridors.put(corridor.getCorridorNumber(), corridor);
  }

  @Override
  public int getMainCorridorMultiplier() {
    return mainCorridorMultiplier;
  }

  @Override
  public void setMainCorridorMultiplier(int mainCorridorMultiplier) {
    this.mainCorridorMultiplier = mainCorridorMultiplier;
  }

  @Override
  public int getSubCorridorMultiplier() {
    return subCorridorMultiplier;
  }

  @Override
  public void setSubCorridorMultiplier(int subCorridorMultiplier) {
    this.subCorridorMultiplier = subCorridorMultiplier;
  }

  @Override
  public synchronized void motionSensed(int corridorNumber) {

    this.corridors.get(corridorNumber).motionSensed();
    compromiseCorridors(corridorNumber);
  }

  private void compromiseCorridors(int corridorNumber) {
    if (hasConsumptionExceeded()) {
      turnCompromisibleDevicesOff(corridorNumber);
    }
  }

  private boolean hasConsumptionExceeded() {
    return getConsumption() > consumptionLimit();
  }

  @Override
  public synchronized void noMotionSensed(int corridorNumber) {

    Corridor corridor = this.corridors.get(corridorNumber);
    corridor.noMotionSensed();

    unCompromiseCorridors();
  }

  private void unCompromiseCorridors() {
    while (hasCompromisedCorridors()) {
      Corridor queuedCorridor = compromisedCorridors.remove();

      while (hasCompromisedDevices(queuedCorridor) && isConsumptionLimitInCheck(queuedCorridor)) {
        queuedCorridor.turnOnCompromisedDevice();
      }
    }
  }

  private boolean hasCompromisedDevices(Corridor queuedCorridor) {
    return queuedCorridor.hasCompromisedDevices();
  }

  private boolean hasCompromisedCorridors() {

    return ((!compromisedCorridors.isEmpty())
        && isConsumptionLimitInCheck(compromisedCorridors.peek()));
  }

  private boolean isConsumptionLimitInCheck(Corridor corridor) {

    Optional<Integer> consumption = corridor.getNextCompromisedDeviceConsumption();
    int actualConsumption = consumption.isPresent() ? consumption.get() : 0;
    return actualConsumption + getConsumption() <= consumptionLimit();
  }

  private void turnCompromisibleDevicesOff(int corridorNumberToBeAvoided) {

    Iterator<Integer> corridorIterator = corridors.keySet().iterator();
    while (corridorIterator.hasNext() && hasConsumptionExceeded()) {

      int corridorNumber = corridorIterator.next();
      if (corridorNumber != corridorNumberToBeAvoided) {
        Corridor corridor = corridors.get(corridorNumber);
        while (corridor.hasCompromisableDevices() && hasConsumptionExceeded()) {

          boolean compromised = corridor.turnOffCompromisableDevice();
          if (compromised) {
            compromisedCorridors.add(corridor);
          }
        }
      }
    }
  }

  private int getConsumption() {
    return corridors.values().stream().mapToInt(Corridor::getConsumption).sum();
  }

  private long consumptionLimit() {

    long numberOfMainCorridors = getCountOfMainCorridors();

    long numberOfSubCorridors = this.corridors.values().stream().count() - numberOfMainCorridors;

    return (mainCorridorMultiplier * numberOfMainCorridors)
        + (subCorridorMultiplier * numberOfSubCorridors);
  }

  private long getCountOfMainCorridors() {
    return this.corridors
        .values()
        .stream()
        .filter(corridor -> corridor instanceof MainCorridor)
        .count();
  }

  @Override
  public void nightModeON() {
    this.corridors.values().forEach(Corridor::nightModeON);
    compromiseCorridors(-1);
  }

  @Override
  public void nightModeOFF() {
    this.corridors.values().forEach(Corridor::nightModeOFF);
    unCompromiseCorridors();
  }

  @Override
  public int getFloorNumber() {
    return floorNumber;
  }

  @Override
  public void setFloorNumber(int floorNumber) {
    this.floorNumber = floorNumber;
  }

  @Override
  public Corridor getCorridor(int corridorNumber) throws CorridorNotPresentException {

    if (!corridors.containsKey(corridorNumber)) {
      throw new CorridorNotPresentException(" The corridor your are searching for does not exist");
    }
    return this.corridors.get(corridorNumber);
  }

  @Override
  public void printStatus() {
    System.out.println();
    System.out.print("--------------Floor " + this.getFloorNumber() + "------------------");
    this.corridors.values().stream().sorted().forEach(Corridor::printStatus);
  }

  @Override
  public int compareTo(Object o) {

    int value = 0;
    IFloor floor = (Floor) o;
    value = this.getFloorNumber() > floor.getFloorNumber() ? 1 : -1;
    return value;
  }
}
