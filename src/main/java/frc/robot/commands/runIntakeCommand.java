// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.LEDSubsystem;

import frc.robot.Constants.LEDConstants;



import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class RunIntakeCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final IntakeSubsystem m_intake;
  private final IndexerSubsystem m_indexer;
  private double speed;
  private final LEDSubsystem m_leds;
 
  public RunIntakeCommand(IntakeSubsystem intake, IndexerSubsystem indexer, LEDSubsystem leds, double speed) {
    m_intake = intake;
    m_indexer = indexer;
    m_leds = leds;
    this.speed = speed;
    addRequirements(intake, indexer, leds);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.runIntake(speed);
    m_indexer.runIndexer(speed);
    m_leds.runLeds(LEDConstants.colorRed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
     m_intake.runIntake(0);
     m_indexer.runIndexer(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    m_leds.runLeds(LEDConstants.colorYellow);
    return false; //m_indexer.notePresent();
  }
}
