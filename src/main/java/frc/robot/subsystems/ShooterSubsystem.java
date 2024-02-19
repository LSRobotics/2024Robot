package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;


import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class ShooterSubsystem extends SubsystemBase{
    private WPI_TalonSRX shooterMotorOne;
    private WPI_TalonSRX shooterMotorTwo;

    public ShooterSubsystem() {
        //shooterMotorOne = new CANSparkMax(ShooterConstants.shooterMotorOneID, MotorType.kBrushless);
        //shooterMotorTwo = new CANSparkMax(ShooterConstants.shooterMotorTwoID, MotorType.kBrushless);

        shooterMotorOne = new WPI_TalonSRX(ShooterConstants.shooterMotorOneID);
        shooterMotorOne = new WPI_TalonSRX(ShooterConstants.shooterMotorTwoID);


    }

    public void runShooter(double speed) {
        shooterMotorOne.set(speed);
        shooterMotorTwo.set(speed);
    }
    
}
