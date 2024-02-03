// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class ElevatorToSetPointCmd extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ElevatorSubsystem m_elevator;
  private double speed = 0;
  private boolean shouldGoToTop = true;


  public ElevatorToSetPointCmd(ElevatorSubsystem elevator, double speed, boolean shouldGoToTop) {
    m_elevator = elevator;
    this.speed = speed;
    this.shouldGoToTop = shouldGoToTop;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(elevator);
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
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_elevator.runElevator(0);
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
