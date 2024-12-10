// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.LimelightHelpers;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.VisionSubsystem;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class mantainDistanceCommand extends Command {
  private final VisionSubsystem m_limelight;
  private final Swerve m_Swerve;

  double KpDistance = -0.1; 



  public mantainDistanceCommand(VisionSubsystem limelight, Swerve swerve) {
    m_limelight = limelight;
    m_Swerve = swerve;
    addRequirements(m_limelight,m_Swerve);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
public void execute() {
    double distanceError = LimelightHelpers.getTY("limelight");
    
    double driving_adjust = KpDistance * distanceError; 

    double steeringError = LimelightHelpers.getTX("limelight");

    double steering_adjust = KpDistance * steeringError;

    Translation2d translation = new Translation2d(driving_adjust, 0.0);

    boolean fieldRelative = false;

    boolean isOpenLoop = false;

    m_Swerve.drive(translation, steering_adjust, fieldRelative, isOpenLoop);
}


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}