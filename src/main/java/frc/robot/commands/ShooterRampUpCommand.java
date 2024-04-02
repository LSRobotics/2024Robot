// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ShooterSubsystem;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.LEDConstants;
import frc.robot.subsystems.LEDSubsystem;

/** An example command that uses an example subsystem. */
public class ShooterRampUpCommand extends Command {
  private final ShooterSubsystem m_shooter;
  private final BooleanSupplier notePresent;
  private double speedTop = 0;
  private double speedBottom = 0;
  private final LEDSubsystem m_leds;

  public ShooterRampUpCommand(ShooterSubsystem shooter, LEDSubsystem leds, double speedTop, double speedBottom, BooleanSupplier notePresent) {
    m_shooter = shooter;
    m_leds = leds;
    this.speedTop = speedTop;
    this.speedBottom = speedBottom;
    this.notePresent = notePresent;
    addRequirements(shooter, leds);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_shooter.runShooter(this.speedTop, speedBottom);

    //m_shooter.rumble();
    //m_leds.runLeds(LEDConstants.colorSkyBlue);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //System.out.println("Shooter ramp speed is equal to " + this.speedTop + ".");
    m_shooter.rumble(true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.runShooter(0 , 0);

    m_shooter.rumble(false);

    m_leds.runLeds(LEDConstants.colorLimeGreen);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(notePresent == null){
      return false;
    }
    return !notePresent.getAsBoolean();
  }
}

