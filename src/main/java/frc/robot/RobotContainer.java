package frc.robot;

import java.util.function.BooleanSupplier;

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
    private final IntakeSubsystem m_intake = new IntakeSubsystem();
    private final IndexerSubsystem m_indexer = new IndexerSubsystem();
    private final LEDSubsystem m_leds = new LEDSubsystem();
    private BooleanSupplier notePresent = () -> notePresent();

    private TimeOfFlight indexBeamBreak = new TimeOfFlight(IndexerConstants.indexBeamBreakChannel);

    public static CTREConfigs ctreConfigs = new CTREConfigs();

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
                new TeleopSwerve(
                        s_Swerve,
                        () -> SwerveConstants.demoSpeed * -driverController.getLeftY(),
                        () -> SwerveConstants.demoSpeed * -driverController.getLeftX(),
                        () -> 0.5 * -driverController.getRightX(),
                        () -> driverController.leftBumper().getAsBoolean()));

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

        driverController.b().onTrue(new IntakeRunCommand(m_intake, m_indexer, m_leds, IntakeConstants.intakeSpeed, IndexerConstants.indexSpeed, () -> notePresent()));
        driverController.y().whileTrue(new ShooterRampUpCommand(m_shooter, m_leds, Constants.ShooterConstants.shortShotSpeed, Constants.ShooterConstants.shortShotSpeed, notePresent));

        driverController.rightTrigger().whileTrue(new RunIndexCommand(m_indexer, IndexerConstants.indexSpeed));
        driverController.leftTrigger().whileTrue(new RunIndexCommand(m_indexer, -IndexerConstants.indexSpeed));

        driverController.b().onTrue(new ShooterRampUpCommand(m_shooter, m_leds, 0.6, 0.6, notePresent));

        driverController.x()
                .whileTrue(Commands.parallel(new ShooterRampUpCommand(m_shooter, m_leds, -0.1, -0.1, notePresent)));
        driverController.rightTrigger().onTrue(new PassToShooterCmd(m_indexer, 0.6, notePresent));

        operatorController.a().whileTrue(new RunIndexCommand(m_indexer, IndexerConstants.indexSpeed));

    } 

    public boolean notePresent() {
        return indexBeamBreak.getRange() <= IndexerConstants.beamBreakRange;
    }

    public Command getAutonomousCommand() {
        return new Command() {};
        
    }
}
