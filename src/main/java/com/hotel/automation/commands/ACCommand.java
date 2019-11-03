package com.hotel.automation.commands;

import com.hotel.automation.devices.ElectronicDevice;

public class ACCommand implements ICommand {
  private ElectronicDevice ac;

  public ACCommand(ElectronicDevice ac) {
    this.ac = ac;
  }

  @Override
  public void execute() {
    ac.switchOn();
  }

  @Override
  public void undo() {
    ac.switchOff();
  }
}
