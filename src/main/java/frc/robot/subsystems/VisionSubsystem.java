package frc.robot.subsystems;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
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
}



