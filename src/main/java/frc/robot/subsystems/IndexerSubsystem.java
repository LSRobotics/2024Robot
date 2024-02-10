// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants.IndexerConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;


public class IndexerSubsystem extends SubsystemBase {
  public CANSparkMax indexMotor;
  public DigitalInput indexBeamBreak;

  public IndexerSubsystem() {
    indexMotor = new CANSparkMax(IndexerConstants.indexMotorID, MotorType.kBrushless);

    indexBeamBreak = new DigitalInput(IndexerConstants.indexBeamBreakChannel);
  }

  public void runIndexer(double speed){
    indexMotor.set(speed);
  }

  public boolean notePresent() {
    return indexBeamBreak.get();  
  }
}
