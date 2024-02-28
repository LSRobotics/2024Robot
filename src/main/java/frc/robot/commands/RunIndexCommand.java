package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.LEDConstants;
import frc.robot.subsystems.IndexerSubsystem;

public class RunIndexCommand extends Command {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final IndexerSubsystem m_indexer;
    private double speed = 0;
    //private double indexSpeed;

    public RunIndexCommand(IndexerSubsystem index, double speed){
        m_indexer = index;
        this.speed = speed;

        addRequirements(index);
    }

    @Override
  public void initialize() {
    
    m_indexer.runIndexer(speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
    m_indexer.runIndexer(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

