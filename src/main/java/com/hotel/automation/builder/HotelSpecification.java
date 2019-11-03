package com.hotel.automation.builder;

public class HotelSpecification implements IHotelSpecification {

  private int noOfMainCorridorsPerFloor = HotelSpecificationDefaults.NO_OF_MAIN_CORRIDORS_PER_FLOOR;
  private int noOfSubCorridorsPerFloor = HotelSpecificationDefaults.NO_OF_SUB_CORRIDORS_PER_FLOOR;
  private int noOfFloors = HotelSpecificationDefaults.NO_OF_FLOORS;
  private int noOfLightsPerCorridor = HotelSpecificationDefaults.NUMBER_OF_LIGHTS_PER_CORRIDOR;
  private int noOfACPerCorridor = HotelSpecificationDefaults.NO_OF_ACS_PER_CORRIDOR;
  private String hotelName;

  @Override
  public int getNoOfMainCorridorsPerFloor() {
    return noOfMainCorridorsPerFloor;
  }

  @Override
  public IHotelSpecification setNoOfMainCorridorsPerFloor(int numberOfMainCorridorsPerFloor) {
    this.noOfMainCorridorsPerFloor = numberOfMainCorridorsPerFloor;
    return this;
  }

  @Override
  public String getHotelName() {
    return hotelName;
  }

  @Override
  public IHotelSpecification setHotelName(String hotelName) {
    this.hotelName = hotelName;
    return this;
  }

  @Override
  public int getNoOfFloors() {
    return noOfFloors;
  }

  @Override
  public IHotelSpecification setNoOfFloors(int numberOfFloors) {
    this.noOfFloors = numberOfFloors;
    return this;
  }

  @Override
  public IHotelSpecification setNoOfCorridorsPerFloor(int numberOfCorridorsPerFloor) {
    this.noOfMainCorridorsPerFloor = numberOfCorridorsPerFloor;
    return this;
  }

  @Override
  public int getNoOfLightsPerCorridor() {
    return noOfLightsPerCorridor;
  }

  @Override
  public IHotelSpecification setNoOfLightsInEachCorridor(int numberOfLightsInEachCorridor) {
    this.noOfLightsPerCorridor = numberOfLightsInEachCorridor;
    return this;
  }

  @Override
  public int getNoOfACPerCorridor() {
    return noOfACPerCorridor;
  }

  @Override
  public IHotelSpecification setNoOfACInEachCorridor(int numberOfACInEachCorridor) {
    this.noOfACPerCorridor = numberOfACInEachCorridor;
    return this;
  }

  @Override
  public int getNoOfSubCorridorsPerFloor() {
    return noOfSubCorridorsPerFloor;
  }

  @Override
  public IHotelSpecification setNoOfSubCorridorsPerFloor(int numberOfSubCorridorsPerFloor) {
    this.noOfSubCorridorsPerFloor = numberOfSubCorridorsPerFloor;
    return this;
  }
}
