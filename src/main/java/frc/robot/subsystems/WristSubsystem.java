package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.ctre.phoenix.sensors.WPI_PigeonIMU;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.WristConstants;

public class WristSubsystem extends SubsystemBase{
    private CANSparkMax wristMotor;

    private DigitalInput wristLimitOne;
    private DigitalInput wristLimitTwo;

    private WPI_PigeonIMU gyro;

    public WristSubsystem() {
        wristMotor = new CANSparkMax(WristConstants.wristMotorID, MotorType.kBrushless);

        wristLimitOne = new DigitalInput(WristConstants.wristLimitOneChannel);
        wristLimitTwo = new DigitalInput(WristConstants.wristLimitTwoChannel);

        gyro = new WPI_PigeonIMU(0);
    }
    
    public void setWrist(double speed) {
        wristMotor.set(speed);
    }

    public double getAngle() {
        return gyro.getAngle();
    }
    
}
