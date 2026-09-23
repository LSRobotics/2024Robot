// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.IndexerConstants;

/** An example command that uses an example subsystem. */
public class Amp extends Command {
  private final IndexerSubsystem m_indexer;
  private final ShooterSubsystem m_shooter;
  private BooleanSupplier notePresent;
  double speedIndex;
  double speedShooterOne;
  double speedShooterTwo;

  public Amp(IndexerSubsystem indexer, ShooterSubsystem shooter, double speed, double shooterOne, double shooterTwo, BooleanSupplier notePresent) {
    m_indexer = indexer;
    m_shooter = shooter;
    //this.notePresent = notePresent;
    this.speedIndex = speed;
    this.speedShooterOne = shooterOne;
    this.speedShooterTwo = shooterTwo;
    this.notePresent = notePresent;
    addRequirements(indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_indexer.runIndexer(this.speedIndex);
    m_shooter.runShooter(this.speedShooterOne, this.speedShooterTwo);
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_indexer.indexMotor.set(0);
    m_shooter.runShooter(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (notePresent == null) return false;
    return !notePresent.getAsBoolean();
  }
}
