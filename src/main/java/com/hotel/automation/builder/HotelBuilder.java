package com.hotel.automation.builder;

import com.hotel.automation.commands.*;
import com.hotel.automation.corridor.Corridor;
import com.hotel.automation.corridor.MainCorridor;
import com.hotel.automation.corridor.SubCorridor;
import com.hotel.automation.devices.AC;
import com.hotel.automation.devices.ElectronicDevice;
import com.hotel.automation.devices.Light;
import com.hotel.automation.floors.Floor;
import com.hotel.automation.floors.IFloor;
import com.hotel.automation.hotel.Hotel;
import com.hotel.automation.hotel.IHotel;

public class HotelBuilder {

  private IHotelSpecification hotelSpecs;

  public HotelBuilder(IHotelSpecification hotelSpecs) {
    this.hotelSpecs = hotelSpecs;
  }

  public IHotel getHotel() {

    IHotel hotel = new Hotel(this.hotelSpecs.getHotelName());
    for (int floornumber = 0; floornumber < this.hotelSpecs.getNoOfFloors(); floornumber++) {
      IFloor floor = new Floor(floornumber);
      hotel.addFloor(floor);
      buildMainCorridors(this.hotelSpecs, floor);
      buildSubCorridors(this.hotelSpecs, floor);
    }

    return hotel;
  }

  private void buildMainCorridors(IHotelSpecification hotelSpecs, IFloor floor) {
    int numberOfCorridors = hotelSpecs.getNoOfMainCorridorsPerFloor();
    int numberOfLights = hotelSpecs.getNoOfLightsPerCorridor();
    int initialCorridorCount = floor.getCorridorCount();

    for (int number = initialCorridorCount;
        number < numberOfCorridors + initialCorridorCount;
        number++) {
      Corridor corridor = new MainCorridor(number, "Main Corridor");
      floor.addCorridor(corridor);

      ICommand[] commands = new ICommand[numberOfLights];

      setUpCorridorLights(hotelSpecs, corridor, commands);
      setUpCorridorACS(hotelSpecs, corridor, false);
      setMainCorridorCommands(corridor, commands);
    }
  }

  private void setUpCorridorLights(
      IHotelSpecification hotelSpecs, Corridor corridor, ICommand[] commands) {

    for (int number = 0; number < hotelSpecs.getNoOfLightsPerCorridor(); number++) {

      ElectronicDevice electronicDevice = new Light(Integer.toString(number));
      corridor.addDevice(electronicDevice);
      commands[number] = new LightCommand(electronicDevice);
    }
  }

  private void setMainCorridorCommands(Corridor corridor, ICommand[] commands) {
    corridor.setNightModeCommand(new NightModeCommand(commands));
    corridor.setMotionSensedCommand(new NullCommand());
  }

  private void buildSubCorridors(IHotelSpecification hotelSpecs, IFloor floor) {
    int numberOfCorridors = hotelSpecs.getNoOfSubCorridorsPerFloor();
    int numberOfLights = hotelSpecs.getNoOfLightsPerCorridor();
    int initialCorridorCount = floor.getCorridorCount();
    for (int number = initialCorridorCount;
        number < numberOfCorridors + initialCorridorCount;
        number++) {

      Corridor corridor = new SubCorridor(number, "Sub  Corridor");
      floor.addCorridor(corridor);
      ICommand[] commands = new ICommand[numberOfLights];
      setUpCorridorLights(hotelSpecs, corridor, commands);
      setUpCorridorACS(hotelSpecs, corridor, true);
      setUpSubCorridorCommands(corridor, commands);
    }
  }

  private void setUpSubCorridorCommands(Corridor corridor, ICommand[] commands) {
    corridor.setNightModeCommand(new NullCommand());
    corridor.setMotionSensedCommand(new MotionSensedCommand(commands));
  }

  private void setUpCorridorACS(
      IHotelSpecification hotelSpecs, Corridor corridor, boolean canBeCompromised) {
    for (int acNumber = 0; acNumber < hotelSpecs.getNoOfACPerCorridor(); acNumber++) {
      ElectronicDevice electronicDevice = new AC(Integer.toString(acNumber));
      electronicDevice.setCanBeConpromised(canBeCompromised);
      corridor.addDevice(electronicDevice);
    }
  }
}
