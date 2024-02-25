// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.IndexerSubsystem;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.IndexerConstants;

/** An example command that uses an example subsystem. */
public class PassToShooterCmd extends Command {
  private final IndexerSubsystem m_indexer;
  private BooleanSupplier notePresent;
  double speed;

  public PassToShooterCmd(IndexerSubsystem indexer, BooleanSupplier notePresent, double speed) {
    m_indexer = indexer;
    this.notePresent = notePresent;
    this.speed = speed;
    addRequirements(indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_indexer.runIndexer(this.speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_indexer.indexMotor.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !notePresent.getAsBoolean();
  }
}
