// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.ShooterSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.math.controller.PIDController;

/** An example command that uses an example subsystem. */
public class WristMovementCommand extends PIDCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShooterSubsystem m_shooter;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public WristMovementCommand(DoubleSupplier currentAngle, DoubleSupplier targetAngle, ShooterSubsystem shooter) {
    super(
      new PIDController(ShooterConstants.wristP, ShooterConstants.wristI, ShooterConstants.wristD),
      currentAngle,
      targetAngle,
      output -> shooter.setWrist(output),
      shooter);
    m_shooter = shooter;

    getController().setTolerance(ShooterConstants.wristPosTolerance, ShooterConstants.wristVelTolerance);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
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
