package com.hotel.automation.corridor;

import com.hotel.automation.commands.ICommand;
import com.hotel.automation.commands.LightCommand;
import com.hotel.automation.commands.MotionSensedCommand;
import com.hotel.automation.commands.NullCommand;
import com.hotel.automation.devices.ElectronicDevice;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SubCorridorTest {

  private ICommand[] commands;
  private ElectronicDevice light;
  private ElectronicDevice ac;
  private Corridor subCorridor;

  @BeforeEach
  public void setUp() {
    setupElectronicDevices();
    setupSubCorridor();
  }

  @Test
  public void subCorridorTest_CallMotionSensedMethod_DeviceSwitchONMethodsCalled() {
    subCorridor.motionSensed();
    Mockito.verify(light).switchOn();
    Mockito.verify(ac, Mockito.never()).switchOn();
  }

  @Test
  public void subCorridorTest_CallNoMotionSensedMethod_DeviceSwitchOFFMethodsCalled() {
    Mockito.when(light.isOn()).thenReturn(true);
    subCorridor.noMotionSensed();
    Mockito.verify(light).switchOff();
    Mockito.verify(ac, Mockito.never()).switchOff();
  }

  @Test
  public void subCorridorTest_CallNightModeONMethod_NoDeviceSwitchONMethodsCalled() {
    subCorridor.nightModeON();
    Mockito.verify(light, Mockito.never()).switchOn();
    Mockito.verify(ac, Mockito.never()).switchOn();
  }

  @Test
  public void subCorridorTest_CallNightModeOFFMethod_NoLightDeviceSwitchOFFMethodsCalled() {
    subCorridor.nightModeOFF();
    Mockito.verify(light, Mockito.never()).switchOff();
    Mockito.verify(ac, Mockito.never()).switchOff();
  }

  @Test
  public void subCorridorTest_CallprintStatusMethod_DevicesPrintStatusCalled() {
    subCorridor.printStatus();
    Mockito.verify(light).printStatus();
    Mockito.verify(ac).printStatus();
  }

  @Test
  public void subCorridorTest_CallTurnOffCompromisableDevices_DeviceSwitchOffMethodCalled() {
    subCorridor.turnOffCompromisableDevice();
    Mockito.verify(light, Mockito.never()).switchOff();
    Mockito.verify(ac).switchOff();
  }

  @Test
  public void subCorridorTest_CallTurnONCompromisedDevices_DeviceSwitchONMethodCalled() {
    subCorridor.turnOffCompromisableDevice();
    subCorridor.turnOnCompromisedDevice();
    Mockito.verify(light, Mockito.never()).switchOn();
    Mockito.verify(ac).switchOn();
  }

  @Test
  public void subCorridorTest_CallHasCompromisedDevices_ReturnsTrue() {
    Mockito.when(light.isOn()).thenReturn(true);
    subCorridor.turnOffCompromisableDevice();
    Assert.assertEquals(true, subCorridor.hasCompromisedDevices());
  }

  @Test
  public void subCorridorTest_CallHasCompromisableDevices_ReturnsTrue() {

    Assert.assertEquals(true, subCorridor.hasCompromisableDevices());
  }

  @Test
  public void subCorridorTest_CallGetConsumption_Returns15() {
    subCorridor.turnOffCompromisableDevice();
    Mockito.when(ac.isOn()).thenReturn(false);
    Assert.assertEquals(0, subCorridor.getConsumption());
  }

  @Test
  public void SubCorridorTest_CallGetnextCompromisableDeviceConsumption_Returns() {
    subCorridor.turnOffCompromisableDevice();

    Assert.assertEquals(
        new Integer(10).intValue(),
        subCorridor.getNextCompromisedDeviceConsumption().get().intValue());
  }

  private void setupSubCorridor() {
    subCorridor = new SubCorridor(1, "Sub Corridor");
    subCorridor.addDevice(light);
    subCorridor.addDevice(ac);
    commands = new ICommand[] {new LightCommand(light)};
    subCorridor.setNightModeCommand(new NullCommand());
    subCorridor.setMotionSensedCommand(new MotionSensedCommand(commands));
  }

  private void setupElectronicDevices() {
    light = Mockito.mock(ElectronicDevice.class);
    ac = Mockito.mock(ElectronicDevice.class);
    Mockito.when(light.getCanBeCompromised()).thenReturn(false);
    Mockito.when(ac.getCanBeCompromised()).thenReturn(true);
    Mockito.when(light.isOn()).thenReturn(false);
    Mockito.when(ac.isOn()).thenReturn(true);
    Mockito.when(ac.getConsumption()).thenReturn(10);
    Mockito.when(light.getConsumption()).thenReturn(5);
  }
}
