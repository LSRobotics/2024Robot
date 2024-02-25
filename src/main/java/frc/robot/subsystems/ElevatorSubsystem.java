// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.playingwithfusion.*;


import com.ctre.phoenix.motorcontrol.can.*;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class ElevatorSubsystem extends SubsystemBase {
  /** Creates a new ElevatorSubsystem. */
  public CANSparkMax elevatorMotor;
  public DigitalInput elevatorBottomLimit;
  public DigitalInput elevatorTopLimit;


  public ElevatorSubsystem() {
    elevatorMotor = new CANSparkMax(ElevatorConstants.elevatorMotorID, MotorType.kBrushless);
    
    elevatorBottomLimit = new DigitalInput(ElevatorConstants.elevatorBottomLimitChannel);
    elevatorTopLimit = new DigitalInput(ElevatorConstants.elevatorTopLimitChannel);
  }
  
 
  public void runElevator(double speed) {
    elevatorMotor.set(speed);
  }

  public int elevatorPosition() {
     
    if (elevatorTopLimit.get() && elevatorBottomLimit.get()){
      return 2;
    }
    else if (elevatorBottomLimit.get()){
      return -1;
    }
    else if (elevatorTopLimit.get()){
      return 1;
    }
    else{
      return 0;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
