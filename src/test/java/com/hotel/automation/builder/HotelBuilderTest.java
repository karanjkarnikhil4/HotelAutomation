package com.hotel.automation.builder;

import com.hotel.automation.hotel.IHotel;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HotelBuilderTest {

  private HotelBuilder hotelBuilder;
  private IHotelSpecification hotelSpecification;

  @BeforeEach
  public void setUp() {
    hotelSpecification = new HotelSpecification();
    hotelBuilder = new HotelBuilder(hotelSpecification);
  }

  @Test
  public void hotelBuilderTest_GivenHotelSpecification_HotelIsBuiltAccordingly() {
    IHotel hotel = hotelBuilder.getHotel();
    hotel.printStatus();
    Assert.assertEquals(2, hotel.getFloors().size());
    Assert.assertEquals(3, hotel.getFloors().get(1).getCorridorCount());
  }
}
