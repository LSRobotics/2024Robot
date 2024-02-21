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

public class ElevatorSubsystem extends SubsystemBase {
  /** Creates a new ElevatorSubsystem. */
  public CANSparkMax leftElevatorMotor;
  public CANSparkMax rightElevatorMotor;
  public DigitalInput elevatorBottomLimit;
  public DigitalInput elevatorTopLimit;

  public ElevatorSubsystem() {
    //leftElevatorMotor = new CANSparkMax(ElevatorConstants.leftElevatorMotorID, MotorType.kBrushless);
    //rightElevatorMotor = new CANSparkMax(ElevatorConstants.rightElevatorMotorID, MotorType.kBrushless);
    
    //elevatorBottomLimit = new DigitalInput(ElevatorConstants.elevatorBottomLimitChannel);
    ///elevatorTopLimit = new DigitalInput(ElevatorConstants.elevatorTopLimitChannel);
  }
  
 
  public void runElevator(double speed) {
    //leftElevatorMotor.set(speed);
    //rightElevatorMotor.set(speed);
  }

  public int elevatorPosition() {
    /* 
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
    */
    return 2;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
