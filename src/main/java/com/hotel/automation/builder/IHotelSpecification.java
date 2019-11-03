package com.hotel.automation.builder;

public interface IHotelSpecification {
  int getNoOfMainCorridorsPerFloor();

  IHotelSpecification setNoOfMainCorridorsPerFloor(int numberOfMainCorridorsPerFloor);

  String getHotelName();

  IHotelSpecification setHotelName(String hotelName);

  int getNoOfFloors();

  IHotelSpecification setNoOfFloors(int numberOfFloors);

  IHotelSpecification setNoOfCorridorsPerFloor(int numberOfCorridorsPerFloor);

  int getNoOfLightsPerCorridor();

  IHotelSpecification setNoOfLightsInEachCorridor(int numberOfLightsInEachCorridor);

  int getNoOfACPerCorridor();

  IHotelSpecification setNoOfACInEachCorridor(int numberOfACInEachCorridor);

  int getNoOfSubCorridorsPerFloor();

  IHotelSpecification setNoOfSubCorridorsPerFloor(int numberOfSubCorridorsPerFloor);
}
