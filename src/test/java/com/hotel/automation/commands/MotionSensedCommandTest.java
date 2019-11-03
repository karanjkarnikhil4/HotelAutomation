package com.hotel.automation.commands;

import com.hotel.automation.devices.ElectronicDevice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MotionSensedCommandTest {

  ICommand[] commands;
  private ElectronicDevice electronicDevice1;
  private ElectronicDevice electronicDevice2;

  @BeforeEach
  public void onSetUp() {
    electronicDevice1 = Mockito.mock(ElectronicDevice.class);
    electronicDevice2 = Mockito.mock(ElectronicDevice.class);
    commands =
        new ICommand[] {new LightCommand(electronicDevice1), new ACCommand(electronicDevice2)};
  }

  @Test
  public void motionCommandTest_CallExecuteMethod_DeviceSwitchONMethodsCalled() {
    ICommand acCommand = new MotionSensedCommand(commands);
    acCommand.execute();

    Mockito.verify(electronicDevice1).switchOn();
    Mockito.verify(electronicDevice1).switchOn();
  }

  @Test
  public void motionCommandTest_CallUndoMethod_DeviceSwitchOFFMethodsCalled() {

    ICommand acCommand = new MotionSensedCommand(commands);
    acCommand.undo();

    Mockito.verify(electronicDevice1).switchOff();
    Mockito.verify(electronicDevice1).switchOff();
  }
}
