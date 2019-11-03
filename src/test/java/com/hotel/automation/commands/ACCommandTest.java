package com.hotel.automation.commands;

import com.hotel.automation.devices.ElectronicDevice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ACCommandTest {

  private ElectronicDevice electronicDevice;
  private ICommand acCommand;

  @BeforeEach
  public void setUp() {
    electronicDevice = Mockito.mock(ElectronicDevice.class);
    acCommand = new ACCommand(electronicDevice);
  }

  @Test
  public void aCCommandTest_CallExecuteMethod_ACSwitchOnMethodCalled() {
    acCommand.execute();
    Mockito.verify(electronicDevice).switchOn();
  }

  @Test
  public void aCCommandTest_CallUndoMethod_ACSwitchOffMethodCalled() {
    acCommand.undo();
    Mockito.verify(electronicDevice).switchOff();
  }
}
