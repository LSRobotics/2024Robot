// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_Blue1Command;
  private Command m_Blue2Command;
  private Command m_Blue3Command;
  private Command m_Red1Command;
  private Command m_Red2Command;
  private Command m_Red3Command;

  private RobotContainer m_robotContainer;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    
    m_Blue1Command = m_robotContainer.getBlue1AutonomousCommand();
    m_Blue2Command = m_robotContainer.getBlue2AutonomousCommand();
    m_Blue3Command = m_robotContainer.getBlue3AutonomousCommand();
    m_Red1Command = m_robotContainer.getRed1AutonomousCommand();
    m_Red2Command = m_robotContainer.getRed2AutonomousCommand();
    m_Red3Command = m_robotContainer.getRed3AutonomousCommand();

    // schedule the autonomous command (example)
    if (m_Blue1Command != null) {
      m_Blue1Command.schedule();
    }

    if (m_Blue2Command != null) {
      m_Blue2Command.schedule();
    }
    
    if (m_Blue3Command != null) {
      m_Blue3Command.schedule();
    }

    if (m_Red1Command != null) {
      m_Red1Command.schedule();
    }

    if (m_Red2Command != null) {
      m_Red2Command.schedule();
    }

    if (m_Red3Command != null) {
      m_Red3Command.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
   if (m_Blue1Command != null) {
      m_Blue1Command.cancel();
    }

    if (m_Blue2Command != null) {
      m_Blue2Command.cancel();
    }
    
    if (m_Blue3Command != null) {
      m_Blue3Command.cancel();
    }

    if (m_Red1Command != null) {
      m_Red1Command.cancel();
    }

    if (m_Red2Command != null) {
      m_Red2Command.cancel();
    }

    if (m_Red3Command != null) {
      m_Red3Command.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
