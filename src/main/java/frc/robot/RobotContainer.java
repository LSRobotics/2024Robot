package frc.robot;

import com.ctre.phoenix.sensors.WPI_PigeonIMU;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.playingwithfusion.TimeOfFlight;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import frc.lib.util.COTSTalonFXSwerveConstants.WCP.SwerveXStandard.driveRatios;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.Constants.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final CommandXboxController driverController = new CommandXboxController(0);
    private final CommandXboxController operatorController = new CommandXboxController(1);

    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    private final ShooterSubsystem m_shooter = new ShooterSubsystem();
    private final ElevatorSubsystem m_elevator = new ElevatorSubsystem();
    private final IntakeSubsystem m_intake = new IntakeSubsystem();
    private final IndexerSubsystem m_indexer = new IndexerSubsystem();
    private final WristSubsystem m_wrist = new WristSubsystem();
    private final LEDSubsystem m_leds = new LEDSubsystem();

    private boolean speed = true;
    
    private TimeOfFlight indexBeamBreak = new TimeOfFlight(IndexerConstants.indexBeamBreakChannel);

    public static CTREConfigs ctreConfigs = new CTREConfigs();

    public static SendableChooser<Command> autoChooser;

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> SwerveConstants.demoSpeed*-driverController.getLeftY(), 
                () -> SwerveConstants.demoSpeed*-driverController.getLeftX(), 
                () -> SwerveConstants.demoSpeed*-driverController.getRightX(), 
                () -> driverController.leftBumper().getAsBoolean()
            )
        );
        
       
        NamedCommands.registerCommand("ShooterRampUp", new ShooterRampUpCommand(m_shooter, m_leds, ShooterConstants.distanceShotSpeed, ShooterConstants.distanceShotSpeed, () -> notePresent()));
        NamedCommands.registerCommand("Intake", new IntakeRunCommand(m_intake, m_indexer, m_leds, 0.53, 0.2, () -> notePresent()));
        NamedCommands.registerCommand("PassToShooter", new PassToShooterCmd(m_indexer, IndexerConstants.indexSpeed, () -> notePresent()));
        NamedCommands.registerCommand("IntakeStage", new IntakeRunCommand(m_intake, m_indexer, m_leds, 0.65, 0.25, () -> notePresent()));
        //NamedCommands.registerCommand("ShooterRampDown", new ShooterRampUpCommand(m_shooter, m_leds, -0.1, () -> !notePresent()));
        NamedCommands.registerCommand("IntakeFarNote", new IntakeRunCommand(m_intake, m_indexer, m_leds, 0.6, 0.24, () -> notePresent())); 
        NamedCommands.registerCommand("IntakeRedStage", new IntakeRunCommand(m_intake, m_indexer, m_leds, 0.43, 0.14, () -> notePresent()));

        NamedCommands.registerCommand("IntakeFourNote", new IntakeRunCommand(m_intake, m_indexer, m_leds, 0.5, 0.24, () -> notePresent()));
        NamedCommands.registerCommand("IntakeFourNoteTest", new IntakeRunCommand(m_intake, m_indexer, m_leds, 0.53, 0.19, () -> notePresent()));


        NamedCommands.registerCommand("ShooterRampDown", new ShooterRampUpCommand(m_shooter, m_leds, -0.3, - 0.3, () -> notePresent()));

        NamedCommands.registerCommand("IntakeStageTest", new IntakeRunCommand(m_intake, m_indexer, m_leds, 0.6, 0.24, () -> notePresent())); 

        NamedCommands.registerCommand("preLehighIntake", new IntakeRunCommand(m_intake, m_indexer, m_leds, 0.6, 0.2, () -> notePresent())); 
                



        autoChooser = AutoBuilder.buildAutoChooser();
        SmartDashboard.putData("AutoChooser", autoChooser);

        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
     * it to a {@link
     * edu.wpi.first.wpil
     * ibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        //driverController.y().onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));
        //driverController.povLeft().onTrue(new InstantCommand(() -> s_Swerve.zeroLeftRedAuton(180)));
        //driverController.a().onTrue(Commands.parallel(new WristMovementCommand(()->2, m_wrist), new ShooterRampUpCommand(m_shooter, m_leds, .7)));
        //driverController.x().onTrue(new InstantCommand(() -> m_Blinkin.set(-0.87)));
        driverController.b().onTrue(new IntakeRunCommand(m_intake, m_indexer, m_leds, IntakeConstants.intakeSpeed, IndexerConstants.indexSpeed,    () -> notePresent()));
        driverController.y().whileTrue(new ClearIntakeCmd(m_intake, m_indexer, IntakeConstants.intakeSpeed, IndexerConstants.indexSpeed));


        //driverController.leftTrigger().whileTrue(m_indexer, IntakeConstants.intakeSpeed);
        driverController.rightTrigger().whileTrue(new RunIndexCommand(m_indexer, IndexerConstants.indexSpeed));
        driverController.leftTrigger().whileTrue(new RunIndexCommand(m_indexer, -IndexerConstants.indexSpeed));
        


        // driverController.povUp().onTrue(new ElevatorToSetPointCmd(m_elevator, m_leds, ElevatorConstants.elevatorSpeed, true));
        // driverController.povDown().onTrue(new ElevatorToSetPointCmd(m_elevator, m_leds, ElevatorConstants.elevatorSpeed, false));
        // //driverController.b().onTrue(Commands.parallel(new ShooterRampUpCommand(m_shooter, m_indexer, m_leds, 0.6),
        //                                                //new ElevatorToSetPointCmd(m_elevator, m_leds, ElevatorConstants.elevatorSpeed, true),
            //                                            new WristMovementCommand(()-> WristConstants.distanceAngle, m_wrist)));
        driverController.a().whileTrue(Commands.parallel(new ShooterRampUpCommand(m_shooter, m_leds, ShooterConstants.distanceShotSpeed, ShooterConstants.distanceShotSpeed, null),
                                                        //new ElevatorToSetPointCmd(m_elevator, m_leds, ElevatorConstants.elevatorSpeed, true),
                                                        new WristMovementCommand(()-> WristConstants.distanceAngle, m_wrist)));
                                                               
        
 
        driverController.rightBumper().whileTrue(Commands.parallel(new ShooterRampUpCommand(m_shooter, m_leds, 0.2, 0.2, null),
                                                        new ElevatorToSetPointCmd(m_elevator, m_leds, ElevatorConstants.elevatorSpeed, true),
                                                       new WristMovementCommand(()-> WristConstants.distanceAngle, m_wrist)));
        // driverController.x().whileTrue(Commands.parallel(new ShooterRampUpCommand(m_shooter, m_leds, -0.1, -0.1, null),
        //                                                 //new ElevatorToSetPointCmd(m_elevator, m_leds, ElevatorConstants.elevatorSpeed, true),
        //                                                 new WristMovementCommand(()-> WristConstants.distanceAngle, m_wrist)));
        // //driverController.rightTrigger().onTrue(new PassToShooterCmd(m_indexer, m_leds, 0.6));
        //  driverController.y().whileTrue(Commands.parallel(new ShooterRampUpCommand(m_shooter, m_leds, ShooterConstants.distanceShotSpeed + 0.15, ShooterConstants.distanceShotSpeed + 0.15, null),
        //                                                 //new ElevatorToSetPointCmd(m_elevator, m_leds, ElevatorConstants.elevatorSpeed,s true),
        //                                                 new WristMovementCommand(()-> WristConstants.distanceAngle, m_wrist)));
    
        driverController.x().whileTrue(Commands.parallel(new Amp(m_indexer, m_shooter, 0.23 , 0.027, 0.269, null),
                                                        //new ElevatorToSetPointCmd(m_elevator, m_leds, ElevatorConstants.elevatorSpeed,s true),
                                                        new WristMovementCommand(()-> WristConstants.distanceAngle, m_wrist)));

        operatorController.a().whileTrue(new RunIndexCommand(m_indexer, IndexerConstants.indexSpeed));

 /*        driverController.leftTrigger().whileTrue(Commands.parallel(new ShooterRampUpCommand(m_shooter, m_leds, 0.45, 0.19, null),
    long shot                                           //new ElevatorToSetPointCmd(m_elevator, m_leds, ElevatorConstants.elevatorSpeed,s true),
                                                        new WristMovementCommand(()-> WristConstants.distanceAngle, m_wrist)));         */                                       
        //driverController.rightTrigger().whileTrue(new RunIndexCommand(m_indexer, IndexerConstants.indexSpeed));


    } // TODO connect to april tags
 
    public boolean notePresent() {
        System.out.println(" " + indexBeamBreak.getRange()); //TODO: delete after testing
        return indexBeamBreak.getRange() <= IndexerConstants.beamBreakRange;
      }

    public Command getAutonomousCommand() {
        return autoChooser.getSelected();
    }
}
