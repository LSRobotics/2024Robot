// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LEDSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Constants.LEDConstants;

/** An example command that uses an example subsystem. */
public class DetectNoteCommand extends Command {
  private final LEDSubsystem m_led;
  private final BooleanSupplier notePresent;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DetectNoteCommand(LEDSubsystem subsystem, BooleanSupplier notePresent) {
    m_led = subsystem;
    this.notePresent = notePresent;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (notePresent.getAsBoolean()) m_led.runLeds(LEDConstants.colorLimeGreen);
    else m_led.runLeds(LEDConstants.colorRed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_led.runLeds(LEDConstants.colorYellow);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
