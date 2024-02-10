// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.Constants.LEDConstants;

/** An example command that uses an example subsystem. */
public class ElevatorToSetPointCmd extends Command {
  private final ElevatorSubsystem m_elevator;
  private double speed = 0;
  private boolean shouldGoToTop = true;
  private final LEDSubsystem m_leds;


  public ElevatorToSetPointCmd(ElevatorSubsystem elevator, LEDSubsystem leds, double speed, boolean shouldGoToTop) {
    m_elevator = elevator;
    m_leds = leds;
    this.speed = speed;
    this.shouldGoToTop = shouldGoToTop;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(elevator, leds);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(shouldGoToTop){
      m_elevator.runElevator(this.speed);
    }
    else{
      m_elevator.runElevator(-this.speed);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (shouldGoToTop == true) {
      if ((int) (System.currentTimeMillis()/1000/LEDConstants.blinkSpeedDuringClimbUp) % 2 == 0) {
        m_leds.runLeds(LEDConstants.colorOneAllianceOne);
      }
      else {
        m_leds.runLeds(LEDConstants.colorTwoAllianceOne);
      }
    }
    else{
       m_leds.runLeds(LEDConstants.colorOrange);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_elevator.runElevator(0);
    if ((int) (System.currentTimeMillis()/1000/LEDConstants.blinkSpeedAtTop) % 2 == 0) {
      m_leds.runLeds(LEDConstants.colorBlue);
    }
    else {
      m_leds.runLeds(LEDConstants.colorGold);
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(m_elevator.elevatorPosition() == 2){
      return true;
    }
    else if(shouldGoToTop && m_elevator.elevatorPosition() == 1){
      return true;
    }
    else if(!shouldGoToTop && m_elevator.elevatorPosition() == -1){
      return true;
   }
    else{
      return false;
   }
  }
}
