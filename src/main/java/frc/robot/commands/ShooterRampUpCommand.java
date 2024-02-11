// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.LEDConstants;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LEDSubsystem;

/** An example command that uses an example subsystem. */
public class ShooterRampUpCommand extends Command {
  private final ShooterSubsystem m_shooter;
  private double speed = 0;
  private final LEDSubsystem m_leds;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShooterRampUpCommand(ShooterSubsystem shooter, LEDSubsystem leds, double speed) {
    m_shooter = shooter;
    m_leds = leds;
    this.speed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter, leds);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_shooter.runShooter(this.speed);
    m_leds.runLeds(LEDConstants.colorSkyBlue);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    /*if () {
      if ((int) (System.currentTimeMillis()/1000/LEDConstants.blinkSpeedShooterRampedUp) % 2 == 0) {
        m_leds.runLeds(LEDConstants.colorLimeGreen);
      }
      else {
        m_leds.runLeds(LEDConstants.colorDarkGreen)
      }
    }
    else {
      m_leds.runLeds(LEDConstants.colorSkyBlue);
    }*/
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.runShooter(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false; //TODO Determine when shooter finished
  }
}
