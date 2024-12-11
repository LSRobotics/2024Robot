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

      public double[] calculateLimelightSteering() {
        int x = (int)(LimelightHelpers.getCurrentPipelineIndex("limelight"));
        LimelightHelpers.setPipelineIndex("limelight", 9);

        //Fix consts
        double kP_Aim = 0.035;  //rot
        double kP_Range = 0.1;  // speed
  
        double tx = LimelightHelpers.getTX("limelight");
        double ty = LimelightHelpers.getTY("limelight");
  
        double rot = -tx * kP_Aim * Constants.SwerveConstants.maxAngularVelocity;  
        double xSpeed = -ty * kP_Range * Constants.SwerveConstants.maxSpeed;   // forward back speed

        LimelightHelpers.setPipelineIndex("limelight", x);
  
        return new double[] {xSpeed, rot};
    }

    public double getDistance(int[] targetIDs){
        int x = (int)(LimelightHelpers.getCurrentPipelineIndex(""));
        LimelightHelpers.setPipelineIndex("", 9);
        double targetOffsetAngle_Vertical = LimelightHelpers.getTY("");

        if (true){return LimelightHelpers.getTY("");}
        
        double limelightMountAngleDegrees = Constants.LimelightConstants.limelightAngle;

        double limelightLensHeightInches = Constants.LimelightConstants.limelightHeight; 

        double goalHeightInches = 10.0;

        double angleToGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
        double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);

        double goalDist = (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);

        LimelightHelpers.setPipelineIndex("", x);

        return goalDist;
    }

}


