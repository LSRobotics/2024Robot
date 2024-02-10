package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkLowLevel.MotorType;


public class IntakeSubsystem extends SubsystemBase {
    

    private CANSparkMax intakeMotor = new CANSparkMax(13, MotorType.kBrushless);
    public IntakeSubsystem() {
        super();
        intakeMotor.setInverted(true);
        //intakeMotor.setNeutralMode(NeutralMode.Brake);
    }
    
    public void setPower(double power) {
        intakeMotor.set(power);
    }


}
