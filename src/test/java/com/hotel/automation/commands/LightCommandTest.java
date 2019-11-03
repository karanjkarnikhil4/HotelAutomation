package com.hotel.automation.commands;

import com.hotel.automation.devices.ElectronicDevice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LightCommandTest {

  private ElectronicDevice electronicDevice;
  private ICommand lightCommand;

  @BeforeEach
  public void setUp() {
    electronicDevice = Mockito.mock(ElectronicDevice.class);
    lightCommand = new LightCommand(electronicDevice);
  }

  @Test
  public void lightCommandTest_CallExecuteMethod_LightSwitchOnMethodCalled() {
    lightCommand.execute();
    Mockito.verify(electronicDevice).switchOn();
  }

  @Test
  public void lightCommandTest_CallUndoMethod_LightSwitchOffMethodCalled() {
    lightCommand.undo();
    Mockito.verify(electronicDevice).switchOff();
  }
}
