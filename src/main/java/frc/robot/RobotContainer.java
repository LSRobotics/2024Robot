package frc.robot;

import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RadiansPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix6.swerve.SwerveRequest;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;
import com.playingwithfusion.TimeOfFlight;

import edu.wpi.first.util.MsvcRuntimeException;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.*;
import frc.robot.generated.TunerConstants;
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
    private final CommandSwerveDrivetrain mSwerve = TunerConstants.createDrivetrain();
    private final ShooterSubsystem m_shooter = new ShooterSubsystem();
    private final IntakeSubsystem m_intake = new IntakeSubsystem();
    private final IndexerSubsystem m_indexer = new IndexerSubsystem();
    private final LEDSubsystem m_leds = new LEDSubsystem();
    private BooleanSupplier notePresent = () -> notePresent();

    private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
            .withDeadband(TunerConstants.kSpeedAt12Volts.in(MetersPerSecond) * 0.1).withRotationalDeadband(RotationsPerSecond.of(0.5).in(RadiansPerSecond) * 0.1) // Add a 10% deadband
            .withDriveRequestType(com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType.OpenLoopVoltage);

    private TimeOfFlight indexBeamBreak = new TimeOfFlight(IndexerConstants.indexBeamBreakChannel);

    public static CTREConfigs ctreConfigs = new CTREConfigs();

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        mSwerve.setDefaultCommand(
            mSwerve.applyRequest(() -> drive.withVelocityX(-driverController.getLeftY() * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond)) // Drive
                    .withVelocityY(-driverController.getLeftX() * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond)) // Drive left with negative X
                    .withRotationalRate(-driverController.getRightX() * RotationsPerSecond.of(0.5).in(RadiansPerSecond)) // Drive counterclockwise
            )
        );

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

        driverController.y().whileTrue(
            new ShooterRampUpCommand(m_shooter, m_leds, Constants.ShooterConstants.shortShotSpeed, Constants.ShooterConstants.shortShotSpeed, notePresent)
        ).onFalse(new PassToShooterCmd(m_indexer, 0.6, notePresent));

        driverController.x().whileTrue(new ShooterRampUpCommand(m_shooter, m_leds, -0.1, -0.1, notePresent));

        driverController.leftTrigger().whileTrue(new PassToShooterCmd(m_indexer, -0.6, notePresent));

    } 
    public boolean notePresent() {
        return indexBeamBreak.getRange() <= IndexerConstants.beamBreakRange;
    }

    public Command getAutonomousCommand() {
        return new Command() {};
        
    }
}
