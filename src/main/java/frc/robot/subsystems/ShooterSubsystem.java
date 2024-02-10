package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase{
    public CANSparkMax shooterMotorOne;
    public CANSparkMax shooterMotorTwo;

    public ShooterSubsystem() {
        shooterMotorOne = new CANSparkMax(ShooterConstants.shooterMotorOneID, MotorType.kBrushless);
        shooterMotorTwo = new CANSparkMax(ShooterConstants.shooterMotorTwoID, MotorType.kBrushless);
    }

    public void runShooter(double speed) {
        shooterMotorOne.set(speed);
        shooterMotorTwo.set(speed);
    }
    
}
