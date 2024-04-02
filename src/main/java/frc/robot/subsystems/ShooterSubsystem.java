package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.ShooterConstants;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj.GenericHID;


import com.ctre.phoenix6.hardware.TalonFX;

public class ShooterSubsystem extends SubsystemBase{
    private TalonFX shooterMotorOne;
    private TalonFX shooterMotorTwo;
    private final CommandXboxController operatorController = new CommandXboxController(1);

   
   // private double g = 0;

    public ShooterSubsystem() {
        shooterMotorOne = new TalonFX(ShooterConstants.shooterMotorOneID);
        shooterMotorTwo = new TalonFX(ShooterConstants.shooterMotorTwoID);
    }
    public void runShooter(double speedTop , double speedBottom) {
        shooterMotorOne.set(speedBottom);
        shooterMotorTwo.set(speedTop);

        //shooterMotorOne.getSelectedSensoryVelocity();

       // if(shooterMotorOne.getVelocity().getValue() > 30){
       //     operatorController.getHID().setRumble(RumbleType.kBothRumble, 1);
       //// }
       // else{
       ///     operatorController.getHID().setRumble(RumbleType.kBothRumble, 0);
      //  }
        
            //operatorController.getHID().setRumble(RumbleType.kBothRumble, 1.0);

        System.out.println("This is the velocity " + shooterMotorOne.getVelocity().getValue());
    }


    public double getMotorVelocity(){
       return shooterMotorOne.getVelocity().getValue();
    }

    public void rumble(boolean check){
        
        
            if(shooterMotorOne.getVelocity().getValue() > 50 && check){
                operatorController.getHID().setRumble(RumbleType.kBothRumble, 0.2);
            }
            else{
                operatorController.getHID().setRumble(RumbleType.kBothRumble, 0);
            }
        
    }


}
