package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class WristSubsystem extends SubsystemBase{
    public CANSparkMax wristMotor;

    public DigitalInput wristLimitOne;
    public DigitalInput wristLimitTwo;

    public WristSubsystem() {
        wristMotor = new CANSparkMax(ShooterConstants.wristMotorID, MotorType.kBrushless);

        wristLimitOne = new DigitalInput(ShooterConstants.wristLimitOneChannel);
        wristLimitTwo = new DigitalInput(ShooterConstants.wristLimitTwoChannel);
    }
    
    public void setWrist(double speed) {
        wristMotor.set(speed);
    }
    
}
