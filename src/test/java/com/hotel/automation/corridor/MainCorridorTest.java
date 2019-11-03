package com.hotel.automation.corridor;

import com.hotel.automation.commands.*;
import com.hotel.automation.devices.ElectronicDevice;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MainCorridorTest {

  private ICommand[] commands;
  private ElectronicDevice light;
  private ElectronicDevice ac;
  private Corridor mainCorridor;

  @BeforeEach
  public void setUp() {
    setupElectronicDevices();
    setupMainCorridor();
  }

  @Test
  public void mainCorridorTest_CallMotionSensedMethod_NoDeviceSwitchONMethodsCalled() {
    mainCorridor.motionSensed();
    Mockito.verify(light, Mockito.never()).switchOn();
    Mockito.verify(ac, Mockito.never()).switchOn();
  }

  @Test
  public void mainCorridorTest_CallNoMotionSensedMethod_NoDeviceSwitchOFFMethodsCalled() {
    mainCorridor.noMotionSensed();
    Mockito.verify(light, Mockito.never()).switchOff();
    Mockito.verify(ac, Mockito.never()).switchOff();
  }

  @Test
  public void mainCorridorTest_CallNightModeONMethod_LightDeviceSwitchONMethodsCalled() {
    mainCorridor.nightModeON();
    Mockito.verify(light).switchOn();
    Mockito.verify(ac).switchOn();
  }

  @Test
  public void mainCorridorTest_CallNightModeOFFMethod_LightDeviceSwitchOFFMethodsCalled() {
    mainCorridor.nightModeOFF();
    Mockito.verify(light).switchOff();
    Mockito.verify(ac).switchOff();
  }

  @Test
  public void mainCorridorTest_CallprintStatusMethod_DevicesPrintStatusCalled() {
    mainCorridor.printStatus();
    Mockito.verify(light).printStatus();
    Mockito.verify(ac).printStatus();
  }

  @Test
  public void mainCorridorTest_CallTurnOffCompromisedDevices_NoDeviceSwitchOffMethodCalled() {
    mainCorridor.turnOffCompromisableDevice();
    Mockito.verify(light, Mockito.never()).switchOff();
    Mockito.verify(light, Mockito.never()).switchOff();
  }

  @Test
  public void mainCorridorTest_CallTurnONCompromisedDevices_NoDeviceSwitchONMethodCalled() {
    mainCorridor.turnOnCompromisedDevice();
    Mockito.verify(light, Mockito.never()).switchOn();
    Mockito.verify(light, Mockito.never()).switchOn();
  }

  @Test
  public void mainCorridorTest_CallHasCompromisedDevices_ReturnsFalse() {
    mainCorridor.turnOffCompromisableDevice();
    Assert.assertEquals(false, mainCorridor.hasCompromisedDevices());
  }

  @Test
  public void mainCorridorTest_CallHasCompromisableDevices_ReturnsFalse() {

    Assert.assertEquals(false, mainCorridor.hasCompromisableDevices());
  }

  @Test
  public void mainCorridorTest_CallGetConsumption_Returns15() {
    mainCorridor.turnOffCompromisableDevice();
    Assert.assertEquals(15, mainCorridor.getConsumption());
  }

  @Test
  public void mainCorridorTest_CallGetnextCompromisableDeviceConsumption_ReturnsNull() {
    mainCorridor.turnOffCompromisableDevice();
    Assert.assertFalse(mainCorridor.getNextCompromisedDeviceConsumption().isPresent());
  }

  private void setupElectronicDevices() {
    light = Mockito.mock(ElectronicDevice.class);
    ac = Mockito.mock(ElectronicDevice.class);
    Mockito.when(light.getCanBeCompromised()).thenReturn(false);
    Mockito.when(ac.getCanBeCompromised()).thenReturn(false);
    Mockito.when(light.isOn()).thenReturn(true);
    Mockito.when(ac.isOn()).thenReturn(true);
    Mockito.when(ac.getConsumption()).thenReturn(10);
    Mockito.when(light.getConsumption()).thenReturn(5);
  }

  private void setupMainCorridor() {
    mainCorridor = new MainCorridor(1, "Main Corridor");
    mainCorridor.addDevice(light);
    mainCorridor.addDevice(ac);
    commands = new ICommand[] {new LightCommand(light), new ACCommand(ac)};
    mainCorridor.setNightModeCommand(new NightModeCommand(commands));
    mainCorridor.setMotionSensedCommand(new NullCommand());
  }
}
