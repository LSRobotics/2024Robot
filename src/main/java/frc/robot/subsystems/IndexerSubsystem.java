// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants.IndexerConstants;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IndexerSubsystem extends SubsystemBase {
  public SparkMax indexMotor;

  public IndexerSubsystem() {
    indexMotor = new SparkMax(IndexerConstants.indexMotorID, MotorType.kBrushless);
  }

  public void runIndexer(double speed){
    indexMotor.set(-speed);
  }

}
