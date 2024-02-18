// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.IndexerSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.Constants.IndexerConstants;
import frc.robot.Constants.LEDConstants;

/** An example command that uses an example subsystem. */
public class PassToShooterCmd extends Command {
  private final IndexerSubsystem m_indexer;
  private final LEDSubsystem m_leds;
  double speed = IndexerConstants.indexSpeed;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public PassToShooterCmd(IndexerSubsystem indexer, LEDSubsystem leds, double speed) {
    m_indexer = indexer;
    m_leds = leds;
    this.speed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(indexer, leds);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_leds.runLeds(LEDConstants.colorBlueViolet);
    m_indexer.runIndexer(this.speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_leds.runLeds(LEDConstants.colorWhite);
    m_indexer.indexMotor.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
