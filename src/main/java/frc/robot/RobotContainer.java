package frc.robot;

import com.ctre.phoenix.sensors.WPI_PigeonIMU;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

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
import frc.robot.Constants;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.Constants.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final CommandXboxController driverController = new CommandXboxController(0);
    private final CommandXboxController operatorController = new CommandXboxController(1);
    private final SendableChooser<Command> autoChooser;
    

    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    private final ShooterSubsystem m_shooter = new ShooterSubsystem();
    private final ElevatorSubsystem m_elevator = new ElevatorSubsystem();
    private final IntakeSubsystem m_intake = new IntakeSubsystem();
    private final IndexerSubsystem m_indexer = new IndexerSubsystem();
    private final WristSubsystem m_wrist = new WristSubsystem();
    private final LEDSubsystem m_leds = new LEDSubsystem();

    public static CTREConfigs ctreConfigs = new CTREConfigs();


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -driverController.getRightY(), 
                () -> -driverController.getLeftX(), 
                () -> -driverController.getRightY(), 
                () -> driverController.leftBumper().getAsBoolean()
            )
        );

        NamedCommands.registerCommand("ShooterRampUp", new ShooterRampUpCommand(m_shooter, m_leds, ShooterConstants.distanceShotSpeed));
        NamedCommands.registerCommand("Intake", new RunIntakeCommand(m_intake, m_indexer, m_leds, IntakeConstants.intakeSpeed));

        autoChooser = AutoBuilder.buildAutoChooser();
        SmartDashboard.putData("AutoChooser", autoChooser);

        //NamedCommands.registerCommand("autoBalance", swerve.autoBalanceCommand());

        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        driverController.y().onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));
        //driverController.a().onTrue(Commands.parallel(new WristMovementCommand(()->2, m_wrist), new ShooterRampUpCommand(m_shooter, m_leds, .7)));
        //driverController.x().onTrue(new InstantCommand(() -> m_Blinkin.set(-0.87)));
        driverController.b().whileTrue(new RunIntakeCommand(m_intake, m_indexer, m_leds, IntakeConstants.intakeSpeed));
        driverController.a().whileTrue(new ClearIntakeCommand(m_intake, m_indexer, IntakeConstants.intakeSpeed));
        operatorController.povUp().onTrue(new ElevatorToSetPointCmd(m_elevator, m_leds, ElevatorConstants.elevatorSpeed, true));
        operatorController.povDown().onTrue(new ElevatorToSetPointCmd(m_elevator, m_leds, ElevatorConstants.elevatorSpeed, false));
        operatorController.a().onTrue(Commands.parallel(new ShooterRampUpCommand(m_shooter, m_leds, ShooterConstants.distanceShotSpeed),
                                                        new ElevatorToSetPointCmd(m_elevator, m_leds, ElevatorConstants.elevatorSpeed, true),
                                                        new WristMovementCommand(()-> WristConstants.distanceAngle, m_wrist)));
        operatorController.rightTrigger().onTrue(new PassToShooterCmd(m_indexer, m_leds, 0.6));
        


    }   //TODO connect to april tags

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getBlue1AutonomousCommand() {
        return new PathPlannerAuto("Blue 1");
    }

    public Command getBlue2AutonomousCommand() {
        return new PathPlannerAuto("Blue 2");
    }

    public Command getBlue3AutonomousCommand() {
        return new PathPlannerAuto("Blue 3");
    }

    public Command getRed1AutonomousCommand() {
        return new PathPlannerAuto("Red 1");
    }

    public Command getRed2AutonomousCommand() {
        return new PathPlannerAuto("Red 2");
    }

    public Command getRed3AutonomousCommand() {
        return new PathPlannerAuto("Red 3");
    }

    public Command getChosenAutonomousCommand() {
        return autoChooser.getSelected();
    }
}


   

