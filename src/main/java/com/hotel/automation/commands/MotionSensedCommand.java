package com.hotel.automation.commands;

import java.util.Arrays;

public class MotionSensedCommand implements ICommand {

  private ICommand[] commands;

  public MotionSensedCommand(ICommand[] commands) {
    this.commands = commands;
  }

  @Override
  public void execute() {

    Arrays.stream(commands).forEach(ICommand::execute);
  }

  @Override
  public void undo() {
    Arrays.stream(commands).forEach(ICommand::undo);
  }
}
