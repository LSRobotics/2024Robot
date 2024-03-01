package frc.robot;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import frc.lib.util.COTSTalonFXSwerveConstants;
import frc.lib.util.SwerveModuleConstants;

public final class Constants {
    public static final double stickDeadband = 0.1;

    public static final class SwerveConstants {
        public static final int pigeonID = 1;

        public static final COTSTalonFXSwerveConstants chosenModule =  //TODO: This must be tuned to specific robot
        COTSTalonFXSwerveConstants.SDS.MK4i.Falcon500(COTSTalonFXSwerveConstants.SDS.MK4i.driveRatios.L2);

        /* Drivetrain Constants */
        public static final double trackWidth = Units.inchesToMeters(21); //TODO: This must be tuned to specific robot
        public static final double wheelBase = Units.inchesToMeters(21); //TODO: This must be tuned to specific robot
        public static final double wheelCircumference = chosenModule.wheelCircumference;

        /* Swerve Kinematics 
         * No need to ever change this unless you are not doing a traditional rectangular/square 4 module swerve */
         public static final SwerveDriveKinematics swerveKinematics = new SwerveDriveKinematics(
            new Translation2d(wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(wheelBase / 2.0, -trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, -trackWidth / 2.0));

        /* Module Gear Ratios */
        public static final double driveGearRatio = chosenModule.driveGearRatio;
        public static final double angleGearRatio = chosenModule.angleGearRatio;

        /* Motor Inverts */
        public static final InvertedValue angleMotorInvert = chosenModule.angleMotorInvert;
        public static final InvertedValue driveMotorInvert = chosenModule.driveMotorInvert;

        /* Angle Encoder Invert */
        public static final SensorDirectionValue cancoderInvert = chosenModule.cancoderInvert;

        /* Swerve Current Limiting */
        public static final int angleCurrentLimit = 25;
        public static final int angleCurrentThreshold = 40;
        public static final double angleCurrentThresholdTime = 0.1;
        public static final boolean angleEnableCurrentLimit = true;

        public static final int driveCurrentLimit = 35;
        public static final int driveCurrentThreshold = 60;
        public static final double driveCurrentThresholdTime = 0.1;
        public static final boolean driveEnableCurrentLimit = true;

        /* These values are used by the drive falcon to ramp in open loop and closed loop driving.
         * We found a small open loop ramp (0.25) helps with tread wear, tipping, etc */
        public static final double openLoopRamp = 0.25;
        public static final double closedLoopRamp = 0.0;

        /* Angle Motor PID Values */
        public static final double angleKP = chosenModule.angleKP;
        public static final double angleKI = chosenModule.angleKI;
        public static final double angleKD = chosenModule.angleKD;

        /* Drive Motor PID Values */
        public static final double driveKP = 0.12; //TODO: This must be tuned to specific robot
        public static final double driveKI = 0.0;
        public static final double driveKD = 0.0;
        public static final double driveKF = 0.0;

        /* Drive Motor Characterization Values From SYSID */
        public static final double driveKS = 0; //TODO: This must be tuned to specific robot
        public static final double driveKV = 1.5;
        public static final double driveKA = 0;

        /* Swerve Profiling Values */
        /** Meters per Second */
        public static final double maxSpeed = 4.5; //TODO: This must be tuned to specific robot
        /** Radians per Second */
        public static final double maxAngularVelocity = 10.0; //TODO: This must be tuned to specific robot

        /* Neutral Modes */
        public static final NeutralModeValue angleNeutralMode = NeutralModeValue.Coast;
        public static final NeutralModeValue driveNeutralMode = NeutralModeValue.Brake;

        /* Module Specific Constants */
        /* Front Left Module - Module 0 */
        public static final class Mod0 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 11;
            public static final int angleMotorID = 10;
            public static final int canCoderID = 25;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(217.7); //-0.25 degrees
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }

        /* Front Right Module - Module 1 */
        public static final class Mod1 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 5;
            public static final int angleMotorID = 4;
            public static final int canCoderID = 27;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(87.62); //0 degrees
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }
        
        /* Back Left Module - Module 2 */
        public static final class Mod2 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 19;
            public static final int angleMotorID = 18;
            public static final int canCoderID = 26;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(306.91); //0.36 degrees
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }

        /* Back Right Module - Module 3 */
        public static final class Mod3 { //TODO: This must be tuned to specific robot
            public static final int driveMotorID = 3;
            public static final int angleMotorID = 2;
            public static final int canCoderID = 24;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(42.36); //0.02 degrees
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }
    }

    public static final class IntakeConstants { // TODO: Update Intake CAN IDs
        public static final int intakeMotorOneID = 13;
        public static final double intakeSpeed = 0.6;
    }

    public static final class ElevatorConstants { // TODO: Update Elevator CAN IDs
        public static final int elevatorMotorID = 16;
        public static final int elevatorTopLimitChannel = 5;
        public static final int elevatorBottomLimitChannel = 6;
        public static final double elevatorSpeed = .5;
    }

    public static final class IndexerConstants { // TODO: Update Indexer CAN IDs and Channels
        public static final int indexMotorID = 32;
        public static final int indexBeamBreakChannel = 61;
        public static final double indexSpeed = 0.2;
        public static final double beamBreakRange = 200;
    }

    public static final class ShooterConstants {
        public static final int shooterMotorOneID = 21;
        public static final int shooterMotorTwoID = 33;
        public static final double distanceShotSpeed = 0.55;
        public static final double ampShotSpeed = .4;
        public static final double shortShotSpeed = .6;
    }

    public static final class WristConstants {
        public static final int wristMotorID = 15;
        public static final int wristLimitOneChannel = 9;
        public static final int wristLimitTwoChannel = 8;
        public static final int subwofferAngle = 75;
        public static final int ampAngle = 85;
        public static final int distanceAngle = 60;
        public static final double wristP = 0;
        public static final double wristI = 0;
        public static final double wristD = 0;
        public static final double wristPosTolerance = 1;
        public static final double wristVelTolerance = 1;
    }
    public static final class LEDConstants {
        public static final int LEDDriverOneID = 3;
        public static final double colorRed = 0.61;
        public static final double colorHotPink = 0.57;
        public static final double colorYellow = 0.69;
        public static final double colorSkyBlue = 0.83;
        public static final double colorBlueViolet = 0.89;
        public static final double colorWhite = 0.93;
        public static final double colorLimeGreen = 0.73;
        public static final double colorOrange = 0.65;
        public static final double colorDarkGreen = 0.75;
        public static final double colorLawnGreen = 0.71;
        public static final double colorBlue = 0.87;
        public static final double colorGold = 0.67;
        public static final double twinklesColorOneAndTwo = 0.51;
    }
}
