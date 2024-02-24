// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.WristConstants;
import frc.robot.subsystems.WristSubsystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.math.controller.PIDController;

public class WristMovementCommand extends PIDCommand {
  private final WristSubsystem m_wrist;
  
  public WristMovementCommand(DoubleSupplier targetAngle, WristSubsystem wrist) {
    super(
      new PIDController(WristConstants.wristP, WristConstants.wristI, WristConstants.wristD),
      ()->wrist.getAngle(),
      targetAngle,
      output -> wrist.setWrist(output),
      wrist);
    m_wrist = wrist;
   
    getController().setTolerance(WristConstants.wristPosTolerance, WristConstants.wristVelTolerance);
    addRequirements(wrist);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
