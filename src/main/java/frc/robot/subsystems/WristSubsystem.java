package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.Pigeon2;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.WristConstants;

public class WristSubsystem extends SubsystemBase{
    private SparkMax wristMotor;

    private DigitalInput wristLimitOne;
    private DigitalInput wristLimitTwo;

    private Pigeon2 gyro;

    public WristSubsystem() {
        //wristMotor = new CANSparkMax(WristConstants.wristMotorID, MotorType.kBrushless);
        /* 
        wristLimitOne = new DigitalInput(WristConstants.wristLimitOneChannel);
        wristLimitTwo = new DigitalInput(WristConstants.wristLimitTwoChannel);
        */
        gyro = new Pigeon2(0);
        
    }
    
    public void setWrist(double speed) {
        //wristMotor.set(speed);
    }

    public double getAngle() {
        return 0;
    }
    
}
