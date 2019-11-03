package com.hotel.automation.constants;

public class HotelSpecificationDefaults {
  public static final int NO_OF_FLOORS = 2;
  public static final int NO_OF_MAIN_CORRIDORS_PER_FLOOR = 1;
  public static final int NO_OF_SUB_CORRIDORS_PER_FLOOR = 2;
  public static final int NUMBER_OF_LIGHTS_PER_CORRIDOR = 1;
  public static final int NO_OF_ACS_PER_CORRIDOR = 1;

  private HotelSpecificationDefaults() {
    throw new IllegalStateException("HotelSpecificationDefaults class");
  }
}
