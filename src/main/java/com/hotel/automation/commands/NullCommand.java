package com.hotel.automation.commands;

public class NullCommand implements ICommand {
  @Override
  public void execute() {
    // do nothing
  }

  @Override
  public void undo() {
    // do nothing
  }
}
