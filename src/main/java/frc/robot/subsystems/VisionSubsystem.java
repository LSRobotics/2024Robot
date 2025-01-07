package frc.robot.subsystems;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.Vector;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.proto.System;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;

public class VisionSubsystem extends SubsystemBase {
    public VisionSubsystem() {
        return;
    }
    
    
    public void periodic() {
        return;
      }

    public double getDistance(int pipelineID){
        LimelightHelpers.setPipelineIndex("",pipelineID);
        double targetAngleOffset = LimelightHelpers.getTY("");
    
        double limelightMountAngle = 25.0; 
    
        double lensHeight = 20.0; //in 
    
        double goalHeight = 60.0; //in 
    
        double angleToGoalDegrees = targetAngleOffset + limelightMountAngle;
        double angleToGoalRadians = angleToGoalDegrees * (Math.PI / 180.0);
    
        //calculate distance
        double distance = (goalHeight - lensHeight) / Math.tan(angleToGoalRadians);
        return distance;
    }

    public double getTX(String limelightName){
        return LimelightHelpers.getTX(limelightName);
    }

    public double getTY(String limelightName){
        return LimelightHelpers.getTY(limelightName);
    }

    

}


