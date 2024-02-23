package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;


//import com.ctre.phoenix.motorcontrol.NeutralMode;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import com.ctre.phoenix.motorcontrol.can.TalonFX;

//import com.ctre.phoenix6.hardware.core.CoreTalonFX;
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
