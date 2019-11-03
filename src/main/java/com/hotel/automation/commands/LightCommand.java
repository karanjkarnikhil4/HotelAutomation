package com.hotel.automation.commands;

import com.hotel.automation.devices.ElectronicDevice;

public class LightCommand implements ICommand {

  private ElectronicDevice light;

  public LightCommand(ElectronicDevice light) {
    this.light = light;
  }

  @Override
  public void execute() {
    light.switchOn();
  }

  @Override
  public void undo() {
    light.switchOff();
  }
}
