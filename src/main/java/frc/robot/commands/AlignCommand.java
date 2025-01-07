package frc.robot.commands;

import frc.robot.LimelightHelpers;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.VisionSubsystem;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;

public class AlignCommand extends Command {
  private final VisionSubsystem m_Vision;
  private final Swerve m_Swerve;
  private final double holdDistance; 
  private final int pipelineID;

  public AlignCommand(VisionSubsystem vision, Swerve swerve, double holdDistance, int pipelineID) {
    m_Vision = vision;
    m_Swerve = swerve;
    this.holdDistance = holdDistance;
    this.pipelineID = pipelineID;
    addRequirements(m_Vision, m_Swerve);
  }

  @Override
  public void initialize() {
    LimelightHelpers.setPipelineIndex("", this.pipelineID);
  }

  @Override
  public void execute() {
    double currentDistance = m_Vision.getDistance(this.pipelineID);
    TranslationData<Translation2d, Double> data = calculateTranslation("");

    if (Math.abs(currentDistance - holdDistance) > 1.0) { // 1 in tolerance
      m_Swerve.drive(data.getFirst(), data.getSecond(), false, false);
    } else {
      new PrintCommand("Aligned");
    }
  }

  @Override
  public void end(boolean interrupted) {}

  public TranslationData<Translation2d, Double> calculateTranslation(String limelightName) {
    double KpAim = -0.1; 
    double KpDistance = -0.1; 
    double minAimCommand = 0.05; // Prevent oscillatiin

    double tx = m_Vision.getTX(limelightName);
    double ty = m_Vision.getTY(limelightName);

    double headingError = -tx; // - = go left
    double distanceError = -ty;
    double steeringAdjust = 0.0;

    if (tx > 1.0) {
      steeringAdjust = KpAim * headingError - minAimCommand;
    } else if (tx < -1.0) {
      steeringAdjust = KpAim * headingError + minAimCommand;
    }

    double distanceAdjust = KpDistance * distanceError;

    return new TranslationData<>(new Translation2d(distanceAdjust, 0), steeringAdjust);
  }

  @Override
  public boolean isFinished() {
    double currentDistance = m_Vision.getDistance(this.pipelineID);
    return Math.abs(currentDistance - holdDistance) <= 1.0; // 1 in threshold
  }
}

final class TranslationData<T, U> {
  private final T translate;
  private final U rot;

  public TranslationData(T translation, U rotation) {
    this.translate = translation;
    this.rot = rotation;
  }

  public T getFirst() {
    return this.translate;
  }

  public U getSecond() {
    return this.rot;
  }

  @Override
  public String toString() { // Make Debug easier
    StringBuilder description = new StringBuilder();

    if (translate instanceof Translation2d) {
      Translation2d translation = (Translation2d) translate;
      double x = translation.getX();
      double y = translation.getY();

      if (Math.abs(x) < 0.05 && Math.abs(y) < 0.05) {
        description.append("No significant translation. ");
      } else if (x > 0) {
        description.append("Move forward ");
      } else if (x < 0) {
        description.append("Move backward ");
      }
      if (y > 0) {
        description.append("and to the right. ");
      } else if (y < 0) {
        description.append("and to the left. ");
      }
    }

    if (rot instanceof Double) {
      double rotation = (Double) rot;

      if (Math.abs(rotation) < 0.05) {
        description.append("No significant steering adjustment.");
      } else if (rotation > 0) {
        description.append("Small adjust to the right.");
      } else {
        description.append("Small adjust to the left.");
      }
    }

    return String.format(
      "TranslationData { translation=%s, rotation=%s } Description: %s",
      translate.toString(),
      rot.toString(),
      description.toString().trim()
    );
  }
}
