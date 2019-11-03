package com.hotel.automation.address;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class AddressTest {
  @Test
  public void AddressTest_CompareAddressObjectsWithSameData_AreEqual() {
    Address address1 = new Address(1, 2);
    Address address2 = new Address(1, 2);

    Assert.assertEquals(address1, address2);
  }

  @Test
  public void AddressTest_CompareAddressObjectsWithDiffData_AreNotEqual() {
    Address address1 = new Address(1, 2);
    Address address2 = new Address(2, 1);

    Assert.assertNotEquals(address1, address2);
  }

  @Test
  public void AddressTest__CompareHashCodeOfDiffAddressObjects_AreNotEqual() {
    Address address1 = new Address(1, 2);
    Address address2 = new Address(2, 1);

    Assert.assertNotEquals(address1.hashCode(), address2.hashCode());
  }

  @Test
  public void AddressTest__CompareHashCodeOfDiffAddressObjects_AreEqual() {
    Address address1 = new Address(1, 2);
    Address address2 = new Address(1, 2);

    Assert.assertEquals(address1.hashCode(), address2.hashCode());
  }
}
