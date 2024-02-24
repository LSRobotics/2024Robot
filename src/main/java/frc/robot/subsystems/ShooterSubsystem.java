package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

import com.ctre.phoenix6.hardware.TalonFX;

public class ShooterSubsystem extends SubsystemBase{
    private TalonFX shooterMotorOne;
    private TalonFX shooterMotorTwo;

    public ShooterSubsystem() {
        shooterMotorOne = new TalonFX(ShooterConstants.shooterMotorOneID);
        shooterMotorTwo = new TalonFX(ShooterConstants.shooterMotorTwoID);
    }
    public void runShooter(double speed) {
        shooterMotorOne.set(speed);
        shooterMotorTwo.set(speed);
    }
}
