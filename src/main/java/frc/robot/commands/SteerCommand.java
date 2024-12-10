// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.SwerveConstants;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.VisionSubsystem;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class SteerCommand extends Command {
  private final VisionSubsystem m_limelight;
  private final Swerve m_Swerve;


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public SteerCommand(VisionSubsystem m_limelight, Swerve mSwerve) {
    this.m_limelight = m_limelight;
    this.m_Swerve = mSwerve;

    addRequirements(m_limelight, m_Swerve);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double[] vals = m_limelight.calculateLimelightSteering();
    Translation2d translation = new Translation2d(vals[0], 0.0);
    boolean fieldRelative = false; //TODO - Check is it is field relative
    boolean isOpenLoop = false;

    m_Swerve.drive(translation, vals[1], fieldRelative, isOpenLoop);

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
