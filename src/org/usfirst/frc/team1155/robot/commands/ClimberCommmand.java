package org.usfirst.frc.team1155.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1155.robot.OI;
import org.usfirst.frc.team1155.robot.Robot;

public class ClimberCommand extends Command {
	public ClimberCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.climberSubsystem);
	}
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.climberSubsystem.setExtensionSpeed(0);
		Robot.climberSubsystem.changeAngle(0);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		if (getButtonPress(OI.descendLift) == 1)
			Robot.climberSubsystem.setExtensionSpeed(-1);
		else if (getButtonPress(OI.ascendLift) == 1)
			Robot.climberSubsystem.setExtensionSpeed(1);
		else
			Robot.climberSubsystem.setExtensionSpeed(0);
		if (getButtonPress(OI.rotateLift1) == 1)
			Robot.climberSubsystem.changeAngle(-1);
		else if (getButtonPress(OI.rotateLift2) == 1)
			Robot.climberSubsystem.changeAngle(1);
		else
			Robot.climberSubsystem.changeAngle(0);
	}
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}
	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.climberSubsystem.setExtensionSpeed(0);
		Robot.climberSubsystem.changeAngle(0);
	}
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		Robot.climberSubsystem.setExtensionSpeed(0)
		Robot.climberSubsystem.changeAngle(0)
	}
}
