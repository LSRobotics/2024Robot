package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.subsystems.Swerve;

public class VisionSubsystem extends SubsystemBase {
    private double tx, ty, ta, tv,tid;
    private final NetworkTable m_limelightTable;


    double limelightMountAngleDegrees = 0; 
    //double targetOffsetAngle_Vertical = ty.getDouble(0.0);

    // distance from the center of the Limelight lens to the floor
    double limelightLensHeightInches = 18.0; 

    // distance from the target to the floor
    double goalHeightInches = 56.49; 

    double angleToGoalDegrees;
    double angleToGoalRadians;

    //calculate distance
    double distanceFromLimelightToGoalInches;
    
    
    public VisionSubsystem() {
        super();
        m_limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    }
    
    
    public void periodic() {
        // This method will be called once per scheduler run
        ty = m_limelightTable.getEntry("ty").getDouble(0);
        tx = m_limelightTable.getEntry("tx").getDouble(0);
        ta = m_limelightTable.getEntry("ta").getDouble(0);
        tv = m_limelightTable.getEntry("tv").getDouble(0);
        tid = m_limelightTable.getEntry("tid").getDouble(0);
      }
   
    public double getTx(){
        return tx;
    }
    public double getTy(){
        return ty;
    }
    public double getTa(){
        return ta;
    }
    public boolean targetAvailable(){
        if(tv == 1){
            return true;
        }
        else{
            return false;
        }
    }


    public double getDistance(){

        angleToGoalDegrees = limelightMountAngleDegrees + getTy();
        angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);
        distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);
        //double distanceFromLimelightToGoalInches = (goalHeightInches - limelightLensHeightInches) / Math.tan(angleToGoalRadians);
        return distanceFromLimelightToGoalInches;

    }
        
    
}