package frc.robot;

import com.ctre.phoenix.sensors.WPI_PigeonIMU;
import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final CommandXboxController driverController = new CommandXboxController(0);
    private final SendableChooser<Command> autoChooser;

    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    private final ShooterSubsystem m_shooter = new ShooterSubsystem();
    private final ElevatorSubsystem m_elevator = new ElevatorSubsystem();
    private final Spark m_Blinkin = new Spark(3);

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
        WPI_PigeonIMU gyro = new WPI_PigeonIMU(0); //TODO figure out arm angle/gyro method
        driverController.y().onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));
        driverController.a().onTrue(Commands.parallel(new WristMovementCommand(()-> gyro.getAngle(),()->2,m_shooter), new ShooterRampUpCommand(m_shooter, .7)));
        driverController.x().onTrue(new InstantCommand(() -> m_Blinkin.set(-0.87)));
        driverController.b().onTrue(new ElevatorToSetPointCmd(m_elevator, 1, true)); //TODO figure out numbers
    }   //TODO connect to april tags

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return autoChooser.getSelected();
    }
}
