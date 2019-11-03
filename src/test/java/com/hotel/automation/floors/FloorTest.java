package com.hotel.automation.floors;

import com.hotel.automation.commands.*;
import com.hotel.automation.corridor.Corridor;
import com.hotel.automation.corridor.MainCorridor;
import com.hotel.automation.corridor.SubCorridor;
import com.hotel.automation.devices.ElectronicDevice;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class FloorTest {

  private ICommand[] mainCorridorCommands;
  private ICommand[] subCorridorCommands;
  private ElectronicDevice lightMain;
  private ElectronicDevice acMain;
  private ElectronicDevice lightSub;
  private ElectronicDevice acSub;
  private Corridor mainCorridor;
  private Corridor subCorridor;
  private IFloor floor;

  @BeforeEach
  public void setUp() {
    setupMainCorridorElectronicDevices();
    setupSubCorridorElectronicDevices();
    setupMainCorridor();
    setupSubCorridor();
    setupFloor();
  }

  @Test
  public void floorTest_CallMotionSensedMethod_OnlySubCorridorLightSwitchedOn() {
    floor.motionSensed(1);
    floor.motionSensed(2);
    Mockito.verify(lightMain, Mockito.never()).switchOn();
    Mockito.verify(acMain, Mockito.never()).switchOn();
    Mockito.verify(lightSub).switchOn();
    Mockito.verify(acSub, Mockito.never()).switchOn();
  }

  @Test
  public void floorTest_CallNoMotionSensedMethod_OnlySubCorridorLightSwitchedOff() {
    floor.noMotionSensed(1);
    floor.noMotionSensed(2);
    Mockito.verify(lightMain, Mockito.never()).switchOff();
    Mockito.verify(acMain, Mockito.never()).switchOff();
    Mockito.verify(lightSub).switchOff();
    Mockito.verify(acSub, Mockito.never()).switchOff();
  }

  @Test
  public void floorTest_CallNightModeONMethod_OnlyMainCorridorLightSwitchedOn() {
    floor.nightModeON();
    Mockito.verify(lightMain).switchOn();
    Mockito.verify(acMain, Mockito.never()).switchOn();
    Mockito.verify(lightSub, Mockito.never()).switchOn();
    Mockito.verify(acSub, Mockito.never()).switchOn();
  }

  @Test
  public void floorTest_CallNightModeOFFMethod_OnlyMainCorridorLightDeviceSwitchedOFF() {
    floor.nightModeOFF();
    Mockito.verify(lightMain).switchOff();
    Mockito.verify(acMain, Mockito.never()).switchOff();
    Mockito.verify(lightSub, Mockito.never()).switchOff();
    Mockito.verify(acSub, Mockito.never()).switchOff();
  }

  @Test
  public void floorTest_CallPrintStatusMethod_DevicesPrintStatusCalled() {
    floor.printStatus();
    Mockito.verify(lightMain).printStatus();
    Mockito.verify(acMain).printStatus();
    Mockito.verify(lightSub).printStatus();
    Mockito.verify(acSub).printStatus();
  }

  @Test
  public void floorTest_OverloadedFloor_OnlySubCorridorACSwitchedOFF() {

    Mockito.when(lightSub.isOn()).thenReturn(true);
    floor.motionSensed(1);

    Mockito.verify(lightMain, Mockito.never()).switchOff();
    Mockito.verify(acMain, Mockito.never()).switchOff();
    Mockito.verify(lightSub, Mockito.never()).switchOff();
    Mockito.verify(acSub).switchOff();
  }

  @Test
  public void floorTest_OverloadFloor_AssertConsumptions() {

    floor.motionSensed(1);
    Mockito.when(acSub.isOn()).thenReturn(false);
    Mockito.when(lightSub.isOn()).thenReturn(true);

    Assert.assertEquals(15, floor.getCorridor(1).getConsumption());
    Assert.assertEquals(5, floor.getCorridor(2).getConsumption());
  }

  @Test
  public void floorTest_UnOverload_OnlySubCorridorACTurnedOn() {

    Mockito.when(lightSub.isOn()).thenReturn(true);
    floor.motionSensed(1);
    Mockito.when(acSub.isOn()).thenReturn(false);
    Mockito.when(lightSub.isOn()).thenReturn(true);
    Mockito.when(lightMain.isOn()).thenReturn(false);
    Mockito.when(acMain.isOn()).thenReturn(true);
    floor.nightModeOFF();

    Mockito.verify(lightMain, Mockito.never()).switchOn();
    Mockito.verify(acMain, Mockito.never()).switchOn();
    Mockito.verify(lightSub, Mockito.never()).switchOn();
    Mockito.verify(acSub).switchOn();
  }

  private void setupMainCorridorElectronicDevices() {
    lightMain = Mockito.mock(ElectronicDevice.class);
    acMain = Mockito.mock(ElectronicDevice.class);
    Mockito.when(lightMain.getCanBeCompromised()).thenReturn(false);
    Mockito.when(acMain.getCanBeCompromised()).thenReturn(false);
    Mockito.when(lightMain.isOn()).thenReturn(true);
    Mockito.when(acMain.isOn()).thenReturn(true);

    Mockito.when(acMain.getConsumption()).thenReturn(10);
    Mockito.when(lightMain.getConsumption()).thenReturn(5);
  }

  private void setupSubCorridorElectronicDevices() {
    lightSub = Mockito.mock(ElectronicDevice.class);
    acSub = Mockito.mock(ElectronicDevice.class);
    Mockito.when(lightSub.getCanBeCompromised()).thenReturn(false);
    Mockito.when(acSub.getCanBeCompromised()).thenReturn(true);
    Mockito.when(lightSub.isOn()).thenReturn(false);
    Mockito.when(acSub.isOn()).thenReturn(true);

    Mockito.when(lightSub.getConsumption()).thenReturn(5);
    Mockito.when(acSub.getConsumption()).thenReturn(10);
  }

  private void setupMainCorridor() {
    mainCorridor = new MainCorridor(1, "Main Corridor");
    mainCorridor.addDevice(lightMain);
    mainCorridor.addDevice(acMain);
    mainCorridorCommands = new ICommand[] {new LightCommand(lightMain)};
    mainCorridor.setNightModeCommand(new NightModeCommand(mainCorridorCommands));
    mainCorridor.setMotionSensedCommand(new NullCommand());
  }

  private void setupSubCorridor() {
    subCorridor = new SubCorridor(2, "Sub Corridor");
    subCorridor.addDevice(lightSub);
    subCorridor.addDevice(acSub);
    subCorridorCommands = new ICommand[] {new LightCommand(lightSub)};
    subCorridor.setNightModeCommand(new NullCommand());
    subCorridor.setMotionSensedCommand(new MotionSensedCommand(subCorridorCommands));
  }

  private void setupFloor() {
    floor = new Floor(1);
    floor.addCorridor(mainCorridor);
    floor.addCorridor(subCorridor);
  }
}
