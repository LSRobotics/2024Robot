// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants.ElevatorConstants;
import frc.robot.Constants.IntakeConstants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX;

public class IntakeSubsystem extends SubsystemBase {
   public TalonFX intakeMotorOne;
   //private DigitalInput elevatorBottomLimit;
   //private DigitalInput elevatorTopLimit;

  public IntakeSubsystem() {
    intakeMotorOne = new TalonFX(IntakeConstants.intakeMotorOneID);
    //elevatorBottomLimit = new DigitalInput(ElevatorConstants.elevatorBottomLimitChannel);
    //elevatorTopLimit = new DigitalInput(ElevatorConstants.elevatorTopLimitChannel);
  }
  
  public void runIntake(double speed) {
    intakeMotorOne.set(-speed);
    //System.out.println("This is the top limit "+elevatorBottomLimit.get());
    //System.out.println("This is bottom limit "+elevatorTopLimit.get());
  }
}
